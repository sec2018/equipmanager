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
import com.jeesite.modules.test.entity.GradeSubjRelate;
import com.jeesite.modules.test.service.GradeSubjRelateService;

/**
 * grade_subj_relateController
 * @author jyf
 * @version 2018-07-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test/gradeSubjRelate")
public class GradeSubjRelateController extends BaseController {

	@Autowired
	private GradeSubjRelateService gradeSubjRelateService;

	/**
	 * 获取数据
	 */
	@ModelAttribute
	public GradeSubjRelate get(String id, boolean isNewRecord) {
		return gradeSubjRelateService.get(id, isNewRecord);
	}

	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:gradeSubjRelate:view")
	@RequestMapping(value = {"list", ""})
	public String list(GradeSubjRelate gradeSubjRelate, Model model) {
		model.addAttribute("gradeSubjRelate", gradeSubjRelate);
		return "modules/test/gradeSubjRelateList";
	}

	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:gradeSubjRelate:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<GradeSubjRelate> listData(GradeSubjRelate gradeSubjRelate, HttpServletRequest request, HttpServletResponse response) {
		Page<GradeSubjRelate> page = gradeSubjRelateService.findPage(new Page<GradeSubjRelate>(request, response), gradeSubjRelate);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:gradeSubjRelate:view")
	@RequestMapping(value = "form")
	public String form(GradeSubjRelate gradeSubjRelate, Model model) {
		model.addAttribute("gradeSubjRelate", gradeSubjRelate);
		return "modules/test/gradeSubjRelateForm";
	}

	/**
	 * 保存grade_subj_relate
	 */
	@RequiresPermissions("test:gradeSubjRelate:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated GradeSubjRelate gradeSubjRelate) {
		gradeSubjRelateService.save(gradeSubjRelate);
		return renderResult(Global.TRUE, text("保存grade_subj_relate成功！"));
	}

	/**
	 * 删除grade_subj_relate
	 */
	@RequiresPermissions("test:gradeSubjRelate:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(GradeSubjRelate gradeSubjRelate) {
		gradeSubjRelateService.delete(gradeSubjRelate);
		return renderResult(Global.TRUE, text("删除grade_subj_relate成功！"));
	}

}