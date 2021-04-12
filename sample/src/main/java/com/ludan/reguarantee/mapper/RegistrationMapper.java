package com.ludan.reguarantee.mapper;

import com.ludan.reguarantee.entity.Registration;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegistrationMapper extends SuperMapper<Registration> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<Registration> findList(Page<Registration> page, @Param("p") Map<String, Object> params);

    List<Registration> findList(@Param("p") Map<String, Object> params);
}
