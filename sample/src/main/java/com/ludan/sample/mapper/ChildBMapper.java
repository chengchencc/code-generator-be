package com.ludan.sample.mapper;

import com.ludan.sample.entity.ChildB;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChildBMapper extends SuperMapper<ChildB> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<ChildB> findList(Page<ChildB> page, @Param("p") Map<String, Object> params);

    List<ChildB> findList(@Param("p") Map<String, Object> params);
}
