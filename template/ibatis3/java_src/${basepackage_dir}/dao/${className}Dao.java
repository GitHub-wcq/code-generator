<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.repository;

import java.util.List;
import java.util.Map;
import ${basepackage}.entity.*;
import org.springframework.stereotype.Component;

@Component
public interface ${className}Dao{

	Integer insert(${className} entity);
	
	Integer update(${className} entity);
	
	Integer delete(${table.idColumn.javaType} id);
	
	${className} getById(${table.idColumn.javaType} id);

	int count();
	
	List<${className}> findPage(Map<String, Object> param);

}
