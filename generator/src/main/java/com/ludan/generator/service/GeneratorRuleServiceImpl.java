package com.ludan.generator.service;

import com.ludan.generator.common.domain.service.CrudAppServiceBase;
import com.ludan.generator.dto.GeneratorRuleDto;
import com.ludan.generator.entity.GeneratorRule;
import com.ludan.generator.repository.GeneratorRuleRepository;
import org.springframework.stereotype.Service;

/**
 * @author: chengchen
 * @create: 2021-04-14 11:47
 **/
@Service
public class GeneratorRuleServiceImpl extends CrudAppServiceBase<GeneratorRuleRepository, GeneratorRule, GeneratorRuleDto,Integer> implements GeneratorRuleService {

    public GeneratorRuleServiceImpl(GeneratorRuleRepository repository) {
        super(repository);
    }

    @Override
    protected Class<GeneratorRule> getEntityType() {
        return GeneratorRule.class;
    }

    @Override
    protected Class<GeneratorRuleDto> getDtoType() {
        return GeneratorRuleDto.class;
    }
}
