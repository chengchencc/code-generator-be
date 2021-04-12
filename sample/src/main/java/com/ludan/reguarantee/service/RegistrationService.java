package com.ludan.reguarantee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.reguarantee.entity.Registration;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface RegistrationService extends ISuperService<Registration> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<Registration> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<Registration> findList(Map<String, Object> params);

}

