package com.ludan.generator.entity;

import lombok.Data;

/**
 * @author: chengchen
 * @create: 2021-02-05 15:35
 **/
@Data
public class GeneratorRule {
    private String packageName;
    private String moduleName;
    private String authorName;
    private String email;

    public static GeneratorRule getDefault(){
        GeneratorRule generatorRule = new GeneratorRule();
        generatorRule.packageName = "com.ludan";
        generatorRule.moduleName = "demo";
        generatorRule.authorName = "chengch";
        generatorRule.email = "chengch@inspur.com";
        return generatorRule;
    }
}
