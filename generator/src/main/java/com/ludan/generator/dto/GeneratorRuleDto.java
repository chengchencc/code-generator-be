package com.ludan.generator.dto;

import com.ludan.generator.common.domain.dto.AuditEntityDto;
import com.ludan.generator.entity.UITemplate;
import lombok.Data;

import javax.persistence.Column;

/**
 * @author: chengchen
 * @create: 2021-04-14 11:44
 **/
@Data
public class GeneratorRuleDto extends AuditEntityDto<Integer> {
    private String packageName;
    private String moduleName;
    private String authorName;
    private String email;
    private UITemplate uiTemplate;
}
