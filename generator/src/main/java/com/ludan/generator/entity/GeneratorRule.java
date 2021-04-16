package com.ludan.generator.entity;

import com.ludan.generator.common.domain.entity.AuditEntityBase;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author: chengchen
 * @create: 2021-02-05 15:35
 **/
@Data
@Table
@Entity
public class GeneratorRule extends AuditEntityBase<Integer> {
    @Column(length = 100)
    private String RuleName;
    @Column(length = 100)
    private String packageName;
    @Column(length = 50)
    private String moduleName;
    @Column(length = 100)
    private String authorName;
    @Column(length = 100)
    private String email;
    @Column
    private UITemplate uiTemplate;

    public static GeneratorRule getDefault(){
        GeneratorRule generatorRule = new GeneratorRule();
        generatorRule.packageName = "com.ludan";
        generatorRule.moduleName = "reguarantee";
        generatorRule.authorName = "ludan";
        generatorRule.email = "ludan@inspur.com";
        generatorRule.uiTemplate = UITemplate.Default;
        return generatorRule;
    }
}
