package com.ludan.reguarantee.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.ludan.reguarantee.entity.Registration;
import com.ludan.reguarantee.mapper.RegistrationMapper;
import com.ludan.reguarantee.service.RegistrationService;

@Slf4j
@Service
public class RegistrationServiceImpl extends SuperServiceImpl<RegistrationMapper, Registration> implements RegistrationService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<Registration> findAll(Map<String, Object> params){
        List<Registration> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<Registration> findList(Map<String, Object> params){
        Page<Registration> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<Registration> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
