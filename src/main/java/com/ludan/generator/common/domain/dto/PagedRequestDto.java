package com.ludan.generator.common.domain.dto;

import lombok.Data;

/**
 * Description com.inspur.edp.epp.framework.infrastructure.dto
 * Created by chengch
 * date on 2019/11/4
 */
@Data
public class PagedRequestDto<T> {
    private int page;
    private int size;


}
