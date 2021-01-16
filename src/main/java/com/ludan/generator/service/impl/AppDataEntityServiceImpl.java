package com.ludan.generator.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.generator.model.AppDataEntity;
import com.ludan.generator.mapper.AppDataEntityMapper;
import com.ludan.generator.service.IAppDataEntityService;

/**
 * 模型
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
@Slf4j
@Service
public class AppDataEntityServiceImpl extends SuperServiceImpl<AppDataEntityMapper, AppDataEntity> implements IAppDataEntityService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<AppDataEntity> findList(Map<String, Object> params){
        Page<AppDataEntity> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        List<AppDataEntity> list  =  baseMapper.findList(page, params);
        return PageResult.<AppDataEntity>builder().data(list).code(0).count(page.getTotal()).build();
    }
}
