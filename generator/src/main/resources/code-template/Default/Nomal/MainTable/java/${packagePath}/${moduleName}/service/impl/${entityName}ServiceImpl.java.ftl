package ${packageName}.${moduleName}.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import ${packageName}.${moduleName}.entity.${entityName};
import ${packageName}.${moduleName}.mapper.${entityName}Mapper;
import ${packageName}.${moduleName}.service.${entityName}Service;

<#--/**-->
<#-- * ${comments}-->
<#-- *-->
<#-- * @author ${author}-->
<#-- * @date ${datetime}-->
<#-- */-->
@Slf4j
@Service
public class ${entityName}ServiceImpl extends SuperServiceImpl<${entityName}Mapper, ${entityName}> implements ${entityName}Service {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<${entityName}> findAll(Map<String, Object> params){
        List<${entityName}> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<${entityName}> findList(Map<String, Object> params){
        Page<${entityName}> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<${entityName}> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

}
