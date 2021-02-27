package ${bussiPackage}.${entityPackage}.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import ${bussiPackage}.${entityPackage}.entity.${entityName};
import ${bussiPackage}.${entityPackage}.service.I${entityName}Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
<#assign bpm_flag=false>
<#list originalColumns as po>
<#if po.fieldDbName=='bpm_status'>
  <#assign bpm_flag=true>
</#if>
</#list>

 /**
 * @Description: ${tableVo.ftlDescription}
 * @Author: jeecg-boot
 * @Date:   ${.now?string["yyyy-MM-dd"]}
 * @Version: V1.0
 */
@Api(tags="${tableVo.ftlDescription}")
@RestController
@RequestMapping("/${entityPackage}/${entityName?uncap_first}")
@Slf4j
public class ${entityName}Controller extends JeecgController<${entityName}, I${entityName}Service> {
	@Autowired
	private I${entityName}Service ${entityName?uncap_first}Service;
	
	/**
	 * 分页列表查询
	 *
	 * @param ${entityName?uncap_first}
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "${tableVo.ftlDescription}-分页列表查询")
	@ApiOperation(value="${tableVo.ftlDescription}-分页列表查询", notes="${tableVo.ftlDescription}-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(${entityName} ${entityName?uncap_first},
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<${entityName}> queryWrapper = QueryGenerator.initQueryWrapper(${entityName?uncap_first}, req.getParameterMap());
		Page<${entityName}> page = new Page<${entityName}>(pageNo, pageSize);
		IPage<${entityName}> pageList = ${entityName?uncap_first}Service.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param ${entityName?uncap_first}
	 * @return
	 */
	@AutoLog(value = "${tableVo.ftlDescription}-添加")
	@ApiOperation(value="${tableVo.ftlDescription}-添加", notes="${tableVo.ftlDescription}-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ${entityName} ${entityName?uncap_first}) {
	    <#if bpm_flag>
        ${entityName?uncap_first}.setBpmStatus("1");
	    </#if>
		${entityName?uncap_first}Service.save(${entityName?uncap_first});
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param ${entityName?uncap_first}
	 * @return
	 */
	@AutoLog(value = "${tableVo.ftlDescription}-编辑")
	@ApiOperation(value="${tableVo.ftlDescription}-编辑", notes="${tableVo.ftlDescription}-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ${entityName} ${entityName?uncap_first}) {
		${entityName?uncap_first}Service.updateById(${entityName?uncap_first});
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "${tableVo.ftlDescription}-通过id删除")
	@ApiOperation(value="${tableVo.ftlDescription}-通过id删除", notes="${tableVo.ftlDescription}-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		${entityName?uncap_first}Service.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "${tableVo.ftlDescription}-批量删除")
	@ApiOperation(value="${tableVo.ftlDescription}-批量删除", notes="${tableVo.ftlDescription}-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.${entityName?uncap_first}Service.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "${tableVo.ftlDescription}-通过id查询")
	@ApiOperation(value="${tableVo.ftlDescription}-通过id查询", notes="${tableVo.ftlDescription}-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		${entityName} ${entityName?uncap_first} = ${entityName?uncap_first}Service.getById(id);
		if(${entityName?uncap_first}==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(${entityName?uncap_first});
	}

    /**
    * 导出excel
    *
    * @param request
    * @param ${entityName?uncap_first}
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ${entityName} ${entityName?uncap_first}) {
        return super.exportXls(request, ${entityName?uncap_first}, ${entityName}.class, "${tableVo.ftlDescription}");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ${entityName}.class);
    }

}
