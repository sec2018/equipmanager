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
import com.jeesite.modules.test.entity.CheckSchedule;
import com.jeesite.modules.test.service.CheckScheduleService;

/**
 * 点检安排Controller
 * @author jyf
 * @version 2018-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/test/checkSchedule")
public class CheckScheduleController extends BaseController {

	@Autowired
	private CheckScheduleService checkScheduleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CheckSchedule get(String chackScheduleCode, boolean isNewRecord) {
		return checkScheduleService.get(chackScheduleCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:checkSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckSchedule checkSchedule, Model model) {
		model.addAttribute("checkSchedule", checkSchedule);
		return "modules/test/checkScheduleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:checkSchedule:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CheckSchedule> listData(CheckSchedule checkSchedule, HttpServletRequest request, HttpServletResponse response) {
		Page<CheckSchedule> page = checkScheduleService.findPage(new Page<CheckSchedule>(request, response), checkSchedule); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:checkSchedule:view")
	@RequestMapping(value = "form")
	public String form(CheckSchedule checkSchedule, Model model) {
		model.addAttribute("checkSchedule", checkSchedule);
		return "modules/test/checkScheduleForm";
	}

	/**
	 * 保存点检安排
	 */
	@RequiresPermissions("test:checkSchedule:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CheckSchedule checkSchedule) {
		checkScheduleService.save(checkSchedule);
		return renderResult(Global.TRUE, text("保存点检安排成功！"));
	}
	
	/**
	 * 停用点检安排
	 */
	@RequiresPermissions("test:checkSchedule:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CheckSchedule checkSchedule) {
		checkSchedule.setStatus(CheckSchedule.STATUS_DISABLE);
		checkScheduleService.updateStatus(checkSchedule);
		return renderResult(Global.TRUE, text("停用点检安排成功"));
	}
	
	/**
	 * 启用点检安排
	 */
	@RequiresPermissions("test:checkSchedule:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CheckSchedule checkSchedule) {
		checkSchedule.setStatus(CheckSchedule.STATUS_NORMAL);
		checkScheduleService.updateStatus(checkSchedule);
		return renderResult(Global.TRUE, text("启用点检安排成功"));
	}
	
	/**
	 * 删除点检安排
	 */
	@RequiresPermissions("test:checkSchedule:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CheckSchedule checkSchedule) {
		checkScheduleService.delete(checkSchedule);
		return renderResult(Global.TRUE, text("删除点检安排成功！"));
	}
	
}