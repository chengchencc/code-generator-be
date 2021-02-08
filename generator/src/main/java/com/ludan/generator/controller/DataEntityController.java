package com.ludan.generator.controller;

import com.ludan.generator.common.controller.CrudControllerBase;
import com.ludan.generator.dto.DataEntityDto;
import com.ludan.generator.dto.DataModelRequestDto;
import com.ludan.generator.service.DataEntityService;
import com.ludan.generator.service.DataModelManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: chengchen
 * @create: 2021-01-25 15:42
 **/
@RestController
@RequestMapping("/dataModel")
@Api(tags = "实体管理")
public class DataEntityController extends CrudControllerBase<DataEntityService, DataEntityDto,Integer> {

    @Autowired
    DataModelManager dataModelManager;

    public DataEntityController(DataEntityService service) {
        super(service);
    }

    @ApiOperation("保存整个配置信息")
    @PostMapping("/saveDataModel")
    public void saveDataModel(@RequestBody DataModelRequestDto dto){
        dataModelManager.saveDataModel(dto);
    }

}
