<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.repository;

<#include "/java_imports.include">

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import ${basepackage}.entity.*;

public interface ${className}Dao extends PagingAndSortingRepository<${className},${table.idColumn.javaType}>, JpaSpecificationExecutor<${className}>{

}
