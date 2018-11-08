<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

<#include "/java_imports.include">

import org.springframework.stereotype.Repository;

@Repository
public interface ${className}Dao {
	
	/**
	 * ���һ���µļ�¼
	 * 
	 * @param entity ����ʵ��
	 */
	public void insert(${className} entity);
	
	/**
	 * ���¼�¼
	 * 
	 * @param entity ����ʵ��
	 */
	public void update(${className} entity);
	
	/**
	 * ����ID��ѯ��������¼
	 * 
	 * @param id ��������
	 */
	public ${className} getById(Long id);
	
	/**
	 * ��ѯ�����еļ�¼
	 * 
	 */
	public List<${className}> findAll();
	
	/**
	 * ����ID��ѯ��������¼
	 * 
	 * @param param ��������
	 */
	public Long count(Map<String, Object> param);
	
	/**
	 * ����ID��ѯ��������¼
	 * 
	 * @param param ��������
	 * @param pageBounds ��ҳ����
	 */
	public PageList<${className}> findPage(Map<String, Object> param,PageBounds pageBounds);
	
	<#list table.columns as column>
	<#if column.unique && !column.pk>
	/**
	 * ����${column.columnName}��ѯ��������¼
	 * 
	 * @param value
	 */
	public ${className} getBy${column.columnName}(${column.javaType} value) {
		return (${className})getSqlMapClientTemplate().queryForObject("${className}.getBy${column.columnName}",value);
	}	
	
	</#if>
	</#list>

}
