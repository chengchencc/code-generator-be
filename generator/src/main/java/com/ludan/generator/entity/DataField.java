package com.ludan.generator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ludan.generator.common.domain.entity.AuditEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(indexes = {@Index(name = "data_field_sort_no",columnList = "sortNo",unique = false)})
@Entity
public class DataField extends AuditEntityBase<Integer> {
    /**
     * 编号，表的字段名称根据编号
     */
    @Column(length = Default_Id_Length)
    private String tableFieldName;
    /**
     * 名称
     */
    @Column(length = Default_Name_Length)
    private String name;

    /**
     * 字段描述
     */
    @Column(length = Default_Description_Length)
    private String description;

    /**
     * 字段类型
     */
    @Enumerated(EnumType.STRING)
    private DataFieldType dataFieldType;
    /**
     * 字段长度
     */
    private Long length;
    /**
     * 是否必须
     */
    private Boolean isRequired;
    /**
     * 是否主键
     */
    private Boolean isPrimaryKey;
    /**
     * 默认值
     */
    @Column(length = Default_Max_VChar_Length)
    private String defaultValue;
    /**
     * 校验规则
     */
    @Column(length = Default_Middle_Length)
    private String validateRegex;
    /**
     * 是否系统字段
     */
    private boolean isSystem;
    /**
     * 是否显示
     */
    private boolean isDisplay;
    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 排序号
     */
    private Integer sortNo;
    /**
     * 是否外键
     */
    private boolean isForeignKey;
    /**
     * 关联表
     */
    @Column(length = Default_Name_Length)
    private String joinTable;
    /**
     * 关联字段
     */
    @Column(length = Default_Name_Length)
    private String joinField;
    /**
     * 是否已同步到数据库
     */
    private boolean isPublished;
    /**
     * 外键（实体）
     */
    private Integer entityId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entityId",insertable = false,updatable = false)
    @JsonIgnore
    private DataEntity dataEntity;

    @OneToOne(mappedBy = "dataField",cascade = CascadeType.ALL)
    private DataFieldUI dataFieldUI;

    public DataField() {
    }

    public DataField(String name,DataFieldType dataFieldType,long length,int sortNo ){
        this(name,name,name,dataFieldType,length,false,false,null,null,
                false,true,true,sortNo);
    }

    public DataField(String tableFieldName, String name, String description, DataFieldType dataFieldType, long length,
                     boolean isRequired, boolean isPrimaryKey, String defaultValue, String validateRegex, boolean isSystem,
                     boolean isDisplay, boolean enabled, int sortNo) {
        this.tableFieldName = tableFieldName;
        this.name = name;
        this.description = description;
        this.dataFieldType = dataFieldType;
        this.length = length;
        this.isRequired = isRequired;
        this.isPrimaryKey = isPrimaryKey;
        this.defaultValue = defaultValue;
        this.validateRegex = validateRegex;
        this.isSystem = isSystem;
        this.isDisplay = isDisplay;
        this.enabled = enabled;
        this.sortNo = sortNo;
        this.isForeignKey = false;
    }

    public DataField(String tableFieldName, String name, String description, com.ludan.generator.entity.DataFieldType dataFieldType,
                     long length, boolean isRequired, boolean isPrimaryKey, String defaultValue, String validateRegex,
                     boolean isSystem, boolean isDisplay, boolean enabled, int sortNo, boolean isForeignKey,
                     String joinTable, String joinField, boolean isPublished, int entityId, DataEntity dataEntity,
                     DataFieldUI dataFieldUI) {
        this.tableFieldName = tableFieldName;
        this.name = name;
        this.description = description;
        this.dataFieldType = dataFieldType;
        this.length = length;
        this.isRequired = isRequired;
        this.isPrimaryKey = isPrimaryKey;
        this.defaultValue = defaultValue;
        this.validateRegex = validateRegex;
        this.isSystem = isSystem;
        this.isDisplay = isDisplay;
        this.enabled = enabled;
        this.sortNo = sortNo;
        this.isForeignKey = isForeignKey;
        this.joinTable = joinTable;
        this.joinField = joinField;
        this.isPublished = isPublished;
        this.entityId = entityId;
        this.dataEntity = dataEntity;
        this.dataFieldUI = dataFieldUI;
    }
}
