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
* @Description: 备案信息
* @Author: ludan
* @Date:   2021-04-09
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RegistrationQueryDto extends EntityDto<String> {
    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "业务编号")
        private String code;
        @ApiModelProperty(value = "客户名称")
        private String custName;
        @ApiModelProperty(value = "客户性质 取字典值")
        private String custNature;
        @ApiModelProperty(value = "客户证件号类别 取字典值")
        private String custCertType;
        @ApiModelProperty(value = "证件号码")
        private String custCertNo;

}
