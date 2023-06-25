package com.ludan.generator.service;

import com.ludan.generator.common.datasource.CachedHikariJdbcDataSourceBuilder;
import com.ludan.generator.config.CodeGeneratorProperties;
import com.ludan.generator.dto.DataSourceDto;
import com.ludan.generator.dto.FieldInfoDto;
import com.ludan.generator.dto.TableInfoDto;
import com.ludan.generator.entity.*;
import com.ludan.generator.entity.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {
    private static final String Query_TableInfo_Sql =
            "select table_name tableName, engine engine, table_comment tableComment, create_time createTime " +
                    " from information_schema.tables" +
                    " where table_schema = (select database())" +
                    " order by create_time desc";

    private static final String Query_TableInfoByName_Sql =
            "select table_name tableName, engine engine, table_comment tableComment, create_time createTime " +
                    " from information_schema.tables" +
                    " where table_schema = (select database()) and table_name=?" +
                    " order by create_time desc";
    private static final String Query_FieldInfo_Sql =
            "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey," +
                    "ORDINAL_POSITION orderNo,COLUMN_DEFAULT defaultValue,IS_NULLABLE isNullable,CHARACTER_MAXIMUM_LENGTH columnLength," +
                    "NUMERIC_PRECISION numberPrecision, extra " +
                    " from information_schema.columns " +
                    " where table_name =? and table_schema = (select database()) " +
                    " order by ordinal_position";

    @Autowired
    private DataEntityService dataEntityService;
    @Autowired
    private DataFieldService dataFieldService;
    @Autowired
    private DataModelManager dataModelManager;
    @Autowired
    private CodeGeneratorProperties codeGeneratorProperties;

    private static TableInfoDto mapTableInfoRow(ResultSet rs, int rowNum) throws SQLException {
        TableInfoDto result = new TableInfoDto();
        result.setTableName(rs.getString("tableName"));
        result.setTableComment(rs.getString("tableComment"));
        result.setEngine(rs.getString("engine"));
        result.setCreateTime(rs.getDate("createTime"));
        return result;
    }

    /**
     * 获取表结构信息
     *
     * @param dataSourceDto
     */
    @Override
    public List<TableInfoDto> queryTableInfos(DataSourceDto dataSourceDto) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dataSourceDto);
        List<TableInfoDto> tableInfoDtos = jdbcTemplate.query(Query_TableInfo_Sql, DataSourceServiceImpl::mapTableInfoRow);
        return tableInfoDtos;
    }

    /**
     * 逆向工程导入
     *
     * @param dataSourceDto
     */
    @Override
    @Transactional()
    public void importFromDb(DataSourceDto dataSourceDto) {
        JdbcTemplate jdbcTemplate = getJdbcTemplate(dataSourceDto);

        for (String tableName : dataSourceDto.getTableNames()) {

            //获取字段信息
            List<FieldInfoDto> fieldInfos = getFieldInfos(jdbcTemplate, tableName);

            //获取表信息
            TableInfoDto tableInfo = getTableInfoByName(jdbcTemplate, tableName);

            //插入到模型表中
            DataEntity dataEntity = createDefaultDataEntity(tableInfo);

            this.dataEntityService.create(dataEntity);

            //插入到字段表中,字段格式转换
            for (FieldInfoDto field : fieldInfos) {
                DataField dataField = mapFieldInfoToDataField(field, dataEntity.getId());
                this.dataFieldService.create(dataField);
                DataFieldUI dataFieldUI = createDefaultDataFieldUIByField(dataField);
                this.dataModelManager.saveDataFieldUI(dataFieldUI);
            }
        }
    }

    public TableInfoDto getTableInfoByName(JdbcTemplate jdbcTemplate, String tableName) {
        List<TableInfoDto> tableInfoDtoList = jdbcTemplate.query(
                Query_TableInfoByName_Sql,
                Arrays.asList(tableName).toArray(),
                DataSourceServiceImpl::mapTableInfoRow);
        return tableInfoDtoList.get(0);
    }

    private List<FieldInfoDto> getFieldInfos(JdbcTemplate jdbcTemplate, String tableName) {
//        String inSql = String.join(",", Collections.nCopies(dataSourceDto.getTableNames().size(), "?"));
        List<FieldInfoDto> fieldInfos = jdbcTemplate.query(
                Query_FieldInfo_Sql,
                Arrays.asList(tableName).toArray(),
                (rs, rowNum) -> {
                    FieldInfoDto result = new FieldInfoDto();
                    result.setColumnName(rs.getString("columnName"));
                    result.setColumnComment(rs.getString("columnComment"));
                    result.setColumnKey(rs.getString("columnKey"));
                    result.setDataType(rs.getString("dataType"));
                    result.setExtra(rs.getString("extra"));
                    result.setOrderNo(rs.getInt("orderNo"));
                    result.setDefaultValue(rs.getString("defaultValue"));
                    result.setIsNullable(rs.getString("isNullable"));
                    result.setColumnLength(rs.getLong("columnLength"));
                    result.setNumberPrecision(rs.getLong("numberPrecision"));
                    return result;
                });
        return fieldInfos;
    }

    private JdbcTemplate getJdbcTemplate(DataSourceDto dataSourceDto) {
        DataSource dataSource = CachedHikariJdbcDataSourceBuilder.create()
                .url(dataSourceDto.getJdbcUrl())
                .driverClassName(dataSourceDto.getDriver())
                .username(dataSourceDto.getUsername())
                .password(dataSourceDto.getPassword())
                .build();
        return new JdbcTemplate(dataSource);
    }

    private DataEntity createDefaultDataEntity(TableInfoDto tableInfo) {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setTableName(tableInfo.getTableName());
        dataEntity.setTableType(TableType.SingleTable);
        dataEntity.setTableSchema(TableSchema.Nomal);
        //TODO:根据字段类型匹配
        dataEntity.setTableIdType(IdType.ASSIGN_ID);
        dataEntity.setUiTemplate(UITemplate.Default);
        dataEntity.setGeneratorRuleId(1);
        dataEntity.setCode(tableToJava(tableInfo.getTableName(), ""));
        //TODO:获取表备注信息
        dataEntity.setName(tableToJava(tableInfo.getTableName(), ""));
        dataEntity.setDescription(tableInfo.getTableComment());
        return dataEntity;
    }

    private DataFieldUI createDefaultDataFieldUIByField(DataField dataField) {
        ControlType controlType = ControlType.from(dataField.getDataFieldType());
        AbstractValidation validation = null;
        switch (dataField.getDataFieldType()) {
            case FLOAT:
            case LONG:
            case INTETER:
            case DOUBLE:
            case DECIMAL:
                validation = new NumberValidation(dataField.getIsRequired(), null, null);
                break;
            case TEXT:
            case STRING:
                validation = new StringValidation(dataField.getIsRequired(), 0L, dataField.getLength(), null);
                break;
            case BYTES:
                validation = new SimpleValidation(dataField.getIsRequired());
                break;
            case DATETIME:
                validation = new SimpleValidation(dataField.getIsRequired());
                break;
            case BOOLEAN:
                validation = new SimpleValidation(dataField.getIsRequired());
                break;
            case UNKNOWN:
            default:
                validation = new SimpleValidation(dataField.getIsRequired());
        }


        DataFieldUI dataFieldUI = new DataFieldUI(dataField.getId(), controlType, true, true, false, true, true, 200, validation);
        return dataFieldUI;
    }

    private DataField mapFieldInfoToDataField(FieldInfoDto fieldInfo, Integer entityId) {
        DataField dataField = new DataField();
        dataField.setTableFieldName(fieldInfo.getColumnName());
        dataField.setName(columnToJava(fieldInfo.getColumnName()));
        dataField.setDescription(fieldInfo.getColumnComment());
        dataField.setDataFieldType(convertDataTypeToDataFieldType(fieldInfo.getDataType()));
        dataField.setLength(fieldInfo.getColumnLength());
        dataField.setIsRequired(fieldInfo.getIsNullable().equalsIgnoreCase("YES") ? false : true);
        dataField.setIsPrimaryKey(fieldInfo.getColumnKey().equalsIgnoreCase("PRI") ? true : false);
        dataField.setDefaultValue(fieldInfo.getDefaultValue());
        dataField.setDisplay(true);
        dataField.setEnabled(true);
        dataField.setSortNo(fieldInfo.getOrderNo());
        dataField.setForeignKey(false);
        dataField.setPublished(true);
        dataField.setEntityId(entityId);
        return dataField;
    }

    private DataField createDefaultDataField() {
        DataField dataField = new DataField();

        return dataField;
    }

    private DataFieldType convertDataTypeToDataFieldType(String dataType) {
        String fieldTypeName = codeGeneratorProperties.getDataTypeMap().get(dataType);
        if (fieldTypeName == null) {
            return DataFieldType.UNKNOWN;
        }
        return DataFieldType.valueOf(fieldTypeName);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        String javaColumnName = WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");// 结果：LogId
        javaColumnName = StringUtils.uncapitalize(javaColumnName);// 结果：logId
        return javaColumnName;
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.substring(tablePrefix.length());
        }
        return WordUtils.capitalizeFully(tableName, new char[]{'_'}).replace("_", "");// 结果：LogId
    }

}
