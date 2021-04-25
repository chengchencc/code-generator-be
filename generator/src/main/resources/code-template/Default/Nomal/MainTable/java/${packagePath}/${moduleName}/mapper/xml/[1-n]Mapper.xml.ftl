<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.${moduleName}.mapper.${entityName}Mapper">
    <select id="findList" resultType="${packageName}.${moduleName}.entity.${entityName}">
        select * from ${entity.tableName} t
        where 1=1
        <#list entity.fields as field >
            <#if field.dataFieldUI.query>
           <if test="p.${field.name} != null">
               t.${field.tableFieldName} = ${r'#'}{p.${field.name}}
           </if>
            </#if>
        </#list>
    </select>
</mapper>
