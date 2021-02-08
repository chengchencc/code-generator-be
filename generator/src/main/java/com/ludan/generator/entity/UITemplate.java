package com.ludan.generator.entity;

/**
 *
 * @author: chengchen
 * @create: 2021-01-18 20:14
 **/
public enum UITemplate {
    Default("Default","默认");

    UITemplate(String templateName, String templateLable) {
        this.templateName = templateName;
        this.templateLable = templateLable;
    }

    /**
     * 模板名称(对应的模板存储路径)
     */
    private String templateName;
    /**
     * 显示名称
     */
    private String templateLable;

    public String getTemplateName() {
        return templateName;
    }

    public String getTemplateLable() {
        return templateLable;
    }
}
