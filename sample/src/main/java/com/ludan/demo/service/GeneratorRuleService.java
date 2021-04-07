package com.ludan.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.demo.entity.GeneratorRule;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface GeneratorRuleService extends ISuperService<GeneratorRule> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<GeneratorRule> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<GeneratorRule> findList(Map<String, Object> params);

}

