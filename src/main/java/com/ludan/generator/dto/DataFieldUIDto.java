package com.ludan.generator.dto;

import com.ludan.generator.common.domain.dto.AuditEntityDto;
import com.ludan.generator.common.domain.dto.EntityDto;
import com.ludan.generator.common.jpa.JpaJsonConverter;
import com.ludan.generator.entity.ControlType;
import com.ludan.generator.entity.DataField;
import com.ludan.generator.entity.validation.AbstractValidation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author: chengchen
 * @create: 2021-01-25 16:04
 **/
@Getter
@Setter
public class DataFieldUIDto extends AuditEntityDto<Integer> {

    private int dataFieldId;

    private DataField dataField;
    /**
     * 控件类型
     */
    private ControlType controlType;
    /**
     * 表单显示
     */
    private Boolean isFormDisplay;
    /**
     * 列表页显示
     */
    private Boolean isListDisplay;
    /**
     * 是否排序字段
     */
    private Boolean isSupportSort;
    /**
     * 是否查询条件
     */
    private Boolean isQuery;
    /**
     * 表单width
     */
    private int colWidth;
    /**
     * 校验器
     */
    private AbstractValidation validation;
}
