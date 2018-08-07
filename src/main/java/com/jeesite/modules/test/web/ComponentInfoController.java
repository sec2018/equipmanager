/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.Logical;
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
import com.jeesite.modules.test.entity.ComponentInfo;
import com.jeesite.modules.test.service.ComponentInfoService;

/**
 * 备品备件Controller
 * @author jyf
 * @version 2018-08-07
 */
@Controller
@RequestMapping(value = "${adminPath}/test/componentInfo")
public class ComponentInfoController extends BaseController {

	@Autowired
	private ComponentInfoService componentInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ComponentInfo get(String id, boolean isNewRecord) {
		return componentInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions (value={"test:componentInfo:view", "test:componentInfo:onlyview"}, logical= Logical.OR)
	@RequestMapping(value = {"list", ""})
	public String list(ComponentInfo componentInfo, Model model) {
		model.addAttribute("componentInfo", componentInfo);
		return "modules/test/componentInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions (value={"test:componentInfo:view", "test:componentInfo:onlyview"}, logical= Logical.OR)
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ComponentInfo> listData(ComponentInfo componentInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<ComponentInfo> page = componentInfoService.findPage(new Page<ComponentInfo>(request, response), componentInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions (value={"test:componentInfo:view", "test:componentInfo:onlyview"}, logical= Logical.OR)
	@RequestMapping(value = "form")
	public String form(ComponentInfo componentInfo, Model model) {
		model.addAttribute("componentInfo", componentInfo);
		return "modules/test/componentInfoForm";
	}

	/**
	 * 保存备品备件
	 */
	@RequiresPermissions("test:componentInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ComponentInfo componentInfo) {
		if(componentInfo.getComponentNumber()<10){
			componentInfo.setComponentStatus("1");
		}
		componentInfoService.save(componentInfo);
		return renderResult(Global.TRUE, text("保存备品备件成功！"));
	}
	
	/**
	 * 停用备品备件
	 */
	@RequiresPermissions("test:componentInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ComponentInfo componentInfo) {
		componentInfo.setStatus(ComponentInfo.STATUS_DISABLE);
		componentInfoService.updateStatus(componentInfo);
		return renderResult(Global.TRUE, text("停用备品备件成功"));
	}
	
	/**
	 * 启用备品备件
	 */
	@RequiresPermissions("test:componentInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ComponentInfo componentInfo) {
		componentInfo.setStatus(ComponentInfo.STATUS_NORMAL);
		componentInfoService.updateStatus(componentInfo);
		return renderResult(Global.TRUE, text("启用备品备件成功"));
	}
	
	/**
	 * 删除备品备件
	 */
	@RequiresPermissions("test:componentInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ComponentInfo componentInfo) {
		componentInfoService.delete(componentInfo);
		return renderResult(Global.TRUE, text("删除备品备件成功！"));
	}
	
}