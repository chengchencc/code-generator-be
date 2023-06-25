package com.ludan.sample.mapper;

import com.ludan.sample.entity.Main;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MainMapper extends SuperMapper<Main> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<Main> findList(Page<Main> page, @Param("p") Map<String, Object> params);

    List<Main> findList(@Param("p") Map<String, Object> params);
}
