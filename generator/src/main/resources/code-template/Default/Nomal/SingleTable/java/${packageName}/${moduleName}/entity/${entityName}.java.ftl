package ${packageName}.${moduleName}.entity;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
<#--#if(${hasBigDecimal})-->
<#--import java.math.BigDecimal;-->
<#--#end-->
import java.util.Date;

<#--/**-->
<#-- * ${comments}-->
<#-- *-->
<#-- * @author ${author}-->
<#-- * @date ${datetime}-->
<#-- */-->
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("${entityName}")
public class ${entityName} extends SuperEntity {
    private static final long serialVersionUID=1L;

<#--#foreach ($column in $columns)-->
<#--#if($column.columnName != $pk.columnName && $column.attrname != "createTime" && $column.attrname != "updateTime")-->
<#--    //$column.comments-->
<#--    private $column.attrType $column.attrname;-->
<#--#end-->
<#--#end-->
}
