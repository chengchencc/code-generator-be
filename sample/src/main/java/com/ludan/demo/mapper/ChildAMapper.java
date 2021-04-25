package com.ludan.demo.mapper;

import com.ludan.demo.entity.ChildA;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChildAMapper extends SuperMapper<ChildA> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<ChildA> findList(Page<ChildA> page, @Param("p") Map<String, Object> params);

    List<ChildA> findList(@Param("p") Map<String, Object> params);
}
