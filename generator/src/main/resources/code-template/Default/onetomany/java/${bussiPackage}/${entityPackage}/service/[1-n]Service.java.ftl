<#list subTables as subTab>
#segment#I${subTab.entityName}Service.java
package ${bussiPackage}.${entityPackage}.service;

import ${bussiPackage}.${entityPackage}.entity.${subTab.entityName};
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: ${subTab.ftlDescription}
 * @Author: jeecg-boot
 * @Date:   ${.now?string["yyyy-MM-dd"]}
 * @Version: V1.0
 */
public interface I${subTab.entityName}Service extends IService<${subTab.entityName}> {

	public List<${subTab.entityName}> selectByMainId(String mainId);
}
</#list>
