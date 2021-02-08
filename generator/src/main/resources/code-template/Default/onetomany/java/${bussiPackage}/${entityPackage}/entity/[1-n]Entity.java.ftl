<#list subTables as subTab>
#segment#${subTab.entityName}.java
package ${bussiPackage}.${entityPackage}.entity;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: ${subTab.ftlDescription}
 * @Author: jeecg-boot
 * @Date:   ${.now?string["yyyy-MM-dd"]}
 * @Version: V1.0
 */
@ApiModel(value="${tableName}对象", description="${tableVo.ftlDescription}")
@Data
@TableName("${subTab.tableName}")
public class ${subTab.entityName} implements Serializable {
    private static final long serialVersionUID = 1L;

<#assign excel_ignore_arr=['createBy','createTime','updateBy','updateTime','sysOrgCode']>
    <#list subTab.originalColumns as po>
	<#-- 生成字典Code -->
	<#assign list_field_dictCode="">
	<#if po.classType='sel_user'>
		<#assign list_field_dictCode=', dictTable = "sys_user", dicText = "realname", dicCode = "username"'>
	<#elseif po.classType='sel_depart'>
		<#assign list_field_dictCode=', dictTable = "sys_depart", dicText = "depart_name", dicCode = "id"'>
	<#elseif po.classType=='list' || po.classType=='list_multi' || po.classType=='sel_search' || po.classType=='radio' || po.classType=='checkbox'>
		<#if po.dictTable?default("")?trim?length gt 1>
			<#assign list_field_dictCode=', dictTable = "${po.dictTable}", dicText = "${po.dictText}", dicCode = "${po.dictField}"'>
		<#elseif po.dictField?default("")?trim?length gt 1>
			<#assign list_field_dictCode=', dicCode = "${po.dictField}"'>
		</#if>
	</#if>
	/**${po.filedComment}*/
	<#if po.fieldName == primaryKeyField>
	@TableId(type = IdType.ASSIGN_ID)
	<#else>
		<#if po.fieldDbType =='Date'>
			<#if po.classType=='date'>
    <#if !excel_ignore_arr?seq_contains("${po.fieldName}")>
	@Excel(name = "${po.filedComment}", width = 15, format = "yyyy-MM-dd"${list_field_dictCode})
    </#if>
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
			<#else>
    <#if !excel_ignore_arr?seq_contains("${po.fieldName}")>
	@Excel(name = "${po.filedComment}", width = 20, format = "yyyy-MM-dd HH:mm:ss"${list_field_dictCode})
	</#if>
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
			</#if>
		<#elseif !subTab.foreignKeys?seq_contains(po.fieldName?cap_first)>
    <#if !excel_ignore_arr?seq_contains("${po.fieldName}")>
	@Excel(name = "${po.filedComment}", width = 15${list_field_dictCode})
	</#if>
		</#if>
	</#if>
	@ApiModelProperty(value = "${po.filedComment}")
	private <#if po.fieldType=='java.sql.Blob'>byte[]<#else>${po.fieldType}</#if> ${po.fieldName};
	</#list>
}
</#list>