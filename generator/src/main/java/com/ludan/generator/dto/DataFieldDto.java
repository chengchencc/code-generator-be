package com.ludan.generator.dto;

import com.ludan.generator.common.domain.dto.AuditEntityDto;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.DataFieldType;
import com.ludan.generator.entity.DataFieldUI;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: chengchen
 * @create: 2021-01-25 16:03
 **/
@Getter
@Setter
public class DataFieldDto extends AuditEntityDto<Integer> {
    /**
     * 编号，表的字段名称根据编号
     */
    private String tableFieldName;
    /**
     * 名称
     */
    private String name;

    /**
     * 字段描述
     */
    private String description;

    /**
     * 字段类型
     */
    private DataFieldType dataFieldType;
    /**
     * 字段长度
     */
    private Integer length;
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
    private String defaultValue;
    /**
     * 校验规则
     */
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
    private String joinTable;
    /**
     * 关联字段
     */
    private String joinField;
    /**
     * 是否已同步到数据库
     */
    private boolean isPublished;
    /**
     * 外键（实体）
     */
    private Integer entityId;

    private DataEntity dataEntity;

    private DataFieldUI dataFieldUI;
}
