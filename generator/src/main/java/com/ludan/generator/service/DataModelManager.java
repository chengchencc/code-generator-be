package com.ludan.generator.service;

import com.ludan.generator.dto.DataModelRequestDto;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.DataFieldUI;
import com.ludan.generator.entity.GeneratorRule;

/**
 * @author: chengchen
 * @create: 2021-02-03 09:17
 **/
public interface DataModelManager {
    DataFieldUI saveDataFieldUI(DataFieldUI entity);
    void saveDataModel(DataModelRequestDto dto);
    DataEntity findByEntityId(Integer id);
    GeneratorRule findGeneratorRuleById(Integer id);
}
