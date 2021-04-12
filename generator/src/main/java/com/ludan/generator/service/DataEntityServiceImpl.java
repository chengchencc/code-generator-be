package com.ludan.generator.service;

import com.ludan.generator.common.domain.service.CrudAppServiceBase;
import com.ludan.generator.dto.DataEntityDto;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.repository.DataEntityRepository;
import org.springframework.stereotype.Service;

/**
 * @author: chengchen
 * @create: 2021-01-22 14:22
 **/
@Service
public class DataEntityServiceImpl
        extends CrudAppServiceBase<DataEntityRepository, DataEntity, DataEntityDto,Integer>
        implements DataEntityService {

    public DataEntityServiceImpl(DataEntityRepository repository) {
        super(repository);
    }

    @Override
    protected Class<DataEntity> getEntityType() {
        return DataEntity.class;
    }

    @Override
    protected Class<DataEntityDto> getDtoType() {
        return DataEntityDto.class;
    }

    @Override
    public DataEntity create(DataEntity entity) {
        return repository.save(entity);
    }
}
