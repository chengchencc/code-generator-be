package com.ludan.generator.common.domain.service;

import com.ludan.generator.common.domain.dto.TreeEntityDto;

import java.util.List;
import java.util.Map;

public interface ITreeAppService<TEntityDto extends TreeEntityDto<TEntityDto,TKey>,TKey> extends ICrudAppService<TEntityDto,TKey>{
    TEntityDto  addChild(TEntityDto dto, TKey key);
    TEntityDto  addSibling(TEntityDto dto, TKey key);
    TEntityDto addFirstRoot(TEntityDto dto);
    void delete(TKey id);

    List<TEntityDto> findAllWithTree();

    TEntityDto findByIdAndOffSpringWithTree(TKey id);
    List<TEntityDto> findByIdAndOffSpring(TKey id);

    TEntityDto findByIdAndChildrenWithTree(TKey id);
    List<TEntityDto> findByIdAndChildren(TKey id);

    List<TEntityDto> findByStoreId(TKey id);
    List<TEntityDto> findByStoreIdWithTree(TKey id);

    TEntityDto findParentDtoOrNull(TKey id);
    List<TEntityDto> findChildren(TKey key);

    List<TEntityDto > getTree(Map<String, Object> params);
}

