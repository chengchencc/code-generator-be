package com.ludan.demo.entity;

import com.central.common.model.SuperEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@TableName("Single")
public class Single extends SuperEntity {
    private static final long serialVersionUID=1L;

}
