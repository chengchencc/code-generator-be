<#include "/common/utils.ftl">
<#include "/common/dataEntityUtils.ftl">
<#include "/common/treeDataEntityUtils.ftl">
package ${packageName}.${moduleName}.controller;

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

import ${packageName}.${moduleName}.entity.${entityName};
import ${packageName}.${moduleName}.service.${entityName}Service;
import com.central.common.model.PageResult;
import com.central.common.model.Result;

import javax.servlet.http.HttpServletResponse;

/**
* @Description: ${entity.description}
* @Author: ${rule.authorName}
* @Date:   ${.now?string["yyyy-MM-dd"]}
* @Version: V1.0
*/
@Slf4j
@RestController
@RequestMapping("/${entityName}")
@Api(tags = "${entity.description}")
public class ${entityName}Controller  {

// ----------------test---------------
// ${entity.parentIdField.name}
// ${entity.treeIdPathField.name}
// ${pk.name}
// ${capTreePathFieldName}
// -------------------------------

    @Autowired
    private ${entityName}Service ${uncapEntityName}Service;
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
        Page<${entityName}> pageList = ${uncapEntityName}Service.findList(params);
        return PageResult.<${entityName}>builder().data(pageList.getRecords()).code(0).count(pageList.getTotal()).build();
    }

    /**
    * 查询
    */
    @ApiOperation(value = "查询")
    @GetMapping("/detail/{id}")
    public Result findById(@PathVariable("id") ${pk.dataFieldType.javaType} id) {
        ${entityName} model = ${uncapEntityName}Service.getById(id);
        return Result.succeed(model, "查询成功");
    }

    @ApiOperation(value = "查询根节点列表")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
    @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/root")
    public PageResult<${entityName}> findRoot(@RequestParam Map<String, Object> params) {
        Page<${entityName}> pageList = ${uncapEntityName}Service.findRoot(params);
        return new PageResult<${entityName}>(pageList.getTotal(), 0, pageList.getRecords());
    }

    @ApiOperation(value = "查询子节点列表")
    @GetMapping("/children/{pid}")
    public Result<List<${entityName}>> findChildren(@PathVariable("pid") ${pk.dataFieldType.javaType} parentId) {
        List<${entityName}> list = ${uncapEntityName}Service.findChildren(parentId);
        return Result.succeed(list);
    }


    /**
    *   添加
    *
    * @param ${uncapEntityName}
    * @return
    */
<#--    @AutoLog(value = "${entity.description}-添加")-->
    @ApiOperation(value="${entity.description}-添加", notes="${entity.description}-添加")
    @PostMapping(value = "/add")
    public Result<?> add(@RequestBody ${entityName} ${uncapEntityName}) {
        if (ObjectUtils.isNotEmpty(${uncapEntityName}.get${capParentIdFieldName}())) {
            ${entityName} parent = ${uncapEntityName}Service.getById(${uncapEntityName}.get${capParentIdFieldName}());
            if (parent == null) {
                return Result.failed("无法找到父节点信息");
            }
            ${uncapEntityName}.set${capTreePathFieldName}(parent.get${capTreePathFieldName}() + "," + parent.get${capPkName}());
        }
<#--        <#if bpm_flag>-->
<#--            ${uncapEntityName}.setBpmStatus("1");-->
<#--        </#if>-->
        ${uncapEntityName}Service.save(${uncapEntityName});
        return Result.succeed(${uncapEntityName},"添加成功！");
    }

    /**
    *  编辑
    *
    * @param ${uncapEntityName}
    * @return
    */
    @ApiOperation(value="${entity.description}-编辑", notes="${entity.description}-编辑")
    @PutMapping(value = "/edit")
    public Result<?> edit(@RequestBody ${entityName} ${uncapEntityName}) {
        if (ObjectUtils.isNotEmpty(${uncapEntityName}.get${capParentIdFieldName}())) {
            ${entityName} parent = ${uncapEntityName}Service.getById(${uncapEntityName}.get${capParentIdFieldName}());
            if (parent == null) {
                return Result.failed("无法找到父节点信息");
            }
            ${uncapEntityName}.set${capTreePathFieldName}(parent.get${capTreePathFieldName}() + "," + parent.get${capPkName}());
        }
<#--        <#if bpm_flag>-->
<#--            ${uncapEntityName}.setBpmStatus("1");-->
<#--        </#if>-->
        ${uncapEntityName}Service.updateById(${uncapEntityName});
        return Result.succeed(${uncapEntityName},"编辑成功!");
    }

    /**
    * 新增or更新
    */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody ${entityName} ${uncapEntityName}) {
        ${uncapEntityName}Service.saveOrUpdate(${uncapEntityName});
        return Result.succeed(${uncapEntityName},"保存成功");
    }

    /**
    *   通过id删除
    *
    * @param id
    * @return
    */
    @ApiOperation(value="${entity.description}-通过id删除", notes="${entity.description}-通过id删除")
    @DeleteMapping(value = "/delete")
    public Result<?> delete(@RequestParam(name="id",required=true) ${pk.dataFieldType.javaType} id) {
        ${uncapEntityName}Service.removeItemAndChildren(id);
        return Result.succeed("删除成功!");
    }

    /**
    *  批量删除
    *
    * @param ids
    * @return
    */
    @ApiOperation(value="${entity.description}-批量删除", notes="${entity.description}-批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
        this.${uncapEntityName}Service.removeByIds(Arrays.asList(ids.split(",")));
        return Result.succeed("批量删除成功!");
    }

<#--    /**-->
<#--    * 删除-->
<#--    */-->
<#--    @ApiOperation(value = "删除")-->
<#--    @DeleteMapping("/{id}")-->
<#--    public Result delete(@PathVariable Long id) {-->
<#--        ${uncapEntityName}Service.removeById(id);-->
<#--        return Result.succeed("删除成功");-->
<#--    }-->

    @ApiOperation(value = "导出excel")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNo", value = "分页起始位置", required = true, dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", value = "分页结束位置", required = true, dataType = "Integer")
    })
    @GetMapping("/exportXlsx")
    public void exportXlsx(@RequestParam Map<String, Object> params, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取文件名
        String fileName = "${entity.description}" + DateUtil.formatDate(DateUtil.date());
        //获取数据
        List<${entityName}> data = ${uncapEntityName}Service.findAll(params);
        //导出
        ExcelHelper.exportToResponse(response,fileName,data,${entityName}.class);
    }

<#--    /**-->
<#--    * 导出excel-->
<#--    *-->
<#--    * @param request-->
<#--    * @param ${uncapEntityName}-->
<#--    */-->
<#--    @RequestMapping(value = "/exportXls")-->
<#--    public ModelAndView exportXls(HttpServletRequest request, ${entityName} ${uncapEntityName}) {-->
<#--        return null;-->
<#--        return super.exportXls(request, ${uncapEntityName}, ${entityName}.class, "${tableVo.ftlDescription}");-->
<#--    }-->

<#--    /**-->
<#--    * 通过excel导入数据-->
<#--    *-->
<#--    * @param request-->
<#--    * @param response-->
<#--    * @return-->
<#--    */-->
<#--    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)-->
<#--    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {-->
<#--        return null;-->
<#--        return super.importExcel(request, response, ${entityName}.class);-->
<#--    }-->

}
