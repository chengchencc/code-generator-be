package com.ludan.viz.dto;

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
* @Description: 部门表
* @Author: Smark
* @Date:   2021-05-07
* @Version: V1.0
*/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class SysDeptQueryDto extends EntityDto<String> {
    private static final long serialVersionUID=1L;


        @ApiModelProperty(value = "部门名称")
        private String deptName;
        @ApiModelProperty(value = "负责人")
        private String leader;
        @ApiModelProperty(value = "联系电话")
        private String phone;
        @ApiModelProperty(value = "邮箱")
        private String email;

}
