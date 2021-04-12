package com.ludan.generator.service;

import com.ludan.generator.common.domain.service.ICrudAppService;
import com.ludan.generator.dto.DataFieldDto;
import com.ludan.generator.entity.DataField;

/**
 * @author: chengchen
 * @create: 2021-01-25 16:09
 **/
public interface DataFieldService extends ICrudAppService<DataFieldDto, Integer> {
    DataField create(DataField dataField);
}
