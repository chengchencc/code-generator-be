package com.ludan.generator.service;

import com.ludan.generator.model.AppDataEntity;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 模型
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
public interface IAppDataEntityService extends ISuperService<AppDataEntity> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<AppDataEntity> findList(Map<String, Object> params);
}

