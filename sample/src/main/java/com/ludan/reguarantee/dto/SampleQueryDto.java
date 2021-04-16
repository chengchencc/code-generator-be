package com.ludan.reguarantee.dto;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.central.common.dto.EntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.math.BigDecimal;
import java.util.Date;


/**
* @Description: 示例
* @Author: ludan
* @Date:   2021-04-16
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
