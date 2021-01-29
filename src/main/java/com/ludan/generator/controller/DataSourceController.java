package com.ludan.generator.controller;

import com.ludan.generator.dto.DataSourceDto;
import com.ludan.generator.dto.TableInfoDto;
import com.ludan.generator.service.DataSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-28 17:21
 **/
@RestController
@RequestMapping("/datasource")
@Api(tags = "数据源接口")
public class DataSourceController {
    @Autowired
    private DataSourceService service;

    @ApiOperation("获取数据库表信息")
    @PostMapping("/getTableInfos")
    public List<TableInfoDto> getTableInfos(@RequestBody DataSourceDto dto){
        return service.queryTableInfos(dto);
    }

    @ApiOperation("导入表结构")
    @PostMapping
    public void importFromDb(@RequestBody DataSourceDto dto){
        service.importFromDb(dto);
    }

}
