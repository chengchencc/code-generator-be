package com.ludan.generator.controller;

import com.ludan.generator.common.controller.CrudControllerBase;
import com.ludan.generator.dto.DataFieldDto;
import com.ludan.generator.dto.GeneratorRuleDto;
import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.service.DataFieldService;
import com.ludan.generator.service.GeneratorRuleService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chengchen
 * @create: 2021-04-14 11:40
 **/
@RestController
@RequestMapping("/generatorRule")
@Api(tags = "生成策略")
public class GeneratorRuleController extends CrudControllerBase<GeneratorRuleService, GeneratorRuleDto,Integer> {

    public GeneratorRuleController(GeneratorRuleService service) {
        super(service);
    }
}
