
package com.ludan.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ludan.demo.entity.SysDept;
import com.central.common.model.PageResult;
import com.central.common.service.ISuperService;

import java.util.List;
import java.util.Map;

public interface SysDeptService extends ISuperService<SysDept> {

    /**
    * 获取全部
    * @param params
    * @return
    */
    List<SysDept> findAll(Map<String, Object> params);

    /**
    * 分页列表
    * @param params
    * @return
    */
    Page<SysDept> findList(Map<String, Object> params);

    /**
    * 获取根节点
    * @param params 过滤信息
    * @return
    */
    Page<SysDept> findRoot(Map<String, Object> params);

    /**
    * 获取子节点
    * @param parentId 父节点Id
    * @return
    */
    List<SysDept> findChildren(String parentId);

    /**
    * 获取所有父节点
    * @param id
    * @return
    */
    List<SysDept> findAllParent(String id);

    /**
    * 获取直接父节点
    * @param id
    * @return
    */
    SysDept findParent(String id);

    /**
    * 新增根节点
    * @param sysDept
    * @return
    */
    SysDept addRoot(SysDept sysDept);

    /**
    * 新增孩子节点
    * @param sysDept
    * @return
    */
    SysDept addChildren(SysDept sysDept);

    /**
    * 更新根节点
    * @param sysDept
    * @return
    */
    SysDept updateRoot(SysDept sysDept);

    /**
    * 更新孩子节点
    * @param sysDept
    * @return
    */
    SysDept updateChildren(SysDept sysDept);

    /**
    * 删除当前Item 和 子节点
    * @param id
    * @return
    */
    void removeItemAndChildren(String id);

}
