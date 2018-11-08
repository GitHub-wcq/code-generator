<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>   
package ${basepackage}.web;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.google.common.collect.Maps;
import ${basepackage}.entity.*;
import ${basepackage}.service.*;

<#include "/java_imports.include">
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Controller {
	@Autowired
	private ${className}Service ${classNameFirstLower}Service;

	private static final String PAGE_SIZE = "10";
	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		Long userId = getCurrentUserId();

		Page<${className}> page = ${classNameFirstLower}Service.getUserPage(userId, searchParams, pageNumber, pageSize, sortType);

		model.addAttribute("page", page);
		model.addAttribute("sortType", sortType);
		model.addAttribute("sortTypes", sortTypes);
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));

		return "${classNameLowerCase}/${classNameLowerCase}List";
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("entity", new ${className}());
		model.addAttribute("action", "create");
		return "${classNameLowerCase}/${classNameLowerCase}Form";
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid ${className} new${className}, RedirectAttributes redirectAttributes) {
		${className} entity = new ${className}(getCurrentUserId());
		new${className}.setUser(entity);

		${classNameFirstLower}Service.save(new${className});
		redirectAttributes.addFlashAttribute("message", "创建任务成功");
		return "redirect:/${classNameLowerCase}/";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("entity", ${classNameFirstLower}Service.get${className}(id));
		model.addAttribute("action", "update");
		return "${classNameLowerCase}/${classNameLowerCase}Form";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("entity") ${className} entity, RedirectAttributes redirectAttributes) {
		${classNameFirstLower}Service.save${className}(entity);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/${classNameLowerCase}/";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		${classNameFirstLower}Service.delete${className}(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/${classNameLowerCase}/";
	}

	@ModelAttribute
	public void get${className}(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("entity", ${classNameFirstLower}Service.get${className}(id));
		}
	}

	
	/**
	 * 取出Shiro中的当前用户Id.
	 */
	private Long getCurrentUserId() {
		ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
		return user.id;
	}
}

