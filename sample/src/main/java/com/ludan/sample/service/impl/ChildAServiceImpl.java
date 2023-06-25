package com.ludan.sample.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.sample.entity.ChildA;
import com.ludan.sample.mapper.ChildAMapper;
import com.ludan.sample.service.ChildAService;

@Slf4j
@Service
public class ChildAServiceImpl extends SuperServiceImpl<ChildAMapper, ChildA> implements ChildAService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<ChildA> findAll(Map<String, Object> params){
        List<ChildA> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<ChildA> findList(Map<String, Object> params){
        Page<ChildA> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<ChildA> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
