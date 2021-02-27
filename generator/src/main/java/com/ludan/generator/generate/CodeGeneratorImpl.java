package com.ludan.generator.generate;

import com.ludan.generator.common.exception.GeneratorException;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.generate.loader.ResourceLoader;
import com.ludan.generator.generate.loader.ResourceLoaderFactory;
import com.ludan.generator.service.DataModelManager;
import com.ludan.generator.generate.resolver.TemplateEngine;
import com.ludan.generator.generate.resolver.TemplateEngineFactory;
import freemarker.template.TemplateException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: chengchen
 * @create: 2021-02-04 17:20
 **/
@Service
@Slf4j
public class CodeGeneratorImpl implements CodeGenerator {

    private final DataModelManager dataModelManager;
    private final TemplateEngine templateEngine;
    private static final String OUTPUT_PATH = "D:\\code\\shandongLD\\code-generator\\code-generator-be\\sample\\src\\main\\java";
    public static final Pattern One_To_Many_Pattern = Pattern.compile("\\[1-n]", Pattern.CASE_INSENSITIVE);


    public CodeGeneratorImpl(DataModelManager dataModelManager) {
        this.dataModelManager = dataModelManager;
        this.templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
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
    @SneakyThrows
    @Override
    public void generate(Integer entityId, OutputStream outputStream) {
        DataEntity entity = dataModelManager.findByEntityId(entityId);
        GeneratorRule generatorRule = GeneratorRule.getDefault();

    }

    @Override
    public void generate(DataEntity entity, GeneratorRule generatorRule) {
        Map<String, Object> model = convertToViewModel(entity, generatorRule);

        ResourceLoader resourceLoader = ResourceLoaderFactory.getLoader();
        String templatePathPrefix = getTemplatePathPrefix(entity);
        File templateDirectory = resourceLoader.getTemplateDirectory(templatePathPrefix);
        File templateRootDirectory = resourceLoader.getTemplateDirectory("");
        List<File> templateFiles = resourceLoader.listFiles(templatePathPrefix);

        //删除历史生成的数据
        File outputFolder = new File(OUTPUT_PATH);
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
            log.info("preOutputFileRelativePath::{}", preOutputFileRelativePath);
            String templateRelativePath = templateRootDirectory.toURI().relativize(templateFile.toURI()).getPath();
            log.info("templateRelativePath::{}", templateRelativePath);

            /**
             * 解析文件名称，${packageName}、 同时将[1-n]Entity.java 类型的文件名称转换为列表
             * */
//            List<String> outputFileRelativePaths = parsePath(preOutputFileRelativePath, entity,generatorRule);

            String outputFileRelativePath = renderSource(preOutputFileRelativePath, model);
            outputFileRelativePath = FilenameUtils.removeExtension(outputFileRelativePath);

            if (One_To_Many_Pattern.matcher(outputFileRelativePath).find()) {
                for (DataEntity child : entity.getChildren()) {
                    String childOutputFileRelativePath = One_To_Many_Pattern.matcher(outputFileRelativePath).replaceFirst(child.getCode());
                    Map childModel = convertToViewModel(child, generatorRule);
                    renderTemplate(childModel,templateRelativePath,childOutputFileRelativePath);
                }
            } else {
                renderTemplate(model, templateRelativePath, outputFileRelativePath);
            }


        }


    }

    private void renderTemplate(Map<String, Object> model, String templateRelativePath, String outputFileRelativePath) {
        log.info("outputFileRelativePath::{}", outputFileRelativePath);
        String outputFileAbsolutePath = FilenameUtils.concat(OUTPUT_PATH, outputFileRelativePath);
        log.info("outputFileAbsolutePath::{}", outputFileAbsolutePath);
        File newFile = new File(outputFileAbsolutePath);
        File directory = newFile.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter(newFile);) {
            render(templateRelativePath, model, fileWriter);
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
        model.put("moduleName", rule.getModuleName());
        model.put("entityName", entity.getCode());
        model.put("entity", entity);
        model.put("rule", rule);
        return model;
    }

    public void render(String viewPath, Map model, Writer writer) {
        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
        try {
            templateEngine.resolve(viewPath, model, writer);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new GeneratorException(e);
        } catch (TemplateException e) {
//            e.printStackTrace();
            throw new GeneratorException(e);
        }
    }

    public String renderSource(String viewSource, Map model) {
        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
        return templateEngine.resolveSource(viewSource, model);
    }


}
