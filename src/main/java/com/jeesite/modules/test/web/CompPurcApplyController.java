/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Extend;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.CompPurcApply;
import com.jeesite.modules.test.service.CompPurcApplyService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * comp_purc_applyController
 * @author jyf
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/test/compPurcApply")
public class CompPurcApplyController extends BaseController {

	@Autowired
	private CompPurcApplyService compPurcApplyService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CompPurcApply get(String purchaseApplyCode, boolean isNewRecord) {
		return compPurcApplyService.get(purchaseApplyCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:compPurcApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(CompPurcApply compPurcApply, Model model) {
		model.addAttribute("compPurcApply", compPurcApply);
		return "modules/test/compPurcApplyList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:compPurcApply:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CompPurcApply> listData(CompPurcApply compPurcApply, HttpServletRequest request, HttpServletResponse response) {

		Page<CompPurcApply> page = compPurcApplyService.findPage(new Page<CompPurcApply>(request, response), compPurcApply); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:compPurcApply:view")
	@RequestMapping(value = "form")
	public String form(CompPurcApply compPurcApply, Model model) {
		model.addAttribute("compPurcApply", compPurcApply);
		return "modules/test/compPurcApplyForm";
	}

	/**
	 * 保存comp_purc_apply
	 */
	@RequiresPermissions("test:compPurcApply:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CompPurcApply compPurcApply) {
		Extend extend = new Extend();
		Subject subject = UserUtils.getSubject();
		//审批人
		extend.setExtendS2("superManagers");

		//申请人
		if(subject.isPermitted("test:compPurc:apply:view")){
			extend.setExtendS3(UserUtils.getUser().getLoginCode());
		}
		compPurcApply.setExtend(extend);
		compPurcApplyService.save(compPurcApply);
		return renderResult(Global.TRUE, text("保存comp_purc_apply成功！"));
	}
	
	/**
	 * 停用comp_purc_apply
	 */
	@RequiresPermissions("test:compPurcApply:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CompPurcApply compPurcApply) {
		compPurcApply.setStatus(CompPurcApply.STATUS_DISABLE);
		compPurcApplyService.updateStatus(compPurcApply);
		return renderResult(Global.TRUE, text("停用comp_purc_apply成功"));
	}
	
	/**
	 * 启用comp_purc_apply
	 */
	@RequiresPermissions("test:compPurcApply:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CompPurcApply compPurcApply) {
		compPurcApply.setStatus(CompPurcApply.STATUS_NORMAL);
		compPurcApplyService.updateStatus(compPurcApply);
		return renderResult(Global.TRUE, text("启用comp_purc_apply成功"));
	}
	
	/**
	 * 删除comp_purc_apply
	 */
	@RequiresPermissions("test:compPurcApply:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CompPurcApply compPurcApply) {
		compPurcApplyService.delete(compPurcApply);
		return renderResult(Global.TRUE, text("删除comp_purc_apply成功！"));
	}
	
}