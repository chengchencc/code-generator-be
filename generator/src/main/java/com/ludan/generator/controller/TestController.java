package com.ludan.generator.controller;

import com.ludan.generator.entity.DataEntity;
import com.ludan.generator.entity.DataField;
import com.ludan.generator.repository.DataEntityRepository;
import com.ludan.generator.repository.DataFieldQueryReposiroty;
import com.ludan.generator.repository.DataFieldReposiroty;
import com.ludan.generator.repository.DataFieldUIReposiroty;
import com.ludan.generator.generate.CodeGenerator;
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

    @Autowired
    private CodeGenerator codeGenerator;

    @GetMapping("/test")
    public void saveDataEntity(){


    }
    @GetMapping("/gettest")
    public void getTest(){
        DataEntity one = repository.getOne(1);

        DataField one1 = dataFieldReposiroty.getOne(1);
    }

    @GetMapping("/testRender")
    public String testRender(){
        return codeGenerator.render();
    }

}
