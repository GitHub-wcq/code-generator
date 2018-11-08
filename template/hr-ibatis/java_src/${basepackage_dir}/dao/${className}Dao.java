<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import java.util.List;
import org.springframework.stereotype.Repository;

<#include "/java_imports.include">
@MyBatisRepository("${className}Dao")
public interface ${className}Dao {
    
    /**
     * 添加一条新的记录
     * 
     * @param entity 对象实体
     */
    public void insert(${className} entity);
    
    /**
     * 更新记录
     * 
     * @param entity 对象实体
     */
    public void update(${className} entity);
    
    /**
     * 根据ID查询出单条记录
     * 
     * @param id 对象主键
     */
    public ${className} getById(Long id);
    
    /**
     * 查询出所有的记录
     * 
     */
    public List<${className}> findAll();
    
    /**
     * 根据ID查询出单条记录
     * 
     * @param param 条件参数
     */
    public Long count(Map<String, Object> param);
    
    /**
     * 根据ID查询出单条记录
     * 
     * @param param 条件参数
     * @param pageBounds 分页参数
     */
    public PageList<${className}> findPage(Map<String, Object> param,PageBounds pageBounds);
    
    <#list table.columns as column>
    <#if column.unique && !column.pk>
    /**
     * 根据${column.columnName}查询出单条记录
     * 
     * @param value
     */
    public ${className} getBy${column.columnName}(${column.javaType} value) {
        return (${className})getSqlMapClientTemplate().queryForObject("${className}.getBy${column.columnName}",value);
    }   
    
    </#if>
    </#list>

}
