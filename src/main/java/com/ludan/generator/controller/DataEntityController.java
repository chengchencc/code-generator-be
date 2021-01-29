package com.ludan.generator.controller;

import com.ludan.generator.common.controller.CrudControllerBase;
import com.ludan.generator.common.domain.service.ICrudAppService;
import com.ludan.generator.dto.DataEntityDto;
import com.ludan.generator.service.DataEntityService;
import io.swagger.annotations.Api;
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

    public DataEntityController(DataEntityService service) {
        super(service);
    }



}
