package com.ludan.reguarantee.dto;

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
* @Description: 开发测试
* @Author: ludan
* @Date:   2021-04-13
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SingleQueryDto extends EntityDto<Integer> {
    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "id")
        private Integer id;
        @ApiModelProperty(value = "propInt")
        private Integer propInt;
        @ApiModelProperty(value = "propString")
        private Integer propString;
        @ApiModelProperty(value = "propBool")
        private Integer propBool;

}
