package com.ludan.generator.common.domain.dto;

import com.ludan.generator.common.domain.Entity;
import lombok.Data;

import java.io.Serializable;

/**
 * Description com.inspur.edp.epp.framework.infrastructure.dto
 * Created by chengch
 * date on 2019/11/4
 */
@Data
public class EntityDto<TId> implements Entity<TId>, Serializable {
    protected TId id;
}
