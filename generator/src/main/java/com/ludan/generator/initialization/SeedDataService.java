package com.ludan.generator.initialization;

import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.service.GeneratorRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @author: chengchen
 * @create: 2021-04-19 16:22
 **/
@Service
@Slf4j
public class SeedDataService implements ApplicationRunner {

    @Autowired
    GeneratorRuleService generatorRuleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=======开始初始化数据========");

        init();

        log.info("=======数据初始化完成========");
    }

    private void init(){
//        generatorRuleService.findById()
    }

}
