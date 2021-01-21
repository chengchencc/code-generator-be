package com.ludan.generator.entity;

import com.ludan.generator.common.domain.entity.EntityBase;
import com.ludan.generator.common.jpa.JpaJsonConverter;
import com.ludan.generator.entity.validation.AbstractValidation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class DataFieldUI extends EntityBase<Integer> {

    private int dataFieldId;

    @OneToOne
    @JoinColumn(name = "dataFieldId",referencedColumnName = "id",insertable = false,updatable = false)
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
    @Column(columnDefinition = "json")
    @Convert(converter = JpaJsonConverter.class)
    private AbstractValidation validation;

    public DataFieldUI() {
    }

    public DataFieldUI(int dataFieldId, ControlType controlType, Boolean isFormDisplay,
                       Boolean isListDisplay, Boolean isSupportSort, Boolean isQuery, int colWidth,
                       AbstractValidation validation) {
        this.dataFieldId = dataFieldId;
        this.controlType = controlType;
        this.isFormDisplay = isFormDisplay;
        this.isListDisplay = isListDisplay;
        this.isSupportSort = isSupportSort;
        this.isQuery = isQuery;
        this.colWidth = colWidth;
        this.validation = validation;
    }
}
