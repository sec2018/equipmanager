/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test.entity.Subjects;
import com.jeesite.modules.test.service.SubjectsService;

/**
 * subjectsController
 * @author jyf
 * @version 2018-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test/subjects")
public class SubjectsController extends BaseController {

	@Autowired
	private SubjectsService subjectsService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Subjects get(String id, boolean isNewRecord) {
		return subjectsService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:subjects:view")
	@RequestMapping(value = {"list", ""})
	public String list(Subjects subjects, Model model) {
		model.addAttribute("subjects", subjects);
		return "modules/test/subjectsList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:subjects:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Subjects> listData(Subjects subjects, HttpServletRequest request, HttpServletResponse response) {
		Page<Subjects> page = subjectsService.findPage(new Page<Subjects>(request, response), subjects); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:subjects:view")
	@RequestMapping(value = "form")
	public String form(Subjects subjects, Model model) {
		model.addAttribute("subjects", subjects);
		return "modules/test/subjectsForm";
	}

	/**
	 * 保存subjects
	 */
	@RequiresPermissions("test:subjects:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Subjects subjects) {
		subjectsService.save(subjects);
		return renderResult(Global.TRUE, text("保存subjects成功！"));
	}
	
	/**
	 * 删除subjects
	 */
	@RequiresPermissions("test:subjects:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Subjects subjects) {
		subjectsService.delete(subjects);
		return renderResult(Global.TRUE, text("删除subjects成功！"));
	}
	
}