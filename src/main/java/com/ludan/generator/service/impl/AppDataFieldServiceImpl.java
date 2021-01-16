package com.ludan.generator.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.generator.model.DataField;
import com.ludan.generator.mapper.AppDataFieldMapper;
import com.ludan.generator.service.IAppDataFieldService;

/**
 * 实体字段
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
@Slf4j
@Service
public class AppDataFieldServiceImpl extends SuperServiceImpl<AppDataFieldMapper, DataField> implements IAppDataFieldService {
    /**
     * 列表
     * @param params
     * @return
     */
    @Override
    public PageResult<DataField> findList(Map<String, Object> params){
        Page<DataField> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        List<DataField> list  =  baseMapper.findList(page, params);
        return PageResult.<DataField>builder().data(list).code(0).count(page.getTotal()).build();
    }
}
