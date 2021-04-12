package com.ludan.generator.service;
import com.ludan.generator.DataEntityUtil;
import com.ludan.generator.entity.*;

import cn.hutool.core.date.DateUtil;
import com.central.common.constant.CommonConstant;
import com.ludan.generator.generate.CodeGeneratorImpl;
import com.ludan.generator.generate.resolver.TemplateEngine;
import com.ludan.generator.generate.resolver.TemplateEngineFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-05 21:02
 **/
public class CodeGeneratorImplTest {
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


        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Velocity_Engine_Name);

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

        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
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


        CodeGeneratorImpl generator = new CodeGeneratorImpl(null);
        generator.generateToFile(singleEntity, GeneratorRule.getDefault());
        System.out.println("success");
    }

    @Test
    public void test_generate_code_OneToMany(){
        DataEntity entity = DataEntityUtil.getOneToManyEntity();
        CodeGeneratorImpl generator = new CodeGeneratorImpl(null);
        generator.generateToFile(entity, GeneratorRule.getDefault());
        System.out.println("success");
    }



}
