package com.ludan.generator.generate.resolver;

import com.ludan.generator.common.exception.GeneratorException;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-02-05 17:59
 **/
public class FreeMarkerEngine extends TemplateEngine {

    private Configuration configuration;

    public FreeMarkerEngine() {
        try {
            configuration = getDefaultConfiguration();
        } catch (IOException e) {
            e.printStackTrace();
            throw new GeneratorException("无法找到模板路径::"+templatePath);
        }
    }

    private Configuration getDefaultConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
//        cfg.setDirectoryForTemplateLoading(new File(templatePath));
//        cfg.setDirectoryForTemplateLoading(ResourceUtils.getFile("/"));
        cfg.setClassForTemplateLoading(this.getClass(),"/code-template");
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(true);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setCacheStorage(new NullCacheStorage());

        return cfg;
    }

    @SneakyThrows
    @Override
    public void resolve(String template, Map dataModel, Writer writer) {
        Template tpl = configuration.getTemplate(template);
        tpl.process(dataModel,writer);
    }

    @Override
    public void resolveSource(String templateSource, Map dataModel, Writer writer) throws IOException, TemplateException {
        Template tpl = new Template(null,new StringReader(templateSource),null);
        tpl.process(dataModel,writer);
    }


}
