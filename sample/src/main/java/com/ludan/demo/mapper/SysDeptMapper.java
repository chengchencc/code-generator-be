
package com.ludan.demo.mapper;

import com.ludan.demo.entity.SysDept;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysDeptMapper extends SuperMapper<SysDept> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<SysDept> findList(Page<SysDept> page, @Param("p") Map<String, Object> params);

    List<SysDept> findList(@Param("p") Map<String, Object> params);

    Page<SysDept> findRoot(Page<SysDept> page, @Param("p") Map<String, Object> params);

    List<SysDept> findAllParent(@Param("parentId") String parentId);

    void deleteChildren(@Param("parentId") String parentId);

}
