package com.ludan.generator.dto;

import com.ludan.generator.common.domain.dto.AuditEntityDto;
import com.ludan.generator.entity.UITemplate;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author: chengchen
 * @create: 2021-04-14 11:44
 **/
@Data
public class GeneratorRuleDto extends AuditEntityDto<Integer> {
    @ApiModelProperty(value = "ruleName")
    private String ruleName;
    @ApiModelProperty(value = "packageName")
    private String packageName;
    @ApiModelProperty(value = "moduleName")
    private String moduleName;
    @ApiModelProperty(value = "authorName")
    private String authorName;
    @ApiModelProperty(value = "email")
    private String email;
    @ApiModelProperty(value = "uiTemplate")
    private UITemplate uiTemplate;
}
