package com.ludan.generator.generate.resolver;

import com.ludan.generator.common.exception.GeneratorException;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-05 17:56
 **/
public abstract class TemplateEngine {

    protected final static String templatePath = "/code-template";

    public abstract void resolve(String template, Map dataModel, Writer writer) throws IOException, TemplateException;

    public String resolve(String template, Map dataModel){
        try(StringWriter stringWriter = new StringWriter()) {
            resolve(template,dataModel,stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneratorException("模板渲染错误",e);
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new GeneratorException("模板渲染错误",e);
        }
    }

    public abstract void resolveSource(String templateSource, Map dataModel, Writer writer) throws IOException, TemplateException;

    public String resolveSource(String templateSource,Map dataModel){
        try(StringWriter stringWriter = new StringWriter()) {
            resolveSource(templateSource,dataModel,stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneratorException("模板渲染错误",e);
        } catch (TemplateException e) {
            e.printStackTrace();
            throw new GeneratorException("模板渲染错误",e);
        }
    }

}
