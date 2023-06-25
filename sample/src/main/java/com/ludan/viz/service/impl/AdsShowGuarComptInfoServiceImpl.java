package com.ludan.viz.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.viz.entity.AdsShowGuarComptInfo;
import com.ludan.viz.mapper.AdsShowGuarComptInfoMapper;
import com.ludan.viz.service.AdsShowGuarComptInfoService;

@Slf4j
@Service
public class AdsShowGuarComptInfoServiceImpl extends SuperServiceImpl<AdsShowGuarComptInfoMapper, AdsShowGuarComptInfo> implements AdsShowGuarComptInfoService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<AdsShowGuarComptInfo> findAll(Map<String, Object> params){
        List<AdsShowGuarComptInfo> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<AdsShowGuarComptInfo> findList(Map<String, Object> params){
        Page<AdsShowGuarComptInfo> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<AdsShowGuarComptInfo> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
