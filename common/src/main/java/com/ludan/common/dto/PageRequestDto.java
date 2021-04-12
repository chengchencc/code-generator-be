package com.ludan.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class PageRequestDto {

    @ApiModelProperty(value = "页码")
    @Range(min = 1, message = "页码非法！")
    protected Integer pageNo;

    @ApiModelProperty(value = "每页个数")
    @Range(min = 1, max = 10000, message = "页大小非法！")
    protected Integer pageSize;

}
