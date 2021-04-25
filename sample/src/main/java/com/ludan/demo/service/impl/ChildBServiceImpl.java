package com.ludan.demo.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.ChildB;
import com.ludan.demo.mapper.ChildBMapper;
import com.ludan.demo.service.ChildBService;

@Slf4j
@Service
public class ChildBServiceImpl extends SuperServiceImpl<ChildBMapper, ChildB> implements ChildBService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<ChildB> findAll(Map<String, Object> params){
        List<ChildB> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<ChildB> findList(Map<String, Object> params){
        Page<ChildB> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<ChildB> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
