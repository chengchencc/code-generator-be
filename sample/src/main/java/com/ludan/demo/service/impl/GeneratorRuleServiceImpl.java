package com.ludan.demo.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.GeneratorRule;
import com.ludan.demo.mapper.GeneratorRuleMapper;
import com.ludan.demo.service.GeneratorRuleService;

@Slf4j
@Service
public class GeneratorRuleServiceImpl extends SuperServiceImpl<GeneratorRuleMapper, GeneratorRule> implements GeneratorRuleService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<GeneratorRule> findAll(Map<String, Object> params){
        List<GeneratorRule> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<GeneratorRule> findList(Map<String, Object> params){
        Page<GeneratorRule> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<GeneratorRule> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
