/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.entity.Extend;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import com.jeesite.modules.test.entity.ComponentApplication;
import com.jeesite.modules.test.service.ComponentApplicationService;

/**
 * component_applicationController
 * @author jyf
 * @version 2018-08-08
 */
@Controller
@RequestMapping(value = "${adminPath}/test/componentApplication")
public class ComponentApplicationController extends BaseController {

	@Autowired
	private ComponentApplicationService componentApplicationService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public ComponentApplication get(String applicationCode, boolean isNewRecord) {
		return componentApplicationService.get(applicationCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:componentApplication:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComponentApplication componentApplication, Model model) {
		model.addAttribute("componentApplication", componentApplication);
		return "modules/test/componentApplicationList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:componentApplication:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<ComponentApplication> listData(ComponentApplication componentApplication, HttpServletRequest request, HttpServletResponse response) {
		Page<ComponentApplication> page = componentApplicationService.findPage(new Page<ComponentApplication>(request, response), componentApplication); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:componentApplication:view")
	@RequestMapping(value = "form")
	public String form(ComponentApplication componentApplication, Model model) {
		model.addAttribute("componentApplication", componentApplication);
		return "modules/test/componentApplicationForm";
	}

	/**
	 * 保存component_application
	 */
	@RequiresPermissions("test:componentApplication:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated ComponentApplication componentApplication) {

		Subject subject = UserUtils.getSubject();
		//设置申请人（需要具有申请权限）
		if(subject.isPermitted("test:componentApplication:apply:view")){
			componentApplication.setApplicantCode(UserUtils.getUser().getUserCode());
		}
		Extend extend = new Extend();
		extend.setExtendS2(UserUtils.getUser().getUserName());
		componentApplication.setExtend(extend);
		//设置审批人(默认只有一个，多个有问题)
		componentApplication.setApprovalCode("buyer");
		componentApplicationService.save(componentApplication);
		return renderResult(Global.TRUE, text("保存component_application成功！"));
	}
	
	/**
	 * 停用component_application
	 */
	@RequiresPermissions("test:componentApplication:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(ComponentApplication componentApplication) {
		componentApplication.setStatus(ComponentApplication.STATUS_DISABLE);
		componentApplicationService.updateStatus(componentApplication);
		return renderResult(Global.TRUE, text("停用component_application成功"));
	}
	
	/**
	 * 启用component_application
	 */
	@RequiresPermissions("test:componentApplication:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(ComponentApplication componentApplication) {
		componentApplication.setStatus(ComponentApplication.STATUS_NORMAL);
		componentApplicationService.updateStatus(componentApplication);
		return renderResult(Global.TRUE, text("启用component_application成功"));
	}
	
	/**
	 * 删除component_application
	 */
	@RequiresPermissions("test:componentApplication:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ComponentApplication componentApplication) {
		componentApplicationService.delete(componentApplication);
		return renderResult(Global.TRUE, text("删除component_application成功！"));
	}
	
}