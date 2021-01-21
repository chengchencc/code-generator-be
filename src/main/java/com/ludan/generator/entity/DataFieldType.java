package com.ludan.generator.entity;

public enum DataFieldType {
    /**
     * int
     * 整型
     */
    INTETER,
    /**
     * string
     * 字符型
     */
    STRING,
    /**
     * bool
     * 布尔型
     */
    BOOLEAN,
    /**
     * datetime
     * 日期时间类型
     */
    DATETIME,
    /**
     * 小数
     */
    DECIMAL,
    /**
     * 双精度
     */
    Double,
    /**
     * 字节型
     * 用于存储图片、文件等二进制数据,对应标准sql blob
     */
    BYTES,
    /**
     * text
     * 文本类型
     * 用于存储大文本文件，对应标准sql clob
     */
    TEXT,
    /**
     * 系统关联类型，关联系统存在的表
     */
//    SYSREFERENCE,
    /**
     * 关联类型，可以关联其他表
     */
//    REFERENCE
}
