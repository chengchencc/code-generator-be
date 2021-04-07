package com.ludan.common.utils;

import cn.hutool.poi.excel.ExcelWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author: chengchen
 * @create: 2021-01-18 15:38
 **/
@Slf4j
public class ResponseHelper {

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static void writeExcel(HttpServletResponse response, String fileName, ExcelWriter writer) throws UnsupportedEncodingException {
        ExcelHelper.writeToResponse(response, fileName, writer);
    }
}
