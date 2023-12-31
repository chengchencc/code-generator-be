package com.ludan.common.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.extension.service.IService;
import com.central.common.constant.SecurityConstants;
import com.central.common.model.Result;
import com.ludan.common.utils.RequestHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author: chengchen
 * @create: 2021-03-17 19:45
 **/
@Slf4j
public abstract class LudanControllerBase<T, S extends IService<T>> {

    @Autowired
    protected S service;

    protected String getUserId(){
        return RequestHelper.getHttpServletRequest().getHeader(SecurityConstants.USER_ID_HEADER);
    }

    /**
     * 导出excel
     *
     * @param request
     */
    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title) {
//        // Step.1 组装查询条件
//        QueryWrapper<T> queryWrapper = QueryGenerator.initQueryWrapper(object, request.getParameterMap());
//        LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
//
//        // Step.2 获取导出数据
//        List<T> pageList = service.list(queryWrapper);
//        List<T> exportList = null;
//
//        // 过滤选中数据
//        String selections = request.getParameter("selections");
//        if (oConvertUtils.isNotEmpty(selections)) {
//            List<String> selectionList = Arrays.asList(selections.split(","));
//            exportList = pageList.stream().filter(item -> selectionList.contains(getId(item))).collect(Collectors.toList());
//        } else {
//            exportList = pageList;
//        }
//
//        // Step.3 AutoPoi 导出Excel
//        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
//        mv.addObject(NormalExcelConstants.FILE_NAME, title); //此处设置的filename无效 ,前端会重更新设置一下
//        mv.addObject(NormalExcelConstants.CLASS, clazz);
//        mv.addObject(NormalExcelConstants.PARAMS, new ExportParams(title + "报表", "导出人:" + sysUser.getRealname(), title));
//        mv.addObject(NormalExcelConstants.DATA_LIST, exportList);
//        return mv;
        return null;
    }

    /**
     * 根据权限导出excel，传入导出字段参数
     *
     * @param request
     */
    protected ModelAndView exportXls(HttpServletRequest request, T object, Class<T> clazz, String title, String exportFields) {
//        ModelAndView mv = this.exportXls(request,object,clazz,title);
//        mv.addObject(NormalExcelConstants.EXPORT_FIELDS,exportFields);
//        return mv;
        return null;
    }

    /**
     * 获取对象ID
     *
     * @return
     */
    private String getId(T item) {
        try {
            return PropertyUtils.getProperty(item, "id").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过excel导入数据
     *
     * @param request
     * @param response
     * @return
     */
    protected Result<?> importExcel(HttpServletRequest request, HttpServletResponse response, Class<T> clazz) {
        return null;
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
//            MultipartFile file = entity.getValue();// 获取上传文件对象
//            ImportParams params = new ImportParams();
//            params.setTitleRows(2);
//            params.setHeadRows(1);
//            params.setNeedSave(true);
//            try {
//                List<T> list = ExcelImportUtil.importExcel(file.getInputStream(), clazz, params);
//                //update-begin-author:taoyan date:20190528 for:批量插入数据
//                long start = System.currentTimeMillis();
//                service.saveBatch(list);
//                //400条 saveBatch消耗时间1592毫秒  循环插入消耗时间1947毫秒
//                //1200条  saveBatch消耗时间3687毫秒 循环插入消耗时间5212毫秒
//                log.info("消耗时间" + (System.currentTimeMillis() - start) + "毫秒");
//                //update-end-author:taoyan date:20190528 for:批量插入数据
//                return Result.succeed("文件导入成功！数据行数：" + list.size());
//            } catch (Exception e) {
//                log.error(e.getMessage(), e);
//                return Result.error("文件导入失败:" + e.getMessage());
//            } finally {
//                try {
//                    file.getInputStream().close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return Result.error("文件导入失败！");
    }


}
