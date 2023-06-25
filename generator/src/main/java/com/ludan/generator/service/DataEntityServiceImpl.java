package com.ludan.generator.service;

import com.ludan.generator.common.domain.service.CrudAppServiceBase;
import com.ludan.generator.dto.DataEntityDto;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.QDataEntity;
import com.ludan.generator.repository.DataEntityRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: chengchen
 * @create: 2021-01-22 14:22
 **/
@Service
public class DataEntityServiceImpl
        extends CrudAppServiceBase<DataEntityRepository, DataEntity, DataEntityDto,Integer>
        implements DataEntityService {

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public List<DataEntityDto> findTop10ByNameStartsWith(String name) {
//        Query query1 = entityManager.createQuery("select t.id,t.name,t.code,t.tableName from DataEntity t ");
        List<DataEntity> entities = repository.findTop10ByNameStartsWithOrderByName(name, PageRequest.of(0, 10));
        return entities.stream().map(s->map(s)).collect(Collectors.toList());
    }
}
