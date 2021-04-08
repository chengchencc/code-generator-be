package com.ludan.demo.dto;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ludan.common.dto.EntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;


/**
* @Description: 单表
* @Author: chengch
* @Date:   2021-04-07
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SampleDto extends EntityDto<String> {
    private static final long serialVersionUID=1L;

    /**
    * 主键
    */
    @ApiModelProperty(value = "主键")
    private String id;
    /**
    * 文本框
    */
    @ApiModelProperty(value = "文本框")
    private String inputText;
    /**
    * 数字
    */
    @ApiModelProperty(value = "数字")
    private BigDecimal number;
    /**
    * 下拉单选
    */
    @ApiModelProperty(value = "下拉单选")
    private String selectOne;
    /**
    * 下拉多选
    */
    @ApiModelProperty(value = "下拉多选")
    private String selectMany;
    /**
    * 多选
    */
    @ApiModelProperty(value = "多选")
    private String checkBox;
    /**
    * 开关
    */
    @ApiModelProperty(value = "开关")
    private String radio;
    /**
    * 日期组件
    */
    @ApiModelProperty(value = "日期组件")
    private Date fieldDate;
    /**
    * 日期时间组件
    */
    @ApiModelProperty(value = "日期时间组件")
    private Date fieldDateTime;
    /**
    * 时间组件
    */
    @ApiModelProperty(value = "时间组件")
    private Date fieldTime;
    /**
    * 文件上传
    */
    @ApiModelProperty(value = "文件上传")
    private String fieldFile;
    /**
    * 图片上传
    */
    @ApiModelProperty(value = "图片上传")
    private String fieldImage;
    /**
    * 多行文本
    */
    @ApiModelProperty(value = "多行文本")
    private String textArea;
    /**
    * 用户选择组件
    */
    @ApiModelProperty(value = "用户选择组件")
    private String userSelect;
    /**
    * 组织选择组件
    */
    @ApiModelProperty(value = "组织选择组件")
    private String orgSelect;
    /**
    * 富文本
    */
    @ApiModelProperty(value = "富文本")
    private String richText;
    /**
    * 区域选择
    */
    @ApiModelProperty(value = "区域选择")
    private String region;
    /**
    * 树选择
    */
    @ApiModelProperty(value = "树选择")
    private String treeSelect;
    /**
    * 自定义弹框
    */
    @ApiModelProperty(value = "自定义弹框")
    private String modal;
    /**
    * 创建者
    */
    @ApiModelProperty(value = "创建者")
    private String creator;
    /**
    * 创建时间
    */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
    * 创建者名称
    */
    @ApiModelProperty(value = "创建者名称")
    private String createName;
    /**
    * 更新者
    */
    @ApiModelProperty(value = "更新者")
    private String updator;
    /**
    * 更新时间
    */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    /**
    * 更新者名称
    */
    @ApiModelProperty(value = "更新者名称")
    private String updateName;

}
