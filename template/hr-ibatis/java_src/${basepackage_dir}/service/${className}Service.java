<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>  
package ${basepackage}.service;

import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import ${basepackage}.entity.*;
import ${basepackage}.repository.*;

<#include "/java_imports.include">

@Service("${className}Service")
public class ${className}Service {
	/**
	 * Dao操作对象
	 */
	private ${className}Dao ${classNameFirstLower}Dao;
	
	/** 
	 * 注入Dao操作对象
	 */
	@Autowired
	public void set${className}Dao(${className}Dao ${classNameFirstLower}Dao) {
		this.${classNameFirstLower}Dao = ${classNameFirstLower}Dao;
	}

	/**
	 * 查询所有实体对象
	 */
	public List<${className}> getAll${className}() {
		return (List<${className}>) ${classNameFirstLower}Dao.findAll();
	}
	
	/**
	 * 查询实体列表页
	 * @param searchParams
	 * @param pageBounds
	 * @return
	 */
	public PageList<${className}> find${className}Page(Map<String, Object> searchParams,PageBounds pageBounds){
		return ${classNameFirstLower}Dao.findPage(searchParams, pageBounds);
	}
	/**
	 * 根据id查询实体信息
	 * @param id
	 * @return
	 */
	public ${className} get${className}(Long id) {
		return ${classNameFirstLower}Dao.getById(id);
	}
	
	/**
	 * 更新实体信息
	 * @param ${classNameFirstLower}
	 */
	public void update${className}(${className} ${classNameFirstLower}) {
		${classNameFirstLower}Dao.update(${classNameFirstLower});
	}
	
	/**
	 * 保存实体信息
	 * @param ${classNameFirstLower}
	 */
	public void insert${className}(${className} ${classNameFirstLower}) {
		${classNameFirstLower}Dao.insert(${classNameFirstLower});
	}
	
  /**
   * 查询实体数量
   * @param roleId
   * @return
   */
	public Long count${className}(Map<String, Object> param){
		return ${classNameFirstLower}Dao.count(param);
	}
	
}