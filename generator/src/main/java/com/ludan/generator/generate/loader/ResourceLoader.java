package com.ludan.generator.generate.loader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: chengchen
 * @create: 2021-02-08 14:14
 **/
public abstract class ResourceLoader {
    protected static final String Code_Template_Directory_Path = "code-template";
    public abstract File getTemplateDirectory (String relativePath);

    /**
     *
     * @param relativePath 相对路径
     * @return
     */
    public List<File> listFiles (String relativePath){
        File directory = getTemplateDirectory(relativePath);
        return FileUtils.listFiles(directory,null,true).stream().map(file->{
            return file;
        }).collect(Collectors.toList());
    }
}
