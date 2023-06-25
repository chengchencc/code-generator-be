
package com.ludan.demo.controller;

import java.io.UnsupportedEncodingException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;
import java.util.Arrays;
import java.util.Map;

import cn.hutool.core.date.DateUtil;
import com.central.common.utils.ExcelHelper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.SysDept;
import com.ludan.demo.service.SysDeptService;
import com.central.common.model.PageResult;
import com.central.common.model.Result;

import javax.servlet.http.HttpServletResponse;

/**
* @Description: 部门表
* @Author: admin
* @Date:   2021-05-08
* @Version: V1.0
*/
@Slf4j
@RestController
@RequestMapping("/SysDept")
@Api(tags = "部门表")
public class SysDeptController  {

// ----------------test---------------
// parentId
// ancestors
// deptId
// Ancestors
// -------------------------------

    @Autowired
    private SysDeptService sysDeptService;
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
        Page<SysDept> pageList = sysDeptService.findList(params);
        return PageResult.<SysDept>builder().data(pageList.getRecords()).code(0).count(pageList.getTotal()).build();
    }

    /**
    * 查询
    */
    @ApiOperation(value = "查询")
    @GetMapping("/detail/{id}")
    public Result findById(@PathVariable("id") String id) {
        SysDept model = sysDeptService.getById(id);
        return Result.succeed(model, "查询成功");
    }

    @ApiOperation(value = "查询根节点列表")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
    @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/root")
    public PageResult<SysDept> findRoot(@RequestParam Map<String, Object> params) {
        Page<SysDept> pageList = sysDeptService.findRoot(params);
        return new PageResult<SysDept>(pageList.getTotal(), 0, pageList.getRecords());
    }

    @ApiOperation(value = "查询子节点列表")
    @GetMapping("/children/{pid}")
    public Result<List<SysDept>> findChildren(@PathVariable("pid") String parentId) {
        List<SysDept> list = sysDeptService.findChildren(parentId);
        return Result.succeed(list);
    }


    /**
    *   添加
    *
    * @param sysDept
    * @return
    */
    @ApiOperation(value="部门表-添加", notes="部门表-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody SysDept sysDept) {
        if (ObjectUtils.isNotEmpty(sysDept.getParentId())) {
            SysDept parent = sysDeptService.getById(sysDept.getParentId());
            if (parent == null) {
                return Result.failed("无法找到父节点信息");
            }
            sysDept.setAncestors(parent.getAncestors() + "," + parent.getDeptId());
        }
        sysDeptService.save(sysDept);
        return Result.succeed(sysDept,"添加成功！");
    }

    /**
    *  编辑
    *
    * @param sysDept
    * @return
    */
    @ApiOperation(value="部门表-编辑", notes="部门表-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody SysDept sysDept) {
        if (ObjectUtils.isNotEmpty(sysDept.getParentId())) {
            SysDept parent = sysDeptService.getById(sysDept.getParentId());
            if (parent == null) {
                return Result.failed("无法找到父节点信息");
            }
            sysDept.setAncestors(parent.getAncestors() + "," + parent.getDeptId());
        }
        sysDeptService.updateById(sysDept);
        return Result.succeed(sysDept,"编辑成功!");
    }

    /**
    * 新增or更新
    */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody SysDept sysDept) {
        sysDeptService.saveOrUpdate(sysDept);
        return Result.succeed(sysDept,"保存成功");
    }

    /**
    *   通过id删除
    *
    * @param id
    * @return
    */
    @ApiOperation(value="部门表-通过id删除", notes="部门表-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) String id) {
        sysDeptService.removeItemAndChildren(id);
        return Result.succeed("删除成功!");
    }

    /**
    *  批量删除
    *
    * @param ids
    * @return
    */
    @ApiOperation(value="部门表-批量删除", notes="部门表-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.sysDeptService.removeByIds(Arrays.asList(ids.split(",")));
        return Result.succeed("批量删除成功!");
    }


    @ApiOperation(value = "导出excel")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/exportXlsx")
    public void exportXlsx(@RequestParam Map<String, Object> params, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取文件名
        String fileName = "部门表" + DateUtil.formatDate(DateUtil.date());
        //获取数据
        List<SysDept> data = sysDeptService.findAll(params);
        //导出
        ExcelHelper.exportToResponse(response,fileName,data,SysDept.class);
    }


}
