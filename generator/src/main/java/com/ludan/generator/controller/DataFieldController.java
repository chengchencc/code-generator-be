package com.ludan.generator.controller;

import com.ludan.generator.common.controller.CrudControllerBase;
import com.ludan.generator.dto.DataFieldDto;
import com.ludan.generator.service.DataFieldService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chengchen
 * @create: 2021-01-25 16:14
 **/
@RestController
@RequestMapping("/dataField")
@Api(tags = "字段管理")
public class DataFieldController extends CrudControllerBase<DataFieldService, DataFieldDto,Integer> {


    public DataFieldController(DataFieldService service) {
        super(service);
    }
}
