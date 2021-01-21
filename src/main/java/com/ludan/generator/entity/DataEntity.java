package com.ludan.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.ludan.generator.common.domain.entity.EntityBase;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table
@Entity
public class DataEntity extends EntityBase<Integer> {
    /**
     * 实体编号
     */
    @Column(length = Default_Id_Length)
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
     * 实体存储的表名称
     */
    @Column(unique = true, nullable = true,length = Default_Id_Length)
    private String tableName;
    /**
     * 表类型
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    private TableType tableType;
    /**
     * 主键策略
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    private IdType tableIdType;
    /**
     * 生成代码使用的模板
     */
    @Enumerated(EnumType.STRING)
    @Column(length = Default_Id_Length)
    private UITemplate uiTemplate;
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
    private List<DataField> fields = new ArrayList<>();

    /**
     * 存储方式
     */
    @Column(length = Default_Id_Length)
    private String storageType;
}
