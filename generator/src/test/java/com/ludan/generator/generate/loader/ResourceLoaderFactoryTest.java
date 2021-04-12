package com.ludan.generator.generate.loader;

import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-02-08 14:38
 **/
public class ResourceLoaderFactoryTest {
    @Test
    public void test(){
        List<File> files = ResourceLoaderFactory.getLoader().listFiles("Default");
        System.out.println(files);
    }
}
