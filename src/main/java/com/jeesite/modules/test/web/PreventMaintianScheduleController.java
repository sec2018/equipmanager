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
import com.jeesite.modules.test.entity.PreventMaintianSchedule;
import com.jeesite.modules.test.service.PreventMaintianScheduleService;

/**
 * 预防性维护计划安排Controller
 * @author dyl
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/preventMaintianSchedule")
public class PreventMaintianScheduleController extends BaseController {

	@Autowired
	private PreventMaintianScheduleService preventMaintianScheduleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PreventMaintianSchedule get(String maintainScheduleCode, boolean isNewRecord) {
		return preventMaintianScheduleService.get(maintainScheduleCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:preventMaintianSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(PreventMaintianSchedule preventMaintianSchedule, Model model) {
		model.addAttribute("preventMaintianSchedule", preventMaintianSchedule);
		return "modules/test/preventMaintianScheduleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:preventMaintianSchedule:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PreventMaintianSchedule> listData(PreventMaintianSchedule preventMaintianSchedule, HttpServletRequest request, HttpServletResponse response) {
		Page<PreventMaintianSchedule> page = preventMaintianScheduleService.findPage(new Page<PreventMaintianSchedule>(request, response), preventMaintianSchedule); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:preventMaintianSchedule:view")
	@RequestMapping(value = "form")
	public String form(PreventMaintianSchedule preventMaintianSchedule, Model model) {
		model.addAttribute("preventMaintianSchedule", preventMaintianSchedule);
		return "modules/test/preventMaintianScheduleForm";
	}

	/**
	 * 保存维护计划安排
	 */
	@RequiresPermissions("test:preventMaintianSchedule:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PreventMaintianSchedule preventMaintianSchedule) {
		preventMaintianScheduleService.save(preventMaintianSchedule);
		return renderResult(Global.TRUE, text("保存维护计划安排成功！"));
	}
	
	/**
	 * 停用维护计划安排
	 */
	@RequiresPermissions("test:preventMaintianSchedule:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PreventMaintianSchedule preventMaintianSchedule) {
		preventMaintianSchedule.setStatus(PreventMaintianSchedule.STATUS_DISABLE);
		preventMaintianScheduleService.updateStatus(preventMaintianSchedule);
		return renderResult(Global.TRUE, text("停用维护计划安排成功"));
	}
	
	/**
	 * 启用维护计划安排
	 */
	@RequiresPermissions("test:preventMaintianSchedule:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PreventMaintianSchedule preventMaintianSchedule) {
		preventMaintianSchedule.setStatus(PreventMaintianSchedule.STATUS_NORMAL);
		preventMaintianScheduleService.updateStatus(preventMaintianSchedule);
		return renderResult(Global.TRUE, text("启用维护计划安排成功"));
	}
	
	/**
	 * 删除维护计划安排
	 */
	@RequiresPermissions("test:preventMaintianSchedule:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PreventMaintianSchedule preventMaintianSchedule) {
		preventMaintianScheduleService.delete(preventMaintianSchedule);
		return renderResult(Global.TRUE, text("删除维护计划安排成功！"));
	}
	
}