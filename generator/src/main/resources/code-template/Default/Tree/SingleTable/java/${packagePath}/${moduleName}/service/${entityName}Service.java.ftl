<#include "/common/utils.ftl">
<#include "/common/dataEntityUtils.ftl">
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

    /**
    * 获取根节点
    * @param params 过滤信息
    * @return
    */
    Page<${entityName}> findRoot(Map<String, Object> params);

    /**
    * 获取子节点
    * @param parentId 父节点Id
    * @return
    */
    List<${entityName}> findChildren(${pk.dataFieldType.javaType} parentId);

    /**
    * 获取所有父节点
    * @param id
    * @return
    */
    List<${entityName}> findAllParent(${pk.dataFieldType.javaType} id);

    /**
    * 获取直接父节点
    * @param id
    * @return
    */
    ${entityName} findParent(${pk.dataFieldType.javaType} id);

    /**
    * 新增根节点
    * @param sysDept
    * @return
    */
    ${entityName} addRoot(${entityName} ${uncapEntityName});

    /**
    * 新增孩子节点
    * @param sysDept
    * @return
    */
    ${entityName} addChildren(${entityName} ${uncapEntityName});

    /**
    * 更新根节点
    * @param sysDept
    * @return
    */
    ${entityName} updateRoot(${entityName} ${uncapEntityName});

    /**
    * 更新孩子节点
    * @param sysDept
    * @return
    */
    ${entityName} updateChildren(${entityName} ${uncapEntityName});

    /**
    * 删除当前Item 和 子节点
    * @param id
    * @return
    */
    void removeItemAndChildren(${pk.dataFieldType.javaType} id);

}
