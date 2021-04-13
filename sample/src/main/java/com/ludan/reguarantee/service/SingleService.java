package com.ludan.reguarantee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.reguarantee.entity.Single;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface SingleService extends ISuperService<Single> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<Single> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<Single> findList(Map<String, Object> params);

}

