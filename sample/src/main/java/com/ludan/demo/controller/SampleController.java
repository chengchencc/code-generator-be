package com.ludan.demo.controller;

import java.io.UnsupportedEncodingException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import com.central.common.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.Sample;
import com.ludan.demo.service.SampleService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;

import javax.servlet.http.HttpServletResponse;

/**
* @Description: 示例
* @Author: admin
* @Date:   2021-04-16
* @Version: V1.0
*/
@Slf4j
@RestController
@RequestMapping("/Sample")
@Api(tags = "示例")
public class SampleController  {
    @Autowired
    private SampleService sampleService;
    /**
    * 列表
    */
    @ApiOperation(value = "查询列表")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
    @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/list")
    public PageResult findAll(@RequestParam Map<String,Object> params) {
        Page<Sample> pageList = sampleService.findList(params);
        return PageResult.<Sample>builder().data(pageList.getRecords()).code(0).count(pageList.getTotal()).build();
    }

    /**
    * 查询
    */
    @ApiOperation(value = "查询")
    @GetMapping("/detail/{id}")
    public Result findUserById(@PathVariable Long id) {
        Sample model = sampleService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
    *   添加
    *
    * @param sample
    * @return
    */
    @ApiOperation(value="示例-添加", notes="示例-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody Sample sample) {
        sampleService.save(sample);
        return Result.succeed(sample,"添加成功！");
    }

    /**
    *  编辑
    *
    * @param sample
    * @return
    */
    @ApiOperation(value="示例-编辑", notes="示例-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody Sample sample) {
        sampleService.updateById(sample);
        return Result.succeed(sample,"编辑成功!");
    }

    /**
    * 新增or更新
    */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody Sample sample) {
        sampleService.saveOrUpdate(sample);
        return Result.succeed(sample,"保存成功");
    }

    /**
    *   通过id删除
    *
    * @param id
    * @return
    */
    @ApiOperation(value="示例-通过id删除", notes="示例-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        sampleService.removeById(id);
        return Result.succeed("删除成功!");
    }

    /**
    *  批量删除
    *
    * @param ids
    * @return
    */
    @ApiOperation(value="示例-批量删除", notes="示例-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.sampleService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.succeed("批量删除成功!");
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        sampleService.removeById(id);
        return Result.succeed("删除成功");
    }

    @ApiOperation(value = "导出excel")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/exportXlsx")
    public void exportXlsx(@RequestParam Map<String, Object> params, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取文件名
        String fileName = "示例" + DateUtil.formatDate(DateUtil.date());
        //获取数据
        List<Sample> data = sampleService.findAll(params);
        //导出
        ExcelHelper.exportToResponse(response,fileName,data,Sample.class);
    }


}
