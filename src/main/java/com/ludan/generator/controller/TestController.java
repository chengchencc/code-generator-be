package com.ludan.generator.controller;

import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.DataField;
import com.ludan.generator.entity.DataFieldQuery;
import com.ludan.generator.entity.DataFieldUI;
import com.ludan.generator.repository.DataEntityRepository;
import com.ludan.generator.repository.DataFieldQueryReposiroty;
import com.ludan.generator.repository.DataFieldReposiroty;
import com.ludan.generator.repository.DataFieldUIReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chengchen
 * @create: 2021-01-19 10:18
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataEntityRepository repository;

    @Autowired
    private DataFieldReposiroty dataFieldReposiroty;

    @Autowired
    private DataFieldQueryReposiroty dataFieldQueryReposiroty;

    @Autowired
    private DataFieldUIReposiroty dataFieldUIReposiroty;

    @GetMapping("/test")
    public void saveDataEntity(){

//        DataEntity dataEntity = new DataEntity();
//        dataEntity.setName("aa");
//        repository.save(dataEntity);
//
//        DataField dataField = new DataField();
////        dataField.setEntityId(dataEntity.getId());
//
//        dataFieldReposiroty.save(dataField);
//
//        DataFieldUI dataFieldUI = new DataFieldUI();
////        dataFieldUI.setDataFieldId(dataField.getId());
//        dataFieldUIReposiroty.save(dataFieldUI);
//
//        DataFieldQuery dataFieldQuery = new DataFieldQuery();
//        dataFieldQuery.setDataFieldId(dataField.getId());
//        dataFieldQueryReposiroty.save(dataFieldQuery);
//
//        DataEntity one = repository.getOne(dataEntity.getId());
//
//        DataField one1 = dataFieldReposiroty.getOne(dataField.getId());


    }
    @GetMapping("/gettest")
    public void getTest(){
        DataEntity one = repository.getOne(1);

        DataField one1 = dataFieldReposiroty.getOne(1);
    }

}
