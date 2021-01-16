package com.ludan.generator.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.ludan.generator.model.AppDataEntity;
import com.ludan.generator.service.IAppDataEntityService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;

/**
 * 模型
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
@Slf4j
@RestController
@RequestMapping("/appdataentity")
@Api(tags = "模型")
public class AppDataEntityController {
    @Autowired
    private IAppDataEntityService appDataEntityService;

    /**
     * 列表
     */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping
    public PageResult list(@RequestParam Map<String, Object> params) {
        return appDataEntityService.findList(params);
    }

    /**
     * 查询
     */
    @ApiOperation(value = "查询")
    @GetMapping("/{id}")
    public Result findUserById(@PathVariable String id) {
        AppDataEntity model = appDataEntityService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
     * 新增or更新
     */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody AppDataEntity appDataEntity) {
        appDataEntityService.saveOrUpdate(appDataEntity);
        return Result.succeed("保存成功");
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        appDataEntityService.removeById(id);
        return Result.succeed("删除成功");
    }
}
