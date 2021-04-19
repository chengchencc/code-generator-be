package com.ludan.generator.entity;

import com.ludan.generator.common.domain.entity.AuditEntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class DataEntity extends AuditEntityBase<Integer> {
    /**
     * 数据库表名称
     */
//    @Column(unique = true, nullable = true,length = Default_Id_Length)
    @Column(unique = false, nullable = false,length = Default_Id_Length)
    private String tableName;
    /**
     * 编号:后续代码生成，类名、页面名称按照编号来生成
     */
    @Column(unique = false, nullable = true,length = Default_Id_Length)
    private String code;
    /**
     * 实体名称
     */
    @Column(length = Default_Name_Length)
    private String name;
    /**
     * 实体描述
     */
    @Column(length = Default_Description_Length)
    private String description;

    /**
     * 表结构，
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    private TableSchema tableSchema;

    /**
     * 父节点Id，{TableSchema.Tree}的时候才会使用
     */
    @Column(length = Default_Id_Length)
    private String parentId;

    /**
     * 表类型
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    private TableType tableType;
    /**
     * 针对一对多，一对一，此处配置主实体Id
     */
    @Column()
    private Integer mainEntityId;
    /**
     * 主键策略
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    private IdType tableIdType;

    /**
     * 生成代码使用的模板
     */
    //TODO:移到生成策略中
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    @Deprecated
    private UITemplate uiTemplate;
    /**
     * 生成规则Id
     */
    @Column
    private Integer generatorRuleId;
    /**
     * 实体版本号
     * v0:创建
     * v1:第一次发布
     * v2:第二次发布
     */
    private Integer version = 0;

    /**
     * 是否已发布
     */
    @Column(name = "isPublished")
    private boolean published;

    /**
     * 实体字段列表
     */
    @OneToMany(mappedBy = "dataEntity",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @OrderBy("sort_no asc")
    private List<DataField> fields = new ArrayList<>();

    /**
     * 存储方式
     */
    @Column(length = Default_Id_Length)
    private String storageType;

    /**
     * 子实体列表
     */
    @Transient
    private List<DataEntity> children;

    public DataEntity() {
    }

}
