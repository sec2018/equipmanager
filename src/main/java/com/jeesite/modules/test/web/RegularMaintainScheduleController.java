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
import com.jeesite.modules.test.entity.RegularMaintainSchedule;
import com.jeesite.modules.test.service.RegularMaintainScheduleService;

/**
 * 定修安排Controller
 * @author dyl
 * @version 2018-08-21
 */
@Controller
@RequestMapping(value = "${adminPath}/test/regularMaintainSchedule")
public class RegularMaintainScheduleController extends BaseController {

	@Autowired
	private RegularMaintainScheduleService regularMaintainScheduleService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public RegularMaintainSchedule get(String regularScheduleCode, boolean isNewRecord) {
		return regularMaintainScheduleService.get(regularScheduleCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:regularMaintainSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(RegularMaintainSchedule regularMaintainSchedule, Model model) {
		model.addAttribute("regularMaintainSchedule", regularMaintainSchedule);
		return "modules/test/regularMaintainScheduleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:regularMaintainSchedule:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<RegularMaintainSchedule> listData(RegularMaintainSchedule regularMaintainSchedule, HttpServletRequest request, HttpServletResponse response) {
		Page<RegularMaintainSchedule> page = regularMaintainScheduleService.findPage(new Page<RegularMaintainSchedule>(request, response), regularMaintainSchedule); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:regularMaintainSchedule:view")
	@RequestMapping(value = "form")
	public String form(RegularMaintainSchedule regularMaintainSchedule, Model model) {
		model.addAttribute("regularMaintainSchedule", regularMaintainSchedule);
		return "modules/test/regularMaintainScheduleForm";
	}

	/**
	 * 保存定修安排
	 */
	@RequiresPermissions("test:regularMaintainSchedule:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated RegularMaintainSchedule regularMaintainSchedule) {
		regularMaintainScheduleService.save(regularMaintainSchedule);
		return renderResult(Global.TRUE, text("保存定修安排成功！"));
	}
	
	/**
	 * 停用定修安排
	 */
	@RequiresPermissions("test:regularMaintainSchedule:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(RegularMaintainSchedule regularMaintainSchedule) {
		regularMaintainSchedule.setStatus(RegularMaintainSchedule.STATUS_DISABLE);
		regularMaintainScheduleService.updateStatus(regularMaintainSchedule);
		return renderResult(Global.TRUE, text("停用定修安排成功"));
	}
	
	/**
	 * 启用定修安排
	 */
	@RequiresPermissions("test:regularMaintainSchedule:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(RegularMaintainSchedule regularMaintainSchedule) {
		regularMaintainSchedule.setStatus(RegularMaintainSchedule.STATUS_NORMAL);
		regularMaintainScheduleService.updateStatus(regularMaintainSchedule);
		return renderResult(Global.TRUE, text("启用定修安排成功"));
	}
	
	/**
	 * 删除定修安排
	 */
	@RequiresPermissions("test:regularMaintainSchedule:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(RegularMaintainSchedule regularMaintainSchedule) {
		regularMaintainScheduleService.delete(regularMaintainSchedule);
		return renderResult(Global.TRUE, text("删除定修安排成功！"));
	}
	
}