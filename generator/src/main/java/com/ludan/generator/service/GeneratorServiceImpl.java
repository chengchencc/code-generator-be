package com.ludan.generator.service;

import cn.hutool.core.date.DateUtil;
import com.central.common.constant.CommonConstant;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.service.resolver.TemplateEngine;
import com.ludan.generator.service.resolver.TemplateEngineFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-04 17:20
 **/
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private DataModelManager dataModelManager;

    /**
     * 生成代码
     *
     * @param entityId
     * @return
     */
    @SneakyThrows
    @Override
    public OutputStream GenerateCode(Integer entityId) {
        DataEntity entity = dataModelManager.findByEntityId(entityId);

        return null;
    }

    public String render() {
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

        TemplateEngine freemarker = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Freemarker_Engine_Name);
        String result = freemarker.resolve("/Test/test.ftl", model);

        TemplateEngine templateEngine = TemplateEngineFactory.getInstance().create(TemplateEngineFactory.Velocity_Engine_Name);
        return templateEngine.resolve("/code-template/Test/test.vm", model);
    }


}
