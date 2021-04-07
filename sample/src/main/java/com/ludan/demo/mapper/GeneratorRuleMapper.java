package com.ludan.demo.mapper;

import com.ludan.demo.entity.GeneratorRule;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GeneratorRuleMapper extends SuperMapper<GeneratorRule> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<GeneratorRule> findList(Page<GeneratorRule> page, @Param("p") Map<String, Object> params);

    List<GeneratorRule> findList(@Param("p") Map<String, Object> params);
}
