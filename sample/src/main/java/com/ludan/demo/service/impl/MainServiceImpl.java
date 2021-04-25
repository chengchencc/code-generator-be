package com.ludan.demo.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.demo.entity.Main;
import com.ludan.demo.mapper.MainMapper;
import com.ludan.demo.service.MainService;

@Slf4j
@Service
public class MainServiceImpl extends SuperServiceImpl<MainMapper, Main> implements MainService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<Main> findAll(Map<String, Object> params){
        List<Main> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<Main> findList(Map<String, Object> params){
        Page<Main> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<Main> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
