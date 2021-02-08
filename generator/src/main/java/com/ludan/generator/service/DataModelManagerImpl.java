package com.ludan.generator.service;

import com.ludan.generator.common.utils.ModelMapperUtils;
import com.ludan.generator.dto.DataModelRequestDto;
import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.DataField;
import com.ludan.generator.entity.DataFieldUI;
import com.ludan.generator.repository.DataEntityRepository;
import com.ludan.generator.repository.DataFieldReposiroty;
import com.ludan.generator.repository.DataFieldUIReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-02-03 09:17
 **/
@Service
public class DataModelManagerImpl implements DataModelManager {

    @Autowired
    private DataEntityRepository dataEntityRepository;
    @Autowired
    private DataFieldReposiroty dataFieldReposiroty;
    @Autowired
    private DataFieldUIReposiroty dataFieldUIReposiroty;

    public DataFieldUI saveDataFieldUI(DataFieldUI entity){
        return this.dataFieldUIReposiroty.save(entity);
    }

    @Override
    @Transactional
    public void saveDataModel(DataModelRequestDto dto) {
        DataEntity entity = ModelMapperUtils.map(dto.getDataEntity(),DataEntity.class);
        entity.setFields(null);
        List<DataField> fields = ModelMapperUtils.mapAll(dto.getDataFields(),DataField.class);
        fields.forEach(dataField ->{dataField.setDataFieldUI(null);});
        List<DataFieldUI> uis = ModelMapperUtils.mapAll(dto.getDataFieldUI(),DataFieldUI.class);

        dataEntityRepository.save(entity);
        dataFieldReposiroty.saveAll(fields);
        dataFieldUIReposiroty.saveAll(uis);
    }

    @Override
    public DataEntity findByEntityId(Integer id) {
        DataEntity one = dataEntityRepository.getOne(id);
        return one;
    }

}
