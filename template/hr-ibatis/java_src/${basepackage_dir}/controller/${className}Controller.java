<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameFirstLower = className?uncap_first>   
<#assign classNameLowerCase = className?lower_case>   
<#assign pkJavaType = table.idColumn.javaType>   
package ${basepackage}.web;

import java.util.Map;
import java.util.List;
import javax.servlet.ServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.opensymphony.module.sitemesh.Page;

import ${basepackage}.entity.*;
import ${basepackage}.service.*;

<#include "/java_imports.include">
@Controller
@RequestMapping("/${classNameLowerCase}")
public class ${className}Controller {

	@Autowired
	private ${className}Service ${classNameFirstLower}Service;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		List<${className}> ${classNameFirstLower}s = ${classNameFirstLower}Service.getAll${className}();
		model.addAttribute("${classNameFirstLower}s", ${classNameFirstLower}s);

		return "/${classNameFirstLower}/${classNameFirstLower}List";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("${classNameFirstLower}", ${classNameFirstLower}Service.get${className}(id));
		return "${classNameFirstLower}Form";
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update( @ModelAttribute("${classNameFirstLower}") ${className} ${classNameFirstLower}, RedirectAttributes redirectAttributes) {
		${classNameFirstLower}Service.update${className}(${classNameFirstLower});
		redirectAttributes.addFlashAttribute("message", "更新实体" + ${classNameFirstLower}.getId() + "成功");
		return "redirect:/admin/user";
	}

	/**
	 * 所有RequestMapping方法调用前的Model准备方法, 
	 * 实现Struts2 Preparable二次部分绑定的效果,先根据form的id从数据库查出对象,再把Form提交的内容绑定到该对象上。
	 * 因为仅update()方法的form中有id属性，因此仅在update时实际执行.
	 */
	@ModelAttribute
	public void get${className}(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
		if (id != -1) {
			model.addAttribute("${classNameFirstLower}", ${classNameFirstLower}Service.get${className}(id));
		}
	}
	
	/**
	 * 跳转到添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(@RequestParam(value="${classNameFirstLower}Id",required=false)Integer ${classNameFirstLower}Id, Model model) {
		// TODO 实现方法
		return "content/jobbase/jobbaseForm";
	}

	/**
	 * 添加实体信息
	 * @param newJobBase
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(${className} ${classNameFirstLower}) {
		// TODO 实现该方法
		${classNameFirstLower}Service.insert${className}(${classNameFirstLower});
		return null;
	}
}

