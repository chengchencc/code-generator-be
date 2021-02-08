package com.ludan.generator.common.domain.service;

import com.ludan.generator.common.domain.dto.EntityDto;
import com.ludan.generator.common.domain.dto.PagedResultDto;
import com.ludan.generator.common.domain.entity.EntityBase;
import com.ludan.generator.common.utils.ModelMapperUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description com.inspur.edp.epp.framework.core.domain
 * Created by chengch
 * date on 2019/11/7
 */
public abstract class CrudAppServiceBase<TRepository extends JpaRepository<TEntity,TKey>,TEntity extends EntityBase<TKey>,TDto extends EntityDto<TKey>,TKey extends Serializable>
        extends ApplicationService {

    protected TRepository repository;

    public CrudAppServiceBase(TRepository repository) {
        this.repository = repository;
    }


    public List<TDto> findAll(){
        List<TEntity> entities = repository.findAll();
        return entities.stream().map(s->map(s)).collect(Collectors.toList());
    }

    /**
     * 分页取数
     * @param page 当前页号
     * @param size 每页显示的个数
     * @return 分页数据
     */
    public PagedResultDto<TDto> findAll(int page, int size){
        Page<TEntity> entities = repository.findAll(PageRequest.of(page,size));
        PagedResultDto<TDto> pagedDtos = map(entities);
        return pagedDtos;
    }

    /**
     * 分页取数
     * @param pageable
     * @return
     */
    public PagedResultDto<TDto> findAll(Pageable pageable){
        Page<TEntity> entities = repository.findAll(pageable);
        PagedResultDto<TDto> pagedDtos = map(entities);
        return pagedDtos;
    }

    public TDto findById(TKey id){
        TEntity entity = repository.findById(id).orElse(null);
        return map(entity);
    }

    /**
     * 新增
     * @param dto
     * @return
     */
    public TDto create(TDto dto){
        TEntity entity = map(dto);
        TEntity saveEntity = repository.save(entity);
        return map(saveEntity);
    }

    /**
     * 更新
     * @param dto
     * @return
     */
    public TDto update(TDto dto){
        TEntity entity = map(dto);
        repository.save(entity);
        return map(entity);
    }

    /**
     * 通过Id删除
     * @param id primaryKey
     */
    public void deleteById(TKey id){
        repository.deleteById(id);
    }

    /**
     * 删除实体
     * @param dto
     */
    public void delete(TDto dto){
        this.deleteById(dto.getId());
    }

    /**
     * 批量删除
     * @param ids
     */
    public void deleteInBatch(List<TKey> ids){
        List<TEntity> entities = this.repository.findAllById(ids);
        this.repository.deleteInBatch(entities);
    }


//    protected Class<TEntity> getEntityType(){
//        Class<TEntity> tClass = (Class<TEntity>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
//        return tClass;
//    }

    protected TEntity map(TDto dto){
        if (dto == null) return null;
        TEntity entity = ModelMapperUtils.map(dto, getEntityType());
        return entity;
    }

    protected TDto map(TEntity entity){
        if(entity == null) return null;
        TDto dto = ModelMapperUtils.map(entity, getDtoType());
        return dto;
    }

    protected PagedResultDto<TDto> map(Page<TEntity> entities){
        Page<TDto> pagedDtos = entities.map(entity -> map(entity));
        PagedResultDto<TDto> pagedResultDto = new PagedResultDto<>();
        pagedResultDto.setTotal(pagedDtos.getTotalElements());
        pagedResultDto.setItems(pagedDtos.getContent());
        return pagedResultDto;
    }


    protected abstract Class<TEntity> getEntityType();
    protected abstract Class<TDto> getDtoType();
}
