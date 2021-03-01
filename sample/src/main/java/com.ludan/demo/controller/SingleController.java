package com.ludan.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.Single;
import com.ludan.demo.service.SingleService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;


/**
* @Description: 开发测试
* @Author: chengch
* @Date:   2021-03-01
* @Version: V1.0
*/
@Slf4j
@RestController
@RequestMapping("/Single")
@Api(tags = "开发测试")
public class SingleController {

@Autowired
private SingleService SingleService;

    /**
    * 列表
    */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
    @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/list")
    public PageResult findAll(@RequestParam Map<String, Object> params) {
        return SingleService.findList(params);
    }

    /**
    * 查询
    */
    @ApiOperation(value = "查询")
    @GetMapping("/detail/{id}")
    public Result findUserById(@PathVariable Long id) {
        Single model = SingleService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
    * 新增or更新
    */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody Single Single) {
        SingleService.saveOrUpdate(Single);
        return Result.succeed("保存成功");
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        SingleService.removeById(id);
        return Result.succeed("删除成功");
    }
}
