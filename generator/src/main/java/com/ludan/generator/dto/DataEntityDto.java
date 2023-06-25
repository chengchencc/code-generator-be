package com.ludan.generator.dto;

import com.ludan.generator.common.domain.dto.AuditEntityDto;
import com.ludan.generator.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-25 15:31
 **/
@Getter
@Setter
public class DataEntityDto extends AuditEntityDto<Integer> {
    /**
     * 实体存储的表名称
     */
    private String tableName;
    /**
     * 实体编号
     */
    private String code;
    /**
     * 实体名称
     */
    private String name;
    /**
     * 实体描述
     */
    private String description;

    /**
     * 表结构，
     */
    private TableSchema tableSchema;
    /**
     * 树结构parentId
     */
    private Integer parentId;
    /**
     * 树结构 路径字段
     */
    private Integer treeIdPathId;
    /**
     * 表类型
     */
    private TableType tableType;
    /**
     * 针对一对多，一对一，此处配置主实体Id
     */
    private Integer mainEntityId;
    /**
     * 主键策略
     */
    private IdType tableIdType;
    /**
     * 生成代码使用的模板
     */
    private UITemplate uiTemplate;
    /**
     * 生成规则Id
     */
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
    private boolean published;

    /**
     * 实体字段列表
     */
    private List<DataField> fields = new ArrayList<>();

    /**
     * 存储方式
     */
    private String storageType;

    public DataEntityDto() {
    }

    public DataEntityDto(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
}
