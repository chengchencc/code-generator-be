package com.ludan.generator.dto;

import com.ludan.generator.common.domain.dto.AuditEntityDto;
import com.ludan.generator.entity.ControlType;
import com.ludan.generator.entity.DataField;
import com.ludan.generator.entity.validation.AbstractValidation;
import lombok.Getter;
import lombok.Setter;

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
    private boolean isFormDisplay;
    /**
     * 列表页显示
     */
    private boolean isListDisplay;
    /**
     * 是否排序字段
     */
    private boolean isSupportSort;
    /**
     * 是否查询条件
     */
    private boolean isQuery;
    /**
     * 是否导出
     */
    private boolean isExportExcel;
    /**
     * 表单width
     */
    private int tableColWidth;
    /**
     * 校验器
     */
    private AbstractValidation validation;
    /**
     * 字典编号 dict_code
     */
    private String dictCode;
    /**
     * 字典类别 dict_value_code
     */
    private String dictType;
}
