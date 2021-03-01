package com.ludan.demo.service;

import com.ludan.demo.entity.Single;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.Map;

public interface SingleService extends ISuperService<Single> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<Single> findList(Map<String, Object> params);
}

