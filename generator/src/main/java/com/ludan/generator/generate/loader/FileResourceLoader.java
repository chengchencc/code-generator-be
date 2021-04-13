package com.ludan.generator.generate.loader;

import org.apache.commons.io.FilenameUtils;

import java.io.File;

/**
 * @author: chengchen
 * @create: 2021-04-13 11:23
 **/
public class FileResourceLoader extends ResourceLoader {

    private String templatePath;

    public FileResourceLoader(String templatePath) {
        this.templatePath = templatePath;
    }

    @Override
    public File getTemplateDirectory(String relativePath) {
        String absolutePath = FilenameUtils.concat(templatePath, relativePath);
        return new File(absolutePath);
    }
}
