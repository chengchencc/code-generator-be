package com.ludan.viz.mapper;

import com.ludan.viz.entity.AdsShowGuarComptInfo;
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdsShowGuarComptInfoMapper extends SuperMapper<AdsShowGuarComptInfo> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<AdsShowGuarComptInfo> findList(Page<AdsShowGuarComptInfo> page, @Param("p") Map<String, Object> params);

    List<AdsShowGuarComptInfo> findList(@Param("p") Map<String, Object> params);
}
