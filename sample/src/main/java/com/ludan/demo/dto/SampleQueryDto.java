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
* @Date:   2021-04-08
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SampleQueryDto extends EntityDto<String> {
    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "文本框")
        private String inputText;
        @ApiModelProperty(value = "数字")
        private BigDecimal number;
        @ApiModelProperty(value = "下拉单选")
        private String selectOne;
        @ApiModelProperty(value = "下拉多选")
        private String selectMany;
        @ApiModelProperty(value = "多选")
        private String checkBox;
        @ApiModelProperty(value = "开关")
        private String radio;
        @ApiModelProperty(value = "日期组件")
        private Date fieldDate;
        @ApiModelProperty(value = "日期时间组件")
        private Date fieldDateTime;
        @ApiModelProperty(value = "时间组件")
        private Date fieldTime;
        @ApiModelProperty(value = "文件上传")
        private String fieldFile;
        @ApiModelProperty(value = "图片上传")
        private String fieldImage;
        @ApiModelProperty(value = "多行文本")
        private String textArea;
        @ApiModelProperty(value = "用户选择组件")
        private String userSelect;
        @ApiModelProperty(value = "组织选择组件")
        private String orgSelect;
        @ApiModelProperty(value = "富文本")
        private String richText;
        @ApiModelProperty(value = "区域选择")
        private String region;
        @ApiModelProperty(value = "树选择")
        private String treeSelect;
        @ApiModelProperty(value = "自定义弹框")
        private String modal;

}
