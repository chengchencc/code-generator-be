package com.ludan.generator.service;

import com.ludan.generator.dto.DataSourceDto;
import com.ludan.generator.dto.TableInfoDto;

import java.util.List;

/**
 * @author: chengchen
 * @create: 2021-01-28 16:44
 **/
public interface DataSourceService {
    /**
     * 获取表结构信息
     * @param dataSourceDto
     */
    public List<TableInfoDto> queryTableInfos(DataSourceDto dataSourceDto);

    /**
     * 逆向工程导入
     */
    public void importFromDb(DataSourceDto dataSourceDto);


}
