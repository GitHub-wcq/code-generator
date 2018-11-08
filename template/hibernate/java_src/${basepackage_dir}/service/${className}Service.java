<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>  
package ${basepackage}.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;

import ${basepackage}.entity.*;
import ${basepackage}.repository.*;

<#include "/java_imports.include">

@Component
@Transactional
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
	 * 通过Id查询对象
	 * @param id
	 * @return
	 */
	public ${className} get${className}(${pkJavaType} id){
		return ${classNameFirstLower}Dao.findOne(id);
	}

	/**
	 * 保存单个对象
	 * @param entity
	 */
	public void save(${className} entity){
		${classNameFirstLower}Dao.save(entity);
	}

	/**
	 * 根据Id删除单个对象
	 * @param id
	 */
	public void delete(${pkJavaType} id){
		${classNameFirstLower}Dao.delete(id);
	}

	/**
	 * 查询分页对象
	 * @param searchParams
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @return
	 */
	public Page<${className}> getUserPage(${pkJavaType} userId, Map<String,Object> searchParams, int pageNumber, int pageSize, String sortType){	
		PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, sortType);
		Specification<${className}> spec = buildSpecification(userId.longValue(), searchParams);
		return ${classNameFirstLower}Dao.findAll(spec, pageRequest);
	}

	/**
	 * 创建分页请求.
	 */
	private PageRequest buildPageRequest(int pageNumber, int pagzSize, String sortType) {
		Sort sort = null;
		if ("auto".equals(sortType)) {
			sort = new Sort(Direction.DESC, "id");
		} else if ("title".equals(sortType)) {
			sort = new Sort(Direction.ASC, "title");
		}

		return new PageRequest(pageNumber - 1, pagzSize, sort);
	}

	/**
	 * 创建动态查询条件组合.
	 */
	private Specification<${className}> buildSpecification(Long userId, Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		// filters.put("user.id", new SearchFilter("user.id", Operator.EQ, userId));
		Specification<${className}> spec = DynamicSpecifications.bySearchFilter(filters.values(), ${className}.class);
		return spec;
	}
	
}