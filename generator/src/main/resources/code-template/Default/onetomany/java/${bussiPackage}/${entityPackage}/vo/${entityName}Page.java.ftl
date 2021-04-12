package ${bussiPackage}.${entityPackage}.vo;

import java.util.List;
import ${bussiPackage}.${entityPackage}.entity.${entityName};
<#list subTables as sub>
import ${bussiPackage}.${entityPackage}.entity.${sub.entityName};
</#list>
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: ${tableVo.ftlDescription}
 * @Author: jeecg-boot
 * @Date:   ${.now?string["yyyy-MM-dd"]}
 * @Version: V1.0
 */
@Data
@ApiModel(value="${tableName}Page对象", description="${tableVo.ftlDescription}")
public class ${entityName}Page {

	<#assign excel_ignore_arr=['createBy','createTime','updateBy','updateTime','sysOrgCode']>
    <#list originalColumns as po>
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
	<#else>
		<#if po.fieldDbType =='Date'>
			<#if po.classType=='date'>
	<#if !excel_ignore_arr?seq_contains("${po.fieldName}")>
	@Excel(name = "${po.filedComment}", width = 15, format = "yyyy-MM-dd")
	</#if>
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
			<#else>
	<#if !excel_ignore_arr?seq_contains("${po.fieldName}")>
	@Excel(name = "${po.filedComment}", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	</#if>
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
			</#if>
		<#else>
	<#if !excel_ignore_arr?seq_contains("${po.fieldName}")>
	@Excel(name = "${po.filedComment}", width = 15${list_field_dictCode})
	</#if>
		</#if>
        <#if list_field_dictCode?length gt 1>
    @Dict(${list_field_dictCode?substring(2)})
        </#if>
	</#if>
	@ApiModelProperty(value = "${po.filedComment}")
	private <#if po.fieldType=='java.sql.Blob'>byte[]<#else>${po.fieldType}</#if> ${po.fieldName};
	</#list>

	<#list subTables as sub>
	@ExcelCollection(name="${sub.ftlDescription}")
	@ApiModelProperty(value = "${sub.ftlDescription}")
	private List<${sub.entityName}> ${sub.entityName?uncap_first}List;
	</#list>

}
