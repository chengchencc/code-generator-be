<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${packageName}.${moduleName}.mapper.${entityName}Mapper">
    <select id="findList" resultType="${packageName}.${moduleName}.entity.${entityName}">
        select * from ${entityName} t
    </select>
</mapper>
