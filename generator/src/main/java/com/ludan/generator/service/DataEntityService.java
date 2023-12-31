package com.ludan.generator.service;

import com.ludan.generator.common.domain.service.ICrudAppService;
import com.ludan.generator.dto.DataEntityDto;
import com.ludan.generator.entity.DataEntity;

import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-22 14:15
 **/
public interface DataEntityService extends ICrudAppService<DataEntityDto,Integer> {
    DataEntity create(DataEntity entity);
    List<DataEntityDto> findTop10ByNameStartsWith(String name);
}
