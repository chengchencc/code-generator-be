package com.ludan.demo.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.Sample;
import com.ludan.demo.mapper.SampleMapper;
import com.ludan.demo.service.SampleService;

@Slf4j
@Service
public class SampleServiceImpl extends SuperServiceImpl<SampleMapper, Sample> implements SampleService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<Sample> findAll(Map<String, Object> params){
        List<Sample> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<Sample> findList(Map<String, Object> params){
        Page<Sample> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<Sample> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
