package com.ludan.demo.dto;

import com.central.common.dto.EntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModelProperty;


/**
* @Description: 部门表
* @Author: Smark
* @Date:   2021-04-28
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
