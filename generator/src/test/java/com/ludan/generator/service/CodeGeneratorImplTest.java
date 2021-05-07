package com.ludan.generator.service;
import cn.hutool.core.exceptions.ExceptionUtil;
import com.ludan.generator.DataEntityUtil;
import com.ludan.generator.config.CodeGeneratorProperties;
import com.ludan.generator.entity.*;

import cn.hutool.core.date.DateUtil;
import com.central.common.constant.CommonConstant;
import com.ludan.generator.generate.CodeGeneratorImpl;
import com.ludan.generator.generate.resolver.TemplateEngine;
import com.ludan.generator.generate.resolver.TemplateEngineFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-05 21:02
 **/
@Slf4j
public class CodeGeneratorImplTest {
    private CodeGeneratorProperties codeGeneratorProperties;

    @Before
    public void prepare_test(){
        this.codeGeneratorProperties = new CodeGeneratorProperties();
        this.codeGeneratorProperties.setOutputPath("D:\\code\\shandongLD\\code-generator\\code-generator-be\\sample\\src\\main\\java");
        this.codeGeneratorProperties.setTemplatePath("D:\\code\\shandongLD\\code-generator\\code-generator-be\\generator\\src\\main\\resources\\code-template");
    }

    @Test
    public void test_velocity_render() {
        Map<String, Object> model = new HashMap<>();
        model.put("tableName", "tablename");
        model.put("dictValue", "DictValue");
        model.put("comments", "tableEntity.getComments()");
        model.put("pk", "tableEntity.getPk()");
        model.put("className", "tableEntity.getClassName()");
        model.put("classname", "tableEntity.getClassname()");
        model.put("pathName", "tableEntity.getClassname().toLowerCase()");
        model.put("columns", "tableEntity.getColumns()");
        model.put("hasBigDecimal", true);
        model.put("mainPath", "mainPath");
        model.put("package", "config.getString(PACKAGE)");
        model.put("moduleName", "config.getString(MODULE_NAME)");
        model.put("author", "author");
        model.put("email", "email");
        model.put("datetime", DateUtil.format(new Date(), CommonConstant.DATETIME_FORMAT));


        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Velocity_Engine_Name,codeGeneratorProperties.getTemplatePath());

        String s = templateEngine.resolve("/code-template/Default/test.vm", model);
        System.out.printf(s);
    }

    @Test
    public void test_freemarker_render(){

        Map<String, Object> model = new HashMap<>();
        model.put("tableName", "tablename");
        model.put("dictValue", "DictValue");
        model.put("comments", "tableEntity");
        model.put("pk", "tableEntity.getPk()");
        model.put("className", "tableEntity");
        model.put("classname", "tableEntity");
        model.put("pathName", "tableEntity.getClassname().toLowerCase()");
        model.put("columns", "tableEntity.getColumns()");
        model.put("hasBigDecimal", true);
        model.put("mainPath", "mainPath");
        model.put("package", "com.ludan");
        model.put("moduleName", "demo");
        model.put("author", "author");
        model.put("email", "email");
        model.put("datetime", DateUtil.format(new Date(), CommonConstant.DATETIME_FORMAT));

        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name,codeGeneratorProperties.getTemplatePath());
        String s = templateEngine.resolve("/Test/test.ftl", model);
        System.out.printf(s);
    }

    @Test
    public void test_generate_code_single() throws IOException {
        DataEntity singleEntity = DataEntityUtil.getSingleEntity();

//        try(FileWriter fileWriter = new FileWriter("D:/SingleEntity.json");) {
//            fileWriter.write(JSON.toJSONString(singleEntity));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        CodeGeneratorImpl generator = new CodeGeneratorImpl(null,this.codeGeneratorProperties);
        generator.generateToFile(singleEntity, GeneratorRule.getDefault());
        System.out.println("success");
    }

    @Test
    public void test_generate_code_OneToMany(){
        DataEntity entity = DataEntityUtil.getOneToManyEntity();
        CodeGeneratorImpl generator = new CodeGeneratorImpl(null,this.codeGeneratorProperties);
        GeneratorRule rule = GeneratorRule.getDefault();
        rule.setModuleName("sample");
        try {
            generator.generateToFile(entity, rule);
        }catch (Throwable e){
            System.out.println(ExceptionUtil.stacktraceToString(e,3000));
        }

        System.out.println("success");
    }



}
