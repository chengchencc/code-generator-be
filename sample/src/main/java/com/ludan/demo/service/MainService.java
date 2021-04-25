package com.ludan.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.demo.entity.Main;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface MainService extends ISuperService<Main> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<Main> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<Main> findList(Map<String, Object> params);

}

