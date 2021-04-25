package com.ludan.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.demo.entity.ChildA;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface ChildAService extends ISuperService<ChildA> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<ChildA> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<ChildA> findList(Map<String, Object> params);

}

