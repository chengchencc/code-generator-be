package com.ludan.generator.service;

import com.ludan.generator.common.datasource.CachedHikariJdbcDataSourceBuilder;
import com.ludan.generator.dto.DataSourceDto;
import com.ludan.generator.dto.TableInfoDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-28 16:56
 **/
@Service
public class DataSourceServiceImpl implements DataSourceService {
    private static final String Query_TableInfo_Sql =
            "select table_name tableName, engine engine, table_comment tableComment, create_time createTime " +
            " from information_schema.tables" +
            " where table_schema = (select database())" +
            " order by create_time desc";
    private static final String Query_FieldInfo_Sql = "" +
            " select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra " +
            " from information_schema.columns " +
            " where table_name =? and table_schema = (select database()) " +
            " order by ordinal_position";

    /**
     * 获取表结构信息
     *
     * @param dataSourceDto
     */
    @Override
    public List<TableInfoDto> queryTableInfos(DataSourceDto dataSourceDto) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dataSourceDto);
        List<TableInfoDto> tableInfoDtos = jdbcTemplate.query(Query_TableInfo_Sql, (rs, rowNum) -> {
            TableInfoDto result = new TableInfoDto();
            result.setTableName(rs.getString("tableName"));
            result.setTableComment(rs.getString("tableComment"));
            result.setEngine(rs.getString("engine"));
            result.setCreateTime(rs.getDate("createTime"));
            return result;
        });
        return tableInfoDtos;
    }

    /**
     * 逆向工程导入
     *
     * @param dataSourceDto
     */
    @Override
    public void importFromDb(DataSourceDto dataSourceDto) {

    }

    private JdbcTemplate getJdbcTemplate(DataSourceDto dataSourceDto){
        DataSource dataSource = CachedHikariJdbcDataSourceBuilder.create()
                .url(dataSourceDto.getJdbcUrl())
                .driverClassName(dataSourceDto.getDriver())
                .username(dataSourceDto.getUsername())
                .password(dataSourceDto.getPassword())
                .build();
        return new JdbcTemplate(dataSource);
    }


}
