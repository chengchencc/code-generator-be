package com.ludan.common.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.central.common.model.Result;
import com.ludan.common.annotation.ExcelDefine;
import com.ludan.common.exception.BusinessException;
import com.ludan.common.remote.SystemDictionaryFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @author: chengchen
 * @create: 2021-01-18 15:32
 **/
@Slf4j
public class ExcelHelper {

    public static void writeToResponse(HttpServletResponse response, String fileName, ExcelWriter writer) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        response.setCharacterEncoding("UTF-8");
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            writer.flush(outputStream);
            outputStream.close();
        } catch (IOException e) {
            log.error("导出Excel出错!", e);
            throw new BusinessException("导出Excel出错！", e);
        }finally {
            writer.close();
        }
    }

    public static <T> void exportToResponse(HttpServletResponse response, String fileName, List<T> data, Class<T> entityType) throws UnsupportedEncodingException{
        exportToResponse(response,fileName,data,entityType,null);
    }

    /**
     * 支持自定义转换器
     * @param response
     * @param fileName
     * @param data
     * @param entityType
     * @param converter
     * @param <T>
     * @throws UnsupportedEncodingException
     */
    public static <T> void exportToResponse(HttpServletResponse response, String fileName, List<T> data, Class<T> entityType, BiConsumer<List<T>,Map<String,ExcelDefine>> converter) throws UnsupportedEncodingException {
        ExcelWriter excelWriter = new ExcelWriter(true);
        //字典转换
        Field[] fields = entityType.getDeclaredFields();
        List<ExcelDefine> excelDefines = new ArrayList<>();
        Map<String,ExcelDefine> excelDefineMap = new HashMap<>();
        for (Field field : fields) {
            ExcelDefine excelDefine = field.getAnnotation(ExcelDefine.class);
            if (ObjectUtils.isNotEmpty(excelDefine)) {
                //设置表头
                excelWriter.addHeaderAlias(field.getName(), excelDefine.columnName());
                excelDefines.add(excelDefine);
                excelDefineMap.put(field.getName(),excelDefine);
            }
        }

        if (converter == null){
            mapDictValues(data,excelDefineMap);
        }else {
            converter.accept(data,excelDefineMap);
        }

        excelWriter.setOnlyAlias(true); // 只导出配置过的headeralias中的内容
        excelWriter.write(data);

        // 设置列宽度
        for (int i = 0; i < excelWriter.getColumnCount(); i++) {
            //自动列宽
            excelWriter.setColumnWidth(i,20);
        }

        writeToResponse(response, fileName, excelWriter);
    }

    private static  <T> List<T> mapDictValues(List<T> data, Map<String,ExcelDefine> excelDefineMap) {
        List<String> dictCodes = new ArrayList<>();
        Map<String,ExcelDefine> needToMappedField = new HashMap<>();

        excelDefineMap.forEach((s, excelDefine) -> {
            if (StringUtils.isNotBlank(excelDefine.dictCode())){
                dictCodes.add(excelDefine.dictCode());
                needToMappedField.put(s,excelDefine);
            }
        });

        SystemDictionaryFeignClient systemDictionaryFeignClient = SpringContextHolder.getBean(SystemDictionaryFeignClient.class);

        Result<List<Map<String, Object>>> result = systemDictionaryFeignClient.findByDictCodes(dictCodes);
        Map<String,Map<String,Map<String,Object>>> codeValue = new HashMap<>();
        for (Map<String, Object> item : result.getDatas()) {
            String dictCode = MapUtils.getString(item, "dictCode");
            String code = MapUtils.getString(item, "code");
            if (codeValue.get(dictCode) == null){
                HashMap<String, Map<String, Object>> map = new HashMap<>();
                map.put(code,item);
                codeValue.put(dictCode, map);
            }else {
                codeValue.get(dictCode).put(code,item);
            }
        }
        data.forEach(item -> {
            needToMappedField.keySet().forEach(fieldName->{
                Object fieldValue = ReflectUtil.getFieldValue(item, fieldName);
                if (ObjectUtils.isNotEmpty(fieldName)&&ObjectUtils.isNotEmpty(fieldValue)){
                    if (ObjectUtils.isNotEmpty(needToMappedField.get(fieldName).dictCode())
                            && ObjectUtils.isNotEmpty(codeValue.get(needToMappedField.get(fieldName).dictCode()))
                            && ObjectUtils.isNotEmpty(codeValue.get(needToMappedField.get(fieldName).dictCode()).get(fieldValue.toString())) ){
                        Object value =codeValue.get(needToMappedField.get(fieldName).dictCode()).get(fieldValue.toString()) .get("value");
                        ReflectUtil.setFieldValue(item,fieldName,value);
                    }

                }
            });
        });
        return data;
    }
}
