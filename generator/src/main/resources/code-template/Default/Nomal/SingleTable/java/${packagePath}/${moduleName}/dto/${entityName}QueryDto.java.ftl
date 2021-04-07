package ${packageName}.${moduleName}.dto;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ludan.common.dto.EntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
<#--#if(${hasBigDecimal})-->
import java.math.BigDecimal;
<#--#end-->
import java.util.Date;

<#--/**-->
<#-- * ${comments}-->
<#-- *-->
<#-- * @author ${author}-->
<#-- * @date ${datetime}-->
<#-- */-->

/**
* @Description: ${entity.description}
* @Author: ${rule.authorName}
* @Date:   ${.now?string["yyyy-MM-dd"]}
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ${entityName}QueryDto extends EntityDto<${primaryType}> {
    private static final long serialVersionUID=1L;

<#--entity.fields:-->
<#--<#list entity.fields?keys as propName>-->
<#--    ${propName}-->
<#--</#list>-->

<#list entity.fields as field >
    <#if field.dataFieldUI.query>
        @ApiModelProperty(value = "${field.description}")
        private ${field.dataFieldType.javaType} ${field.name};
    </#if>
</#list>

}
