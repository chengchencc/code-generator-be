
package com.ludan.viz.service.impl;

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

import com.ludan.viz.entity.SysDept;
import com.ludan.viz.mapper.SysDeptMapper;
import com.ludan.viz.service.SysDeptService;

@Slf4j
@Service
public class SysDeptServiceImpl extends SuperServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    /**
    * 获取全部
    * @param params
    * @return
    */
    @Override
    public List<SysDept> findAll(Map<String, Object> params){
        List<SysDept> all  =  baseMapper.findList(params);
        return all;
    }

    /**
    * 列表
    * @param params
    * @return
    */
    @Override
    public Page<SysDept> findList(Map<String, Object> params){
        Page<SysDept> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<SysDept> pageList  =  baseMapper.findList(page, params);
        return pageList;
    }

    /**
     * 获取根节点信息
     *
     * @param params 过滤信息
     * @return
     */
    @Override
    public Page<SysDept> findRoot(Map<String, Object> params) {
        Page<SysDept> page = new Page<>(MapUtils.getInteger(params, "pageNo"), MapUtils.getInteger(params, "pageSize"));
        Page<SysDept> pageList = baseMapper.findRoot(page, params);
        return pageList;
    }

    /**
     * 获取子节点
     *
     * @param parentId 父节点Id
     * @return
     */
    @Override
    public List<SysDept> findChildren(String parentId) {
        return this.lambdaQuery()
                .eq(SysDept::getParentId, parentId)
                .list();
    }

    /**
     * 获取所有父节点
     *
     * @param id
     * @return
     */
    @Override
    public List<SysDept> findAllParent(String id) {
        String parentId = findParentId(id);
        List<SysDept> allParent = baseMapper.findAllParent(parentId);
        return allParent;
    }

    /**
     * 获取直接父节点
     *
     * @param id
     * @return
     */
    @Override
    public SysDept findParent(String id) {
        String parentId = findParentId(id);
        if (ObjectUtils.isNotEmpty(parentId))
            return this.getById(parentId);
        return null;
    }

    /**
     * 新增根节点
     *
     * @param sysDept
     * @return
     */
    @Override
    public SysDept addRoot(SysDept sysDept) {
        assertNotEmpty(sysDept.getParentId(), "根节点不能包含父节点信息");
        boolean save = this.save(sysDept);
        return sysDept;
    }

    /**
     * 新增孩子节点
     *
     * @param sysDept
     * @return
     */
    @Override
    public SysDept addChildren(SysDept sysDept) {
        assertNotEmpty(sysDept.getParentId(), "子节点必须有一个父节点Id");

        SysDept parent = this.getById(sysDept.getParentId());
        assertNotEmpty(parent, "无法找到父节点信息");
        sysDept.setAncestors(parent.getAncestors() + "," + parent);

        this.save(sysDept);

        return sysDept;
    }

    /**
     * 更新根节点
     *
     * @param sysDept
     * @return
     */
    @Override
    public SysDept updateRoot(SysDept sysDept) {
        assertNotEmpty(sysDept.getParentId(), "子节点必须有一个父节点Id");

        this.updateById(sysDept);
        return sysDept;
    }

    /**
     * 更新孩子节点
     *
     * @param sysDept
     * @return
     */
    @Override
    public SysDept updateChildren(SysDept sysDept) {
        assertNotEmpty(sysDept.getParentId(),"子节点必须有一个父节点Id");
        SysDept parent = this.getById(sysDept.getParentId());
        assertNotEmpty(parent,"无法找到父节点信息");
        sysDept.setAncestors(parent.getAncestors() + "," + parent);

        this.updateById(sysDept);

        return sysDept;
    }

    public String findParentId(String id) {
        Optional<SysDept> sysDept = this.lambdaQuery().eq(SysDept::getDeptId, id).select(SysDept::getParentId).oneOpt();
        if (!sysDept.isPresent()) {
            throw new EntityNotFoundException();
        }
        return sysDept.get().getParentId();

    }

    /**
     * 删除当前Item 和 子节点
     *
     * @param id
     */
    @Transactional
    public void removeItemAndChildren(String id) {
        this.remove(new QueryWrapper<SysDept>().last(" and find_in_set('"+id+"',ancestors)"));
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
