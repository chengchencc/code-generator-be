package ${packageName}.${moduleName}.entity;

import com.central.common.model.SuperEntity;
import com.central.common.model.BaseEntityFill;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ludan.common.annotation.ExcelDefine;
<#--#if(${hasBigDecimal})-->
import java.math.BigDecimal;
<#--#end-->
import java.util.Date;

/**
* @Description: ${entity.description}
* @Author: ${rule.authorName}
* @Date:   ${.now?string["yyyy-MM-dd"]}
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("${entity.tableName}")
public class ${entityName} extends BaseEntityFill {
    private static final long serialVersionUID=1L;
<#--<#list entity.fields?keys as propName >-->
<#--    ${propName}-->
<#--</#list>-->
<#list entity.fields as field >
    <#assign fieldui = field.dataFieldUI>
    /**
    * ${field.description}
    */
    @ApiModelProperty(value = "${field.description}")
    <#if field.isPrimaryKey>
    @TableId(type = IdType.${entity.tableIdType})
    </#if>
    <#if fieldui.exportExcel>
        <#if fieldui.dictCode?has_content>
        @ExcelDefine(columnName = "${field.description}",dictCode = "${fieldui.dictCode}")
        <#else>
        @ExcelDefine(columnName = "${field.description}")
        </#if>
    </#if>
    private ${field.dataFieldType.javaType} ${field.name};
</#list>

<#--#foreach ($column in $columns)-->
<#--#if($column.columnName != $pk.columnName && $column.attrname != "createTime" && $column.attrname != "updateTime")-->
<#--    //$column.comments-->
<#--    private $column.attrType $column.attrname;-->
<#--#end-->
<#--#end-->
}
