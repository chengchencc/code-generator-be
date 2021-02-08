package com.ludan.generator.entity;

/**
 * 表结构
 * @author: chengchen
 * @create: 2021-01-28 14:23
 **/
public enum TableSchema {
    /**
     * 一般表结构
     */
    Nomal,
    /**
     * 树状结构，需要定义parentId
     */
    Tree,
}
