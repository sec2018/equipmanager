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
import com.jeesite.modules.test.entity.InspectResult;
import com.jeesite.modules.test.service.InspectResultService;

/**
 * 巡检结果Controller
 * @author dyl
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/test/inspectResult")
public class InspectResultController extends BaseController {

	@Autowired
	private InspectResultService inspectResultService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public InspectResult get(String inspectResultCode, boolean isNewRecord) {
		return inspectResultService.get(inspectResultCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:inspectResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(InspectResult inspectResult, Model model) {
		model.addAttribute("inspectResult", inspectResult);
		return "modules/test/inspectResultList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:inspectResult:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<InspectResult> listData(InspectResult inspectResult, HttpServletRequest request, HttpServletResponse response) {
		Page<InspectResult> page = inspectResultService.findPage(new Page<InspectResult>(request, response), inspectResult); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:inspectResult:view")
	@RequestMapping(value = "form")
	public String form(InspectResult inspectResult, Model model) {
		model.addAttribute("inspectResult", inspectResult);
		return "modules/test/inspectResultForm";
	}

	/**
	 * 保存巡检结果
	 */
	@RequiresPermissions("test:inspectResult:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated InspectResult inspectResult) {
		inspectResultService.save(inspectResult);
		return renderResult(Global.TRUE, text("保存巡检结果成功！"));
	}
	
	/**
	 * 停用巡检结果
	 */
	@RequiresPermissions("test:inspectResult:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(InspectResult inspectResult) {
		inspectResult.setStatus(InspectResult.STATUS_DISABLE);
		inspectResultService.updateStatus(inspectResult);
		return renderResult(Global.TRUE, text("停用巡检结果成功"));
	}
	
	/**
	 * 启用巡检结果
	 */
	@RequiresPermissions("test:inspectResult:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(InspectResult inspectResult) {
		inspectResult.setStatus(InspectResult.STATUS_NORMAL);
		inspectResultService.updateStatus(inspectResult);
		return renderResult(Global.TRUE, text("启用巡检结果成功"));
	}
	
	/**
	 * 删除巡检结果
	 */
	@RequiresPermissions("test:inspectResult:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(InspectResult inspectResult) {
		inspectResultService.delete(inspectResult);
		return renderResult(Global.TRUE, text("删除巡检结果成功！"));
	}
	
}