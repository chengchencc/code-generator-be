package com.ludan.generator.controller;

import com.ludan.generator.generate.CodeGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: chengchen
 * @create: 2021-03-01 16:40
 **/
@Api(tags = "代码生成")
@RestController
@RequestMapping("/generator")
public class CodeGeneratorController {

    @Autowired
    CodeGenerator codeGenerator;

    @GetMapping("/exportToZip")
    public void ExportToZip(Integer entityId, HttpServletResponse response){
        codeGenerator.generateToResponse(entityId,response);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.setCharacterEncoding("UTF-8");
    }

}
