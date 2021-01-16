package com.ludan.generator.model;

import com.central.common.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ludan.generator.common.domain.entity.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模型
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("app_data_entity")
public class DataEntity extends Entity {
    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "租户Id")
    private String tenantId;
    @ApiModelProperty(value = "编号")
    private String code;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "是否发布")
    private Boolean isPublished;
    @ApiModelProperty(value = "存储方式")
    private String storageType;
    @ApiModelProperty(value = "表名")
    private String tableName;
    @ApiModelProperty(value = "版本")
    private Integer version;
//
//    private String creator;
//    private Date createTime;

    protected Serializable pkVal() {return this.id;}
}
