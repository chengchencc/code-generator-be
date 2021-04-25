package com.ludan.demo.dto;

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
* @Description: 开发测试
* @Author: ludan
* @Date:   2021-04-25
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ChildAQueryDto extends EntityDto<Integer> {
    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "id")
        private Integer id;
        @ApiModelProperty(value = "fieldInt")
        private Integer fieldInt;
        @ApiModelProperty(value = "fieldString")
        private String fieldString;
        @ApiModelProperty(value = "fieldBool")
        private Boolean fieldBool;

}
