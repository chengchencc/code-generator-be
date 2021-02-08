package com.ludan.generator.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: chengchen
 * @create: 2021-01-28 16:47
 **/
@Data
public class TableInfoDto {
    private String tableName;
    private String engine;
    private String tableComment;
    private Date createTime;
}
