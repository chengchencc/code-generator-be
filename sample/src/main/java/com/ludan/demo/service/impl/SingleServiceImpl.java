package com.ludan.demo.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.Single;
import com.ludan.demo.mapper.SingleMapper;
import com.ludan.demo.service.SingleService;

@Slf4j
@Service
public class SingleServiceImpl extends SuperServiceImpl<SingleMapper, Single> implements SingleService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<Single> findAll(Map<String, Object> params){
        List<Single> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<Single> findList(Map<String, Object> params){
        Page<Single> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<Single> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
