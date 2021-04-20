package com.ludan.generator.initialization;
import com.ludan.generator.entity.UITemplate;
import java.util.Date;

import com.ludan.generator.dto.GeneratorRuleDto;
import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.repository.GeneratorRuleRepository;
import com.ludan.generator.service.GeneratorRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: chengchen
 * @create: 2021-04-19 16:22
 **/
@Service
@Slf4j
public class SeedDataService implements ApplicationRunner {

    @Autowired
    GeneratorRuleService generatorRuleService;
    @Autowired
    GeneratorRuleRepository generatorRuleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("=======开始初始化数据========");

        init();

        log.info("=======数据初始化完成========");
    }

    private void init(){
        initGeneratorRule();
    }

    /**
     * 初始化生成规则
     */
    private void initGeneratorRule(){
        Optional<GeneratorRule> byId = generatorRuleRepository.findById(1);
        if (!byId.isPresent()){
            GeneratorRule generatorRule = new GeneratorRule();
            generatorRule.setRuleName("Default");
            generatorRule.setPackageName("com.ludan");
            generatorRule.setModuleName("demo");
            generatorRule.setAuthorName("admin");
            generatorRule.setEmail("admin@sdnydb.com");
            generatorRule.setUiTemplate(UITemplate.Default);
            generatorRule.setCreationTime(new Date());
            generatorRule.setLastModifyTime(new Date());
            generatorRule.setCreationUserId("admin");
            generatorRule.setLastModifyUserId("admin");
            generatorRule.setDeletionTime(new Date());
            generatorRule.setId(1);
            generatorRule.setTenantId("10000");
            generatorRuleRepository.save(generatorRule);
        }
    }

}
