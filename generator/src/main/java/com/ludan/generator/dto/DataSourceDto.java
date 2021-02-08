package com.ludan.generator.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-28 16:41
 **/
@Data
public class DataSourceDto {
    @NotEmpty
    private String jdbcUrl;
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    private String driver = "com.mysql.cj.jdbc.Driver";
    /**
     * 需要导入的表名列表，支持一次导入多个
     */
    private List<String> tableNames;
}
