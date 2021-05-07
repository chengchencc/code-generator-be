<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#include "/common/utils.ftl">
<#include "/common/dataEntityUtils.ftl">
<#include "/common/treeDataEntityUtils.ftl">
<mapper namespace="${packageName}.${moduleName}.mapper.${entityName}Mapper">

    <sql id="commonFilter">
        <#list entity.fields as field >
            <#if field.dataFieldUI.query>
                <if test="p.${field.name} != null">
                    and t.${field.tableFieldName} = ${r'#'}{p.${field.name}}
                </if>
            </#if>
        </#list>
    </sql>

    <select id="findList" resultType="${packageName}.${moduleName}.entity.${entityName}">
        select * from ${entity.tableName} t
        where del_flag='0'
        <include refid="commonFilter"></include>
    </select>

    <select id="findRoot" resultType="${packageName}.${moduleName}.entity.${entityName}">
        select * from ${entity.tableName} t
        where t.${entity.parentIdField.tableFieldName} is null
        and del_flag='0'
        <include refid="commonFilter"></include>
    </select>

    <select id="findAllParent" resultType="${packageName}.${moduleName}.entity.${entityName}">
        select * from ${entity.tableName} t
        where del_flag='0' and find_in_set(${'#'}{parentId},${entity.treeIdPathField.tableFieldName})
    </select>

    <delete id="deleteChildren">
        <!-- 物理删除 -->
        <!-- delete from sys_dept where find_in_set(${'#'}{parentId},ancestors) -->
        <!-- 逻辑删除 -->
        update ${entity.tableName} set del_flag=1 where find_in_set(${'#'}{parentId},${entity.treeIdPathField.tableFieldName})
    </delete>
</mapper>
