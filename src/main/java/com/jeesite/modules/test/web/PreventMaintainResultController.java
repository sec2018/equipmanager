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
import com.jeesite.modules.test.entity.PreventMaintainResult;
import com.jeesite.modules.test.service.PreventMaintainResultService;

/**
 * 预防性维护结果Controller
 * @author dyl
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/preventMaintainResult")
public class PreventMaintainResultController extends BaseController {

	@Autowired
	private PreventMaintainResultService preventMaintainResultService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PreventMaintainResult get(String maintianResultCode, boolean isNewRecord) {
		return preventMaintainResultService.get(maintianResultCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:preventMaintainResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(PreventMaintainResult preventMaintainResult, Model model) {
		model.addAttribute("preventMaintainResult", preventMaintainResult);
		return "modules/test/preventMaintainResultList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:preventMaintainResult:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<PreventMaintainResult> listData(PreventMaintainResult preventMaintainResult, HttpServletRequest request, HttpServletResponse response) {
		Page<PreventMaintainResult> page = preventMaintainResultService.findPage(new Page<PreventMaintainResult>(request, response), preventMaintainResult); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:preventMaintainResult:view")
	@RequestMapping(value = "form")
	public String form(PreventMaintainResult preventMaintainResult, Model model) {
		model.addAttribute("preventMaintainResult", preventMaintainResult);
		return "modules/test/preventMaintainResultForm";
	}

	/**
	 * 保存维护结果
	 */
	@RequiresPermissions("test:preventMaintainResult:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PreventMaintainResult preventMaintainResult) {
		preventMaintainResultService.save(preventMaintainResult);
		return renderResult(Global.TRUE, text("保存维护结果成功！"));
	}
	
	/**
	 * 停用维护结果
	 */
	@RequiresPermissions("test:preventMaintainResult:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PreventMaintainResult preventMaintainResult) {
		preventMaintainResult.setStatus(PreventMaintainResult.STATUS_DISABLE);
		preventMaintainResultService.updateStatus(preventMaintainResult);
		return renderResult(Global.TRUE, text("停用维护结果成功"));
	}
	
	/**
	 * 启用维护结果
	 */
	@RequiresPermissions("test:preventMaintainResult:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PreventMaintainResult preventMaintainResult) {
		preventMaintainResult.setStatus(PreventMaintainResult.STATUS_NORMAL);
		preventMaintainResultService.updateStatus(preventMaintainResult);
		return renderResult(Global.TRUE, text("启用维护结果成功"));
	}
	
	/**
	 * 删除维护结果
	 */
	@RequiresPermissions("test:preventMaintainResult:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PreventMaintainResult preventMaintainResult) {
		preventMaintainResultService.delete(preventMaintainResult);
		return renderResult(Global.TRUE, text("删除维护结果成功！"));
	}
	
}