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
import com.central.common.annotation.ExcelDefine;
import java.math.BigDecimal;
import java.util.Date;

/**
* @Description: 示例
* @Author: admin
* @Date:   2021-04-16
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("sample")
public class Sample extends BaseEntityFill {
    private static final long serialVersionUID=1L;
    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.ASSIGN_ID)
        @ExcelDefine(columnName = "主键")
    private String id;
    /**
    * 文本框
    */
    @ApiModelProperty(value = "文本框")
        @ExcelDefine(columnName = "文本框")
    private String inputText;
    /**
    * 数字
    */
    @ApiModelProperty(value = "数字")
        @ExcelDefine(columnName = "数字")
    private BigDecimal number;
    /**
    * 下拉单选
    */
    @ApiModelProperty(value = "下拉单选")
        @ExcelDefine(columnName = "下拉单选",dictCode = "projectSource")
    private String selectOne;
    /**
    * 下拉多选
    */
    @ApiModelProperty(value = "下拉多选")
        @ExcelDefine(columnName = "下拉多选",dictCode = "mainType")
    private String selectMany;
    /**
    * 多选
    */
    @ApiModelProperty(value = "多选")
        @ExcelDefine(columnName = "多选",dictCode = "custType")
    private String checkBox;
    /**
    * 开关
    */
    @ApiModelProperty(value = "开关")
        @ExcelDefine(columnName = "开关",dictCode = "stateSdndType")
    private String radio;
    /**
    * 日期组件
    */
    @ApiModelProperty(value = "日期组件")
        @ExcelDefine(columnName = "日期组件")
    private Date fieldDate;
    /**
    * 日期时间组件
    */
    @ApiModelProperty(value = "日期时间组件")
        @ExcelDefine(columnName = "日期时间组件")
    private Date fieldDateTime;
    /**
    * 时间组件
    */
    @ApiModelProperty(value = "时间组件")
        @ExcelDefine(columnName = "时间组件")
    private Date fieldTime;
    /**
    * 文件上传
    */
    @ApiModelProperty(value = "文件上传")
        @ExcelDefine(columnName = "文件上传")
    private String fieldFile;
    /**
    * 图片上传
    */
    @ApiModelProperty(value = "图片上传")
        @ExcelDefine(columnName = "图片上传")
    private String fieldImage;
    /**
    * 多行文本
    */
    @ApiModelProperty(value = "多行文本")
        @ExcelDefine(columnName = "多行文本")
    private String textArea;
    /**
    * 用户选择组件
    */
    @ApiModelProperty(value = "用户选择组件")
        @ExcelDefine(columnName = "用户选择组件")
    private String userSelect;
    /**
    * 组织选择组件
    */
    @ApiModelProperty(value = "组织选择组件")
        @ExcelDefine(columnName = "组织选择组件")
    private String orgSelect;
    /**
    * 富文本
    */
    @ApiModelProperty(value = "富文本")
        @ExcelDefine(columnName = "富文本")
    private String richText;
    /**
    * 区域选择
    */
    @ApiModelProperty(value = "区域选择")
        @ExcelDefine(columnName = "区域选择")
    private String region;
    /**
    * 树选择
    */
    @ApiModelProperty(value = "树选择")
        @ExcelDefine(columnName = "树选择")
    private String treeSelect;
    /**
    * 自定义弹框
    */
    @ApiModelProperty(value = "自定义弹框")
        @ExcelDefine(columnName = "自定义弹框")
    private String modal;

}
