package com.ludan.generator.model;

import com.central.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体字段
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("app_data_field")
public class AppDataField extends BaseEntity {
    private static final long serialVersionUID=1L;


    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    @ApiModelProperty(value = "租户id")
    private String tenantId;
    @ApiModelProperty(value = "校验正则")
    private String validateRegex;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "类型")
    private Integer dataType;
    @ApiModelProperty(value = "默认值")
    private String defaultValue;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;
    @ApiModelProperty(value = "实体id")
    private String entityId;
    @ApiModelProperty(value = "是否显示")
    private Boolean isDisplay;
    @ApiModelProperty(value = "是否主键")
    private Boolean isPrimaryKey;
    @ApiModelProperty(value = "是否已发布")
    private Boolean isPublished;
    @ApiModelProperty(value = "是否必填")
    private Boolean isRequired;
    @ApiModelProperty(value = "是否系统字段")
    private Boolean isSystem;
    @ApiModelProperty(value = "长度")
    private Integer length;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "排序号")
    private Integer sortNo;

    protected Serializable pkVal() {return this.id;}
}
