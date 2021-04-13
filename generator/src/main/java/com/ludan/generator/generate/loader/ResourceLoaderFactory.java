package com.ludan.generator.generate.loader;

/**
 * @author: chengchen
 * @create: 2021-02-08 14:14
 **/
public class ResourceLoaderFactory {
    public static ResourceLoader getLoader(){
        return new ClasspathResourceLoader();
    }
    public static ResourceLoader getLoader(String templatePath){
        return new FileResourceLoader(templatePath);
    }
}
