<#include "/common/utils.ftl">
<#include "/common/dataEntityUtils.ftl">
<#include "/common/treeDataEntityUtils.ftl">
package ${packageName}.${moduleName}.service.impl;

import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.central.common.exception.BusinessException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import com.central.common.model.PageResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.central.common.service.impl.SuperServiceImpl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.MapUtils;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

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

    /**
     * 获取根节点信息
     *
     * @param params 过滤信息
     * @return
     */
    @Override
    public Page<${entityName}> findRoot(Map<String, Object> params) {
        Page<${entityName}> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<${entityName}> pageList = baseMapper.findRoot(page, params);
        return pageList;
    }

    /**
     * 获取子节点
     *
     * @param parentId 父节点Id
     * @return
     */
    @Override
    public List<${entityName}> findChildren(${pk.dataFieldType.javaType} parentId) {
        return this.lambdaQuery()
                .eq(${entityName}::get${capParentIdFieldName}, parentId)
                .list();
    }

    /**
     * 获取所有父节点
     *
     * @param id
     * @return
     */
    @Override
    public List<${entityName}> findAllParent(${pk.dataFieldType.javaType} id) {
        String parentId = findParentId(id);
        List<${entityName}> allParent = baseMapper.findAllParent(parentId);
        return allParent;
    }

    /**
     * 获取直接父节点
     *
     * @param id
     * @return
     */
    @Override
    public ${entityName} findParent(${pk.dataFieldType.javaType} id) {
        String parentId = findParentId(id);
        if (ObjectUtils.isNotEmpty(parentId))
            return this.getById(parentId);
        return null;
    }

    /**
     * 新增根节点
     *
     * @param ${uncapEntityName}
     * @return
     */
    @Override
    public ${entityName} addRoot(${entityName} ${uncapEntityName}) {
        assertNotEmpty(${uncapEntityName}.get${capParentIdFieldName}(), "根节点不能包含父节点信息");
        boolean save = this.save(${uncapEntityName});
        return ${uncapEntityName};
    }

    /**
     * 新增孩子节点
     *
     * @param ${uncapEntityName}
     * @return
     */
    @Override
    public ${entityName} addChildren(${entityName} ${uncapEntityName}) {
        assertNotEmpty(${uncapEntityName}.get${capParentIdFieldName}(), "子节点必须有一个父节点Id");

        ${entityName} parent = this.getById(${uncapEntityName}.get${capParentIdFieldName}());
        assertNotEmpty(parent, "无法找到父节点信息");
        ${uncapEntityName}.set${capTreePathFieldName}(parent.get${capTreePathFieldName}() + "," + parent);

        this.save(${uncapEntityName});

        return ${uncapEntityName};
    }

    /**
     * 更新根节点
     *
     * @param ${uncapEntityName}
     * @return
     */
    @Override
    public ${entityName} updateRoot(${entityName} ${uncapEntityName}) {
        assertNotEmpty(${uncapEntityName}.get${capParentIdFieldName}(), "子节点必须有一个父节点Id");

        this.updateById(${uncapEntityName});
        return ${uncapEntityName};
    }

    /**
     * 更新孩子节点
     *
     * @param ${uncapEntityName}
     * @return
     */
    @Override
    public ${entityName} updateChildren(${entityName} ${uncapEntityName}) {
        assertNotEmpty(${uncapEntityName}.get${capParentIdFieldName}(),"子节点必须有一个父节点Id");
        ${entityName} parent = this.getById(${uncapEntityName}.get${capParentIdFieldName}());
        assertNotEmpty(parent,"无法找到父节点信息");
        ${uncapEntityName}.set${capTreePathFieldName}(parent.get${capTreePathFieldName}() + "," + parent);

        this.updateById(${uncapEntityName});

        return ${uncapEntityName};
    }

    public String findParentId(${pk.dataFieldType.javaType} id) {
        Optional<${entityName}> ${uncapEntityName} = this.lambdaQuery().eq(${entityName}::getDeptId, id).select(${entityName}::get${capParentIdFieldName}).oneOpt();
        if (!${uncapEntityName}.isPresent()) {
            throw new EntityNotFoundException();
        }
        return ${uncapEntityName}.get().get${capParentIdFieldName}();

    }

    /**
     * 删除当前Item 和 子节点
     *
     * @param id
     */
    @Transactional
    public void removeItemAndChildren(${pk.dataFieldType.javaType} id) {
        this.remove(new QueryWrapper<${entityName}>().last(" and find_in_set('"+id+"',ancestors)"));
        this.removeById(id);
    }

    /**
     * <p>Checks if an Object is empty or null.</p>
     *
     * The following types are supported:
     * <ul>
     * <li>{@link CharSequence}: Considered empty if its length is zero.</li>
     * <li>{@code Array}: Considered empty if its length is zero.</li>
     * <li>{@link Collection}: Considered empty if it has zero elements.</li>
     * <li>{@link Map}: Considered empty if it has zero key-value mappings.</li>
     * </ul>
     *
     * <pre>
     * ObjectUtils.isEmpty(null)             = true
     * ObjectUtils.isEmpty("")               = true
     * ObjectUtils.isEmpty("ab")             = false
     * ObjectUtils.isEmpty(new int[]{})      = true
     * ObjectUtils.isEmpty(new int[]{1,2,3}) = false
     * ObjectUtils.isEmpty(1234)             = false
     * </pre>
     *
     * @param msg 异常信息
     * @param obj  the {@code Object} to test, may be {@code null}
     * @return {@link BusinessException}
     * @since
     */
    private void assertNotEmpty(Object obj, String msg) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new BusinessException(msg);
        }
    }


}
