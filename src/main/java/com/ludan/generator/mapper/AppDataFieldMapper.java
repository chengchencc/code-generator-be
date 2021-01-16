package com.ludan.generator.mapper;

import com.ludan.generator.model.DataField;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 实体字段
 * 
 * @author chengchen
 * @date 2021-01-11 21:43:15
 */
@Mapper
public interface AppDataFieldMapper extends SuperMapper<DataField> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    List<DataField> findList(Page<DataField> page, @Param("p") Map<String, Object> params);
}
