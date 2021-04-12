package com.ludan.generator.entity;

/**
 * 表类型
 * @author: chengchen
 * @create: 2021-01-18 20:08
 **/
public enum TableType {
    /**
     * 单表
     */
    SingleTable,
    /**
     * 主表
     */
    MainTable,
    /**
     * 子表，一对一
     */
    OneToOneChildTable,
    /**
     * 子表，一对多
     */
    OneToManyChildTable,
}
