package com.ludan.generator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ludan.generator.common.domain.entity.AuditEntityBase;
import com.ludan.generator.common.jpa.JpaJsonConverter;
import com.ludan.generator.entity.validation.AbstractValidation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class DataFieldUI extends AuditEntityBase<Integer> {

    private int dataFieldId;

    @OneToOne
    @JoinColumn(name = "dataFieldId",referencedColumnName = "id",insertable = false,updatable = false)
    @JsonIgnore
    private DataField dataField;
    /**
     * 控件类型
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
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
     * 表格列宽
     */
    private int tableColWidth;

    /**
     * 表单列宽
     */
    private String formColWidth;

    /**
     * 分组
     */
    private String groupName;
    /**
     * 校验器
     */
    @Column(columnDefinition = "json")
    @Convert(converter = JpaJsonConverter.class)
    private AbstractValidation validation;
    /**
     * 字典编号 dict_code
     */
    private String dictCode;
    /**
     * 字典类别 dict_value_code
     */
    private String dictType;

    public String getGroupName() {
        return groupName;
    }


    public DataFieldUI() {
    }

    public DataFieldUI(int dataFieldId,
                       ControlType controlType,
                       boolean isFormDisplay,
                       boolean isListDisplay,
                       boolean isSupportSort,
                       boolean isQuery,
                       boolean isExportExcel,
                       int tableColWidth,
                       AbstractValidation validation) {
        this.dataFieldId = dataFieldId;
        this.controlType = controlType;
        this.isFormDisplay = isFormDisplay;
        this.isListDisplay = isListDisplay;
        this.isSupportSort = isSupportSort;
        this.isQuery = isQuery;
        this.isExportExcel = isExportExcel;
        this.tableColWidth = tableColWidth;
        this.validation = validation;
    }
}
