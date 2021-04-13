package com.ludan.generator.generate.resolver;

import org.apache.commons.lang3.NotImplementedException;

/**
 * @author: chengchen
 * @create: 2021-02-05 17:56
 **/
public class TemplateEngineFactory {

    public static final String Velocity_Engine_Name = "velocity";
    public static final String Freemarker_Engine_Name = "freemarker";

    public TemplateEngine create(String templateEngine,String templatePath) {
        if (templateEngine.equals(Velocity_Engine_Name))
            return new VelocityEngine(templatePath);
        else if (templateEngine.equals(Freemarker_Engine_Name))
            return new FreeMarkerEngine(templatePath);
        else
            throw new NotImplementedException("不支持的渲染引擎::"+templateEngine);
    }

    public static TemplateEngineFactory getInstance(){
        return new TemplateEngineFactory();
    }
}
