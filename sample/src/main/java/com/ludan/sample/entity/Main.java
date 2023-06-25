package com.ludan.sample.entity;

import com.central.common.model.SuperEntity;
import com.central.common.model.BaseEntityFill;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.central.common.annotation.ExcelDefine;
import java.math.BigDecimal;
import java.util.Date;

/**
* @Description: 开发测试
* @Author: ludan
* @Date:   2021-05-08
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("t_main")
public class Main extends BaseEntityFill {
    private static final long serialVersionUID=1L;
    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
        @ExcelDefine(columnName = "id")
    private Integer id;
    /**
    * fieldInt
    */
    @ApiModelProperty(value = "fieldInt")
        @ExcelDefine(columnName = "fieldInt")
    private Integer fieldInt;
    /**
    * fieldString
    */
    @ApiModelProperty(value = "fieldString")
        @ExcelDefine(columnName = "fieldString")
    private String fieldString;
    /**
    * fieldBool
    */
    @ApiModelProperty(value = "fieldBool")
        @ExcelDefine(columnName = "fieldBool")
    private Boolean fieldBool;

}
