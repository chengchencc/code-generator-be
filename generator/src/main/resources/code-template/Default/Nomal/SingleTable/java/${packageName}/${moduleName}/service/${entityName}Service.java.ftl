package ${packageName}.${moduleName}.service;

import ${packageName}.${moduleName}.entity.${entityName};
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.Map;

<#--/**-->
<#-- * ${comments}-->
<#-- *-->
<#-- * @author ${author}-->
<#-- * @date ${datetime}-->
<#-- */-->
public interface ${entityName}Service extends ISuperService<${entityName}> {
    /**
     * 列表
     * @param params
     * @return
     */
    PageResult<${entityName}> findList(Map<String, Object> params);
}

