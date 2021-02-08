package com.ludan.generator.dto;

import lombok.Data;

/**
 * @author: chengchen
 * @create: 2021-02-02 17:17
 **/
@Data
public class FieldInfoDto {
    private String columnName;
    private String columnComment;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 主键:'PRI'
     */
    private String columnKey;
    /**
     * 自增：auto_increment
     */
    private String extra;
    /**
     * 排序号
     */
    private int orderNo;
    /**
     * 默认值：
     */
    private String defaultValue;
    /**
     * 是否可空： NO/YES
     */
    private String isNullable;
    /**
     * 长度
     */
    private Long columnLength;
    /**
     * 小数位数
     */
    private Long numberPrecision;

}
