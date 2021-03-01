package ${packageName}.${moduleName}.controller;

import java.util.Map;

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

<#--rule:-->
<#--<#list rule?keys as propName>-->
<#--    ${propName} =-->
<#--</#list>-->

<#--entity:-->
<#--<#list entity?keys as propName>-->
<#--    ${propName} =-->
<#--</#list>-->

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
public class ${entityName}Controller {

@Autowired
private ${entityName}Service ${entityName}Service;

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
        return ${entityName}Service.findList(params);
    }

    /**
    * 查询
    */
    @ApiOperation(value = "查询")
    @GetMapping("/detail/{id}")
    public Result findUserById(@PathVariable Long id) {
        ${entityName} model = ${entityName}Service.getById(id);
        return Result.succeed(model, "查询成功");
    }

    /**
    * 新增or更新
    */
    @ApiOperation(value = "保存")
    @PostMapping
    public Result save(@RequestBody ${entityName} ${entityName}) {
        ${entityName}Service.saveOrUpdate(${entityName});
        return Result.succeed("保存成功");
    }

    /**
    * 删除
    */
    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        ${entityName}Service.removeById(id);
        return Result.succeed("删除成功");
    }
}
