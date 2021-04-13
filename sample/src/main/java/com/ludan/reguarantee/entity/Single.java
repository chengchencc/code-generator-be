package com.ludan.reguarantee.entity;

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
* @Description: 开发测试
* @Author: ludan
* @Date:   2021-04-13
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("t_test_Single")
public class Single extends BaseEntityFill {
    private static final long serialVersionUID=1L;
    /**
    * id
    */
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.ASSIGN_ID)
        @ExcelDefine(columnName = "id")
    private Integer id;
    /**
    * propInt
    */
    @ApiModelProperty(value = "propInt")
        @ExcelDefine(columnName = "propInt")
    private Integer propInt;
    /**
    * propString
    */
    @ApiModelProperty(value = "propString")
        @ExcelDefine(columnName = "propString")
    private Integer propString;
    /**
    * propBool
    */
    @ApiModelProperty(value = "propBool")
        @ExcelDefine(columnName = "propBool")
    private Integer propBool;

}
