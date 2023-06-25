package com.ludan.viz.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.viz.entity.AdsShowGuarComptInfo;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface AdsShowGuarComptInfoService extends ISuperService<AdsShowGuarComptInfo> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<AdsShowGuarComptInfo> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<AdsShowGuarComptInfo> findList(Map<String, Object> params);

}

