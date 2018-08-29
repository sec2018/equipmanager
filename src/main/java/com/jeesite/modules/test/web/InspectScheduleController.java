/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.test.entity.InspectPlan;
import com.jeesite.modules.test.service.InspectPlanService;
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
import com.jeesite.modules.test.entity.InspectSchedule;
import com.jeesite.modules.test.service.InspectScheduleService;

import java.util.List;

/**
 * 巡检安排Controller
 * @author dyl
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/test/inspectSchedule")
public class InspectScheduleController extends BaseController {

	@Autowired
	private InspectScheduleService inspectScheduleService;

	@Autowired
	private InspectPlanService inspectPlanService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public InspectSchedule get(String inspectScheduleCode, boolean isNewRecord) {
		return inspectScheduleService.get(inspectScheduleCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:inspectSchedule:view")
	@RequestMapping(value = {"list", ""})
	public String list(InspectSchedule inspectSchedule, Model model) {
		model.addAttribute("inspectSchedule", inspectSchedule);
		return "modules/test/inspectScheduleList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:inspectSchedule:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<InspectSchedule> listData(InspectSchedule inspectSchedule, HttpServletRequest request, HttpServletResponse response) {
		Page<InspectSchedule> page = inspectScheduleService.findPage(new Page<InspectSchedule>(request, response), inspectSchedule); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:inspectSchedule:view")
	@RequestMapping(value = "form")
	public String form(InspectSchedule inspectSchedule, Model model) {
		model.addAttribute("inspectSchedule", inspectSchedule);
		InspectPlan inspectPlan = new InspectPlan();
		//查询所有巡检计划编号
		List<InspectPlan> inspectPlanList = inspectPlanService.findList(inspectPlan);
		//将巡检计划编号传入前台
		model.addAttribute("inspectPlanList", inspectPlanList);
		return "modules/test/inspectScheduleForm";
	}

	/**
	 * 保存巡检安排
	 */
	@RequiresPermissions("test:inspectSchedule:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated InspectSchedule inspectSchedule) {
		inspectScheduleService.save(inspectSchedule);
		return renderResult(Global.TRUE, text("保存巡检安排成功！"));
	}
	
	/**
	 * 停用巡检安排
	 */
	@RequiresPermissions("test:inspectSchedule:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(InspectSchedule inspectSchedule) {
		inspectSchedule.setStatus(InspectSchedule.STATUS_DISABLE);
		inspectScheduleService.updateStatus(inspectSchedule);
		return renderResult(Global.TRUE, text("停用巡检安排成功"));
	}
	
	/**
	 * 启用巡检安排
	 */
	@RequiresPermissions("test:inspectSchedule:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(InspectSchedule inspectSchedule) {
		inspectSchedule.setStatus(InspectSchedule.STATUS_NORMAL);
		inspectScheduleService.updateStatus(inspectSchedule);
		return renderResult(Global.TRUE, text("启用巡检安排成功"));
	}
	
	/**
	 * 删除巡检安排
	 */
	@RequiresPermissions("test:inspectSchedule:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(InspectSchedule inspectSchedule) {
		inspectScheduleService.delete(inspectSchedule);
		return renderResult(Global.TRUE, text("删除巡检安排成功！"));
	}
	
}