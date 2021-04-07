package ${packageName}.${moduleName}.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${packageName}.${moduleName}.entity.${entityName};
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

<#--/**-->
<#-- * ${comments}-->
<#-- *-->
<#-- * @author ${author}-->
<#-- * @date ${datetime}-->
<#-- */-->
public interface ${entityName}Service extends ISuperService<${entityName}> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<${entityName}> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<${entityName}> findList(Map<String, Object> params);

}

