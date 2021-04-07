package com.ludan.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EntityDto<T> {
    private T id;
    private String creator;
    private String createName;
    private String updator;
    private String updateName;
    private Date createTime;
    private Date updateTime;
}
