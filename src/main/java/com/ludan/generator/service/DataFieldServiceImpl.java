package com.ludan.generator.service;

import com.ludan.generator.common.controller.CrudControllerBase;
import com.ludan.generator.common.domain.service.CrudAppServiceBase;
import com.ludan.generator.dto.DataFieldDto;
import com.ludan.generator.entity.DataField;
import com.ludan.generator.repository.DataFieldReposiroty;
import org.springframework.stereotype.Service;

/**
 * @author: chengchen
 * @create: 2021-01-25 16:10
 **/
@Service
public class DataFieldServiceImpl
        extends CrudAppServiceBase<DataFieldReposiroty, DataField, DataFieldDto,Integer>
        implements DataFieldService {

    public DataFieldServiceImpl(DataFieldReposiroty repository) {
        super(repository);
    }

    @Override
    protected Class<DataField> getEntityType() {
        return DataField.class;
    }

    @Override
    protected Class<DataFieldDto> getDtoType() {
        return DataFieldDto.class;
    }
}
