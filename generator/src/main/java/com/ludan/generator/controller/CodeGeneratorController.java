package com.ludan.generator.controller;

import com.ludan.generator.common.domain.dto.PagedResultDto;
import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.generate.CodeGenerator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

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

    @PostMapping("/exportBatchToZip")
    public void exportBatchToZip(@RequestBody Integer[] entityIds, HttpServletResponse response){
        codeGenerator.generateToResponse(entityIds,response);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"generator.zip\"");
        response.setCharacterEncoding("UTF-8");
    }

    @GetMapping("/generatorRule")
    public PagedResultDto<GeneratorRule> findGeneratorRule(){
        PagedResultDto<GeneratorRule> resultDto = new PagedResultDto<>(1, Arrays.asList(GeneratorRule.getDefault()));
        return resultDto;
    }

}
