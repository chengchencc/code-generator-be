package com.ludan.demo.entity;

import com.central.common.model.SuperEntity;
import com.central.common.model.BaseEntityFill;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ludan.common.annotation.ExcelDefine;
import java.math.BigDecimal;
import java.util.Date;

/**
* @Description: 生成规则
* @Author: chengch
* @Date:   2021-04-06
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("generator_rule")
public class GeneratorRule {
    private static final long serialVersionUID=1L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
    private Integer id;
    /**
    * 租户Id
    */
    @ApiModelProperty(value = "租户Id")
        @ExcelDefine(columnName = "租户Id")
    private String tenantId;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
        @ExcelDefine(columnName = "创建时间")
    private Date creationTime;
    /**
    * 创建者
    */
    @ApiModelProperty(value = "创建者")
        @ExcelDefine(columnName = "创建者")
    private String creationUserId;
    /**
    * 删除时间
    */
    @ApiModelProperty(value = "删除时间")
        @ExcelDefine(columnName = "删除时间")
    private Date deletionTime;
    /**
    * 删除人
    */
    @ApiModelProperty(value = "删除人")
        @ExcelDefine(columnName = "删除人")
    private String deletionUserId;
    /**
    * 最后修改时间
    */
    @ApiModelProperty(value = "最后修改时间")
        @ExcelDefine(columnName = "最后修改时间")
    private Date lastModifyTime;
    /**
    * 最后修改人
    */
    @ApiModelProperty(value = "最后修改人")
        @ExcelDefine(columnName = "最后修改人")
    private String lastModifyUserId;
    /**
    * 作者
    */
    @ApiModelProperty(value = "作者")
        @ExcelDefine(columnName = "作者")
    private String authorName;
    /**
    * 作者邮箱
    */
    @ApiModelProperty(value = "作者邮箱")
        @ExcelDefine(columnName = "作者邮箱")
    private String email;
    /**
    * 模块名
    */
    @ApiModelProperty(value = "模块名")
        @ExcelDefine(columnName = "模块名")
    private String moduleName;
    /**
    * 包名
    */
    @ApiModelProperty(value = "包名")
        @ExcelDefine(columnName = "包名")
    private String packageName;
    /**
    * 模板
    */
    @ApiModelProperty(value = "模板")
        @ExcelDefine(columnName = "模板",dictCode = "projectSource")
    private Integer uiTemplate;

}
