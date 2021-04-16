package com.ludan.reguarantee.mapper;

import com.ludan.reguarantee.entity.Sample;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SampleMapper extends SuperMapper<Sample> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<Sample> findList(Page<Sample> page, @Param("p") Map<String, Object> params);

    List<Sample> findList(@Param("p") Map<String, Object> params);
}
