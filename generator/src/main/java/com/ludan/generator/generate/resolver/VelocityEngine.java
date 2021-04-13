package com.ludan.generator.generate.resolver;

import freemarker.template.TemplateException;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-05 19:10
 **/
public class VelocityEngine extends TemplateEngine {

    private org.apache.velocity.app.VelocityEngine velocityEngine;

    public VelocityEngine(String templatePath) {
        super(templatePath);
        velocityEngine = new org.apache.velocity.app.VelocityEngine();
        velocityEngine.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, false); // 不适用缓存
//        Properties p = new Properties();
//        p.setProperty("resource.loader.file.path", templatePath);
//        p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
//        p.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.FileResourceLoader");
//        p.setProperty("file.resource.loader.cache","false");
//        p.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, true); // 使用缓存
//        Velocity.init(p);
    }

    @Override
    public void resolve(String template, Map dataModel, Writer writer) {
        Template tpl = velocityEngine.getTemplate(template);
        tpl.merge(new VelocityContext(dataModel),writer);
    }

    @Override
    public void resolveSource(String templateSource, Map dataModel, Writer writer) {
        throw new NotImplementedException("方法未实现！");
    }


}
