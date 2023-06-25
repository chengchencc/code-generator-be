package com.ludan.sample.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.sample.entity.ChildB;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface ChildBService extends ISuperService<ChildB> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<ChildB> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<ChildB> findList(Map<String, Object> params);

}

