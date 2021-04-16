package com.ludan.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.demo.entity.Sample;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface SampleService extends ISuperService<Sample> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<Sample> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<Sample> findList(Map<String, Object> params);

}

