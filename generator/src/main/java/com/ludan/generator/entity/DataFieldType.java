package com.ludan.generator.entity;

import java.math.BigDecimal;

public enum DataFieldType {
    /**
     * int
     * 整型
     */
    INTETER("Integer"),
    /**
     * Long
     */
    LONG("Long"),
    /**
     * string
     * 字符型
     */
    STRING("String"),
    /**
     * bool
     * 布尔型
     */
    BOOLEAN("Boolean"),
    /**
     * datetime
     * 日期时间类型
     */
    DATETIME("Date"),
    /**
     * 小数
     */
    DECIMAL("BigDecimal"),
    /**
     * float
     */
    FLOAT("Float"),
    /**
     * 双精度
     */
    DOUBLE("Double"),
    /**
     * 字节型
     * 用于存储图片、文件等二进制数据,对应标准sql blob
     */
    BYTES("Byte[]"),
    /**
     * text
     * 文本类型
     * 用于存储大文本文件，对应标准sql clob
     */
    TEXT("String"),
    UNKNOWN("Unknow");
    /**
     * 系统关联类型，关联系统存在的表
     */
//    SYSREFERENCE,
    /**
     * 关联类型，可以关联其他表
     */
//    REFERENCE

    private String javaType;

    DataFieldType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaType() {
        return javaType;
    }
}
