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

}
