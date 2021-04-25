package ${packageName}.${moduleName}.mapper;

import ${packageName}.${moduleName}.entity.${entityName};
import com.central.db.mapper.SuperMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

<#--/**-->
<#-- * ${comments}-->
<#-- * -->
<#-- * @author ${author}-->
<#-- * @date ${datetime}-->
<#-- */-->
@Mapper
public interface ${entityName}Mapper extends SuperMapper<${entityName}> {
    /**
     * 分页查询用户列表
     * @param page
     * @param params
     * @return
     */
    Page<${entityName}> findList(Page<${entityName}> page, @Param("p") Map<String, Object> params);

    List<${entityName}> findList(@Param("p") Map<String, Object> params);
}
