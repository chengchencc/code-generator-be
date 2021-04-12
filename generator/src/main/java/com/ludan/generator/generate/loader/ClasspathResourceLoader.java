package com.ludan.generator.generate.loader;

import com.ludan.generator.common.exception.GeneratorException;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author: chengchen
 * @create: 2021-02-08 14:11
 **/
public class ClasspathResourceLoader extends ResourceLoader {
    @Override
    public File getTemplateDirectory(String relativePath) {
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();

        if (classLoader == null) {
            classLoader = ClasspathResourceLoader.class.getClassLoader();
        }
        URI uri = null;
        String resourceName = FilenameUtils.concat(Code_Template_Directory_Path, relativePath);
        URL resource = classLoader.getResource(resourceName);
        if (resource == null)
            throw new GeneratorException("template directory not found ", resourceName);
        try {
            uri = resource.toURI();
        } catch (URISyntaxException e) {
            throw new GeneratorException(e);
        }
        return new File(uri);
    }

}
