package com.ludan.generator.generate;

import com.ludan.generator.common.exception.GeneratorException;
import com.ludan.generator.config.CodeGeneratorProperties;
import com.ludan.generator.entity.*;
import com.ludan.generator.generate.loader.ResourceLoader;
import com.ludan.generator.generate.loader.ResourceLoaderFactory;
import com.ludan.generator.service.DataModelManager;
import com.ludan.generator.generate.resolver.TemplateEngine;
import com.ludan.generator.generate.resolver.TemplateEngineFactory;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.stream.Collectors;

/**
 * @author: chengchen
 * @create: 2021-02-04 17:20
 **/
@Service
@Slf4j
public class CodeGeneratorImpl implements CodeGenerator {

    private final CodeGeneratorProperties codeGeneratorProperties;
    private final DataModelManager dataModelManager;
    private final TemplateEngine templateEngine;
    private String outputPath;
    private String templatePath;
    public static final Pattern One_To_Many_Pattern = Pattern.compile("\\[1-n]", Pattern.CASE_INSENSITIVE);

    public CodeGeneratorImpl(DataModelManager dataModelManager,CodeGeneratorProperties codeGeneratorProperties) {
        this.dataModelManager = dataModelManager;
        this.codeGeneratorProperties = codeGeneratorProperties;
        this.outputPath = codeGeneratorProperties.getOutputPath();
        this.templatePath = codeGeneratorProperties.getTemplatePath();
        this.templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name,templatePath);
    }


    /**
     * 生成代码
     * 1.获取实体结构
     * 2.获取生成策略
     * 3.组装model上下文
     * 4.获取模板
     * 5.遍历模板文件生成代码文件
     *
     * @param entityId
     * @return
     */
    @Override
    public void generateToResponse(Integer entityId, HttpServletResponse response) {
        DataEntity entity = dataModelManager.findByEntityId(entityId);
        GeneratorRule generatorRule = GeneratorRule.getDefault();
        generateToResponse(entity,generatorRule,response);
        generateToFile(entity,generatorRule);
    }

    @Override
    public void generateToFile(DataEntity entity, GeneratorRule generatorRule) {
        internal(entity, generatorRule, this::writeToFile);
    }

    @Override
    public void generateToResponse(DataEntity entity, GeneratorRule generatorRule, HttpServletResponse response) {
        try (ZipOutputStream zip = new ZipOutputStream(response.getOutputStream())) {
            internal(entity, generatorRule, (code, outputFileRelativePath) -> {
                //添加到zip
                try {
                    zip.putNextEntry(new ZipEntry(outputFileRelativePath));
                    IOUtils.write(code, zip, StandardCharsets.UTF_8);
                    zip.closeEntry();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new GeneratorException(e);
                }
            });

            zip.finish();
        } catch (Exception e) {
            // Handle the exception
            throw new GeneratorException("write code to zip error!", e);
        }
    }

    private void internal(DataEntity entity, GeneratorRule generatorRule, Renderer renderer) {
        Map<String, Object> model = convertToViewModel(entity, generatorRule);
        ResourceLoader resourceLoader = ResourceLoaderFactory.getLoader(templatePath);
        String templatePathPrefix = getTemplatePathPrefix(entity);
        File templateDirectory = resourceLoader.getTemplateDirectory(templatePathPrefix);
        File templateRootDirectory = resourceLoader.getTemplateDirectory("");
        List<File> templateFiles = resourceLoader.listFiles(templatePathPrefix);

        //删除历史生成的数据
        File outputFolder = new File(outputPath);
        if (outputFolder.exists()) {
            try {
                FileUtils.deleteDirectory(outputFolder);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //遍历生成文件
        for (File templateFile : templateFiles) {
            log.info("======================开始处理===================");
            log.info("templateFilePath::{}", templateFile.getPath());
            String preOutputFileRelativePath = templateDirectory.toURI().relativize(templateFile.toURI()).getPath();
            // log.info("preOutputFileRelativePath::{}", preOutputFileRelativePath);
            String templateRelativePath = templateRootDirectory.toURI().relativize(templateFile.toURI()).getPath();
            log.info("templateRelativePath::{}", templateRelativePath);

            /**
             * 解析文件名称，${packageName}、 同时将[1-n]Entity.java 类型的文件名称转换为列表
             * */
//            List<String> outputFileRelativePaths = parsePath(preOutputFileRelativePath, entity,generatorRule);

            String outputFileRelativePath = renderSource(preOutputFileRelativePath, model);
            outputFileRelativePath = FilenameUtils.removeExtension(outputFileRelativePath);

            //TODO:主子表关系的生成待完善
            if (entity.getTableType().equals(TableType.SingleTable)) {
                renderTemplate(model, templateRelativePath, outputFileRelativePath, renderer);
            } else {
                if (One_To_Many_Pattern.matcher(outputFileRelativePath).find()) {
                    for (DataEntity child : entity.getChildren()) {
                        String childOutputFileRelativePath = One_To_Many_Pattern.matcher(outputFileRelativePath).replaceFirst(child.getCode());
                        Map childModel = convertToViewModel(child, generatorRule);
                        renderTemplate(childModel, templateRelativePath, childOutputFileRelativePath, renderer);
                    }
                } else {
                    renderTemplate(model, templateRelativePath, outputFileRelativePath, renderer);
                }
            }

        }

    }

    private void renderTemplate(Map<String, Object> model, String templateRelativePath, String outputFileRelativePath, Renderer renderer) {
        String code = render(templateRelativePath, model);
        renderer.execute(code, outputFileRelativePath);
    }

    private void writeToFile(String fileContent, String outputFileRelativePath) {
        log.info("outputFileRelativePath::{}", outputFileRelativePath);
        String outputFileAbsolutePath = FilenameUtils.concat(outputPath, outputFileRelativePath);
        log.info("outputFileAbsolutePath::{}", outputFileAbsolutePath);
        File newFile = new File(outputFileAbsolutePath);
        File directory = newFile.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(newFile);) {
            //render(templateRelativePath, model, fileWriter);
            fileWriter.write(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 模板结构：
     * /templateName/entitySchema/entityRelation
     *
     * @templateName default\mobile\erp\oa\...
     * @entitySchema normal\tree
     * @entityRelation single\main\OneToOne\OneToMany\ManyToMany
     */
    private String getTemplatePathPrefix(DataEntity entity) {
        String templateName = entity.getUiTemplate().getTemplateName();
        String entitySchema = entity.getTableSchema().name();
        String relation = entity.getTableType().name();
        String language = "java";

        String dirPath = templateName + "/" + entitySchema + "/" + relation + "/" + language;
        return dirPath;
    }

    /**
     * 解析文件名称，
     * 1.形如：${packageName} 类型的文件名会使用模板方式进行解析
     * 2.[1-n]Entity.java 类型的文件名称转换为列表
     *
     * @param filePath
     * @param entity
     * @param rule
     * @return
     */
    private List<String> parsePath(String filePath, DataEntity entity, GeneratorRule rule) {
        Map<String, Object> model = new HashMap<>();
        model.put("packageName", rule.getPackageName());
        model.put("moduleName", rule.getModuleName());
        model.put("entityName", entity.getCode());
        model.put("entity", entity);
        model.put("rule", rule);
        List<String> result = new ArrayList<>();
        String pasedPath = renderSource(filePath, model);

        pasedPath = FilenameUtils.removeExtension(pasedPath);

        if (One_To_Many_Pattern.matcher(pasedPath).find()) {
            for (DataEntity child : entity.getChildren()) {
                String fileName = One_To_Many_Pattern.matcher(pasedPath).replaceFirst(child.getCode());
                result.add(fileName);
//                result.add(pasedPath.replace("\\^[1-n]",child.getCode()));
            }
        } else {
            result.add(pasedPath);
        }

        return result;
    }

    public Map convertToViewModel(DataEntity entity, GeneratorRule rule) {
        Map<String, Object> model = new HashMap<>();
        model.put("packageName", rule.getPackageName());
        model.put("packagePath",rule.getPackageName().replace(".",File.separator));
        model.put("moduleName", rule.getModuleName());
        model.put("entityName", entity.getCode());
        model.put("entity", entity);
        model.put("rule", rule);


        //跟据某个属性分组  entity.fields.dataFieldUI
        List<DataField> list =  entity.getFields(); // new ArrayList<DataEntity>();
        Map<String, List<DataField>> groupList = list.stream().collect(Collectors.groupingBy(DataField -> {
            DataFieldUI dataFieldUI =  DataField.getDataFieldUI();
            if(dataFieldUI.getGroupName() == null || dataFieldUI.getGroupName().isEmpty()){
               return "foot";
            }else{
               return dataFieldUI.getGroupName();
            }
        }));
        model.put("groupList", groupList);

        log.info("groupList ========== ::{}", groupList);


        Optional<DataField> first = entity.getFields().stream().filter(s -> s.getIsPrimaryKey()).findFirst();
        if (!first.isPresent()){
            throw new GeneratorException("字段必须有一个主键标示");
        }
        model.put("primaryName",first.get().getName());
        model.put("primaryType",first.get().getDataFieldType().getJavaType());

        return model;
    }

    public void render(String viewPath, Map model, Writer writer) {
        try {
            templateEngine.resolve(viewPath, model, writer);
        } catch (IOException e) {
            throw new GeneratorException(e);
        } catch (TemplateException e) {
            throw new GeneratorException(e);
        }
    }

    public String render(String viewPath, Map model) {
        String result = templateEngine.resolve(viewPath, model);
        return result;
    }

    public String renderSource(String viewSource, Map model) {
        return templateEngine.resolveSource(viewSource, model);
    }


}
