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
import com.jeesite.modules.test.entity.ComponentPurchaseInfo;
import com.jeesite.modules.test.service.ComponentPurchaseInfoService;

/**
 * 备品备件入库Controller
 * @author jyf
 * @version 2018-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/test/componentPurchaseInfo")
public class ComponentPurchaseInfoController extends BaseController {

	@Autowired
	private ComponentPurchaseInfoService componentPurchaseInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ComponentPurchaseInfo get(String purchaseInfoCode, boolean isNewRecord) {
		return componentPurchaseInfoService.get(purchaseInfoCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:componentPurchaseInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComponentPurchaseInfo componentPurchaseInfo, Model model) {
		model.addAttribute("componentPurchaseInfo", componentPurchaseInfo);
		return "modules/test/componentPurchaseInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:componentPurchaseInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ComponentPurchaseInfo> listData(ComponentPurchaseInfo componentPurchaseInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<ComponentPurchaseInfo> page = componentPurchaseInfoService.findPage(new Page<ComponentPurchaseInfo>(request, response), componentPurchaseInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:componentPurchaseInfo:view")
	@RequestMapping(value = "form")
	public String form(ComponentPurchaseInfo componentPurchaseInfo, Model model) {
		model.addAttribute("componentPurchaseInfo", componentPurchaseInfo);
		return "modules/test/componentPurchaseInfoForm";
	}

	/**
	 * 保存备品备件入库
	 */
	@RequiresPermissions("test:componentPurchaseInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ComponentPurchaseInfo componentPurchaseInfo) {
		componentPurchaseInfoService.save(componentPurchaseInfo);
		return renderResult(Global.TRUE, text("保存备品备件入库成功！"));
	}
	
	/**
	 * 停用备品备件入库
	 */
	@RequiresPermissions("test:componentPurchaseInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ComponentPurchaseInfo componentPurchaseInfo) {
		componentPurchaseInfo.setStatus(ComponentPurchaseInfo.STATUS_DISABLE);
		componentPurchaseInfoService.updateStatus(componentPurchaseInfo);
		return renderResult(Global.TRUE, text("停用备品备件入库成功"));
	}
	
	/**
	 * 启用备品备件入库
	 */
	@RequiresPermissions("test:componentPurchaseInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ComponentPurchaseInfo componentPurchaseInfo) {
		componentPurchaseInfo.setStatus(ComponentPurchaseInfo.STATUS_NORMAL);
		componentPurchaseInfoService.updateStatus(componentPurchaseInfo);
		return renderResult(Global.TRUE, text("启用备品备件入库成功"));
	}
	
	/**
	 * 删除备品备件入库
	 */
	@RequiresPermissions("test:componentPurchaseInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ComponentPurchaseInfo componentPurchaseInfo) {
		componentPurchaseInfoService.delete(componentPurchaseInfo);
		return renderResult(Global.TRUE, text("删除备品备件入库成功！"));
	}
	
}