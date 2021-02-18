package com.ludan.generator.generate;

import com.ludan.generator.common.exception.GeneratorException;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.generate.loader.ResourceLoaderFactory;
import com.ludan.generator.service.DataModelManager;
import com.ludan.generator.generate.resolver.TemplateEngine;
import com.ludan.generator.generate.resolver.TemplateEngineFactory;
import freemarker.template.TemplateException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
    private final TemplateEngine templateEngine ;
    private static final String OUTPUT_PATH = "D:\\code\\shandongLD\\code-generator\\code-generator-be\\sample\\src\\main\\java";
    public static final Pattern One_To_Many_Pattern = Pattern.compile("^\\[1-n]",Pattern.CASE_INSENSITIVE);



    public CodeGeneratorImpl(DataModelManager dataModelManager) {
        this.dataModelManager = dataModelManager;
        this.templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
    }


    /**
     * 生成代码
     *  1.获取实体结构
     *  2.获取生成策略
     *  3.组装model上下文
     *  4.获取模板
     *  5.遍历模板文件生成代码文件
     *
     * @param entityId
     * @return
     */
    @SneakyThrows
    @Override
    public void generate(Integer entityId,OutputStream outputStream) {
        DataEntity entity = dataModelManager.findByEntityId(entityId);
        GeneratorRule generatorRule = GeneratorRule.getDefault();


    }

    @Override
    public void generate(DataEntity entity,GeneratorRule generatorRule) {
        Map<String,Object> model = convertToViewModel(entity,generatorRule);

        //获取模板文件列表
        List<File> files = getTemplateFiles(entity);
        File templateDirectory = getTemplateDirectory(entity);

        //删除历史生成的数据
        File outputFolder = new File(OUTPUT_PATH);
        if (outputFolder.exists()){
            outputFolder.delete();
        }
        //遍历生成文件
        for (File file : files) {
            log.info("======================开始处理===================");
            log.info("templateFilePath::{}",file.getPath());
            String templateFilePath = templateDirectory.toURI().relativize(file.toURI()).getPath();

            String preParsedFilePath = templateFilePath;
            log.info("preParsedFilePath::{}",preParsedFilePath);

            /**
             * 解析文件名称，${packageName}、 同时将[1-n]Entity.java 类型的文件名称转换为列表
             * */
            List<String> fileRelativePaths = parsePath(preParsedFilePath, entity,generatorRule);

            for (String fileRelativePath : fileRelativePaths) {
                log.info("fileRelativePath::{}",fileRelativePath);
                String fileAbsolutePath = FilenameUtils.concat(OUTPUT_PATH,fileRelativePath);
                log.info("fileAbsolutePath::{}",fileAbsolutePath);
                File newFile = new File(fileAbsolutePath);
                File directory = newFile.getParentFile();
                if (!directory.exists()){
                    directory.mkdirs();
                }

                try (FileWriter fileWriter = new FileWriter(newFile);){
                    fileWriter.write("test");
//                    render(file.getPath(),model,fileWriter);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


    }

    /**
     * 模板结构：
     * /templateName/entitySchema/entityRelation
     * @templateName default\mobile\erp\oa\...
     * @entitySchema normal\tree
     * @entityRelation single\main\OneToOne\OneToMany\ManyToMany
     *
     * */
    private List<File> getTemplateFiles(DataEntity entity) {
        String templateName = entity.getUiTemplate().getTemplateName();
        String entitySchema = entity.getTableSchema().name();
        String relation = entity.getTableType().name();
        String language = "java";

        String dirPath = templateName+"/"+entitySchema+"/"+relation+"/"+language;
        return ResourceLoaderFactory.getLoader().listFiles(dirPath);
    }
    private File getTemplateDirectory(DataEntity entity) {
        String templateName = entity.getUiTemplate().getTemplateName();
        String entitySchema = entity.getTableSchema().name();
        String relation = entity.getTableType().name();
        String language = "java";

        String dirPath = templateName+"/"+entitySchema+"/"+relation+"/"+language;
        return ResourceLoaderFactory.getLoader().getTemplateDirectory(dirPath);
    }

    /**
     * 解析文件名称，
     * 1.形如：${packageName} 类型的文件名会使用模板方式进行解析
     * 2.[1-n]Entity.java 类型的文件名称转换为列表
     * @param filePath
     * @param entity
     * @param rule
     * @return
     */
    private List<String> parsePath(String filePath,DataEntity entity,GeneratorRule rule){
        Map<String,Object> model = new HashMap<>();
        model.put("packageName",rule.getPackageName());
        model.put("moduleName",rule.getModuleName());
        model.put("entityName",entity.getCode());
        model.put("entity",entity);
        model.put("rule",rule);
        List<String> result = new ArrayList<>();
        String pasedPath = renderSource(filePath, model);

        pasedPath = FilenameUtils.removeExtension(pasedPath);

        if(One_To_Many_Pattern.matcher(pasedPath).find()){
            for (DataEntity child : entity.getChildren()) {
                result.add( One_To_Many_Pattern.matcher(pasedPath).replaceFirst(child.getCode()));
//                result.add(pasedPath.replace("\\^[1-n]",child.getCode()));
            }
        }else {
            result.add(pasedPath);
        }

        return result;
    }

    public Map convertToViewModel(DataEntity entity,GeneratorRule rule){
        Map<String,Object> model = new HashMap<>();
        model.put("packageName",rule.getPackageName());
        model.put("moduleName",rule.getModuleName());
        model.put("entity",entity);
        model.put("rule",rule);
        return model;
    }

    @Override
    public String render() {
        return null;
    }


    public String render(Map model) {
        TemplateEngine freemarker = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
        String result = freemarker.resolve("/Test/test.ftl", model);

        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Velocity_Engine_Name);
        return templateEngine.resolve("/code-template/Test/test.vm", model);
    }

    public void render(String viewPath, Map model, Writer writer){
        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
        try {
            templateEngine.resolve(viewPath, model,writer);
        } catch (IOException e) {
//            e.printStackTrace();
            throw new GeneratorException(e);
        } catch (TemplateException e) {
//            e.printStackTrace();
            throw new GeneratorException(e);
        }
    }

    public String renderSource(String viewSource, Map model){
        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
        return templateEngine.resolveSource(viewSource, model);
    }


}
