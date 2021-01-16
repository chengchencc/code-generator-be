package com.ludan.generator.service;

import com.ludan.generator.model.AppDataField;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.Map;

/**
 * 实体字段
 *
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
public interface IAppDataFieldService extends ISuperService<AppDataField> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<AppDataField> findList(Map<String, Object> params);
}

