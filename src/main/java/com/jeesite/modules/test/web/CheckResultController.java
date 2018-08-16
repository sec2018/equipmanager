/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.common.lang.StringUtils;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.CheckPlans;
import com.jeesite.modules.test.entity.Subjects;
import com.jeesite.modules.test.service.CheckPlansService;
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
import com.jeesite.modules.test.entity.CheckResult;
import com.jeesite.modules.test.service.CheckResultService;

/**
 * 点检结果Controller
 * @author jyf
 * @version 2018-08-16
 */
@Controller
@RequestMapping(value = "${adminPath}/test/checkResult")
public class CheckResultController extends BaseController {

	@Autowired
	private CheckResultService checkResultService;

	@Autowired
	private CheckPlansService checkPlansService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CheckResult get(String id, boolean isNewRecord) {
		return checkResultService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:checkResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckResult checkResult, Model model) {
		model.addAttribute("checkResult", checkResult);
		return "modules/test/checkResultList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:checkResult:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<CheckResult> listData(CheckResult checkResult, HttpServletRequest request, HttpServletResponse response) {
		Page<CheckResult> page = checkResultService.findPage(new Page<CheckResult>(request, response), checkResult);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:checkResult:view")
	@RequestMapping(value = "form")
	public String form(CheckResult checkResult, Model model) {
		model.addAttribute("checkResult", checkResult);
		return "modules/test/checkResultForm";
	}

	/**
	 * 保存点检结果
	 */
	@RequiresPermissions("test:checkResult:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CheckResult checkResult) {
	    CheckPlans checkPlans =checkPlansService.get(checkResult.getCheckPlanCode());
		checkResult.setCheckPlanName(checkPlans.getTreeName());
		User user = UserUtils.getUser();
		Subject subject =  UserUtils.getSubject();
	    if(subject.isPermitted("test:checkResult:edit")&&subject.hasRole("repairmen")){
			checkResult.setCheckerId(user.getUserCode());
		}
	    checkResultService.save(checkResult);
		return renderResult(Global.TRUE, text("保存点检结果成功！"));
	}
	
	/**
	 * 停用点检结果
	 */
	@RequiresPermissions("test:checkResult:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CheckResult checkResult) {
		checkResult.setStatus(CheckResult.STATUS_DISABLE);
		checkResultService.updateStatus(checkResult);
		return renderResult(Global.TRUE, text("停用点检结果成功"));
	}
	
	/**
	 * 启用点检结果
	 */
	@RequiresPermissions("test:checkResult:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CheckResult checkResult) {
		checkResult.setStatus(CheckResult.STATUS_NORMAL);
		checkResultService.updateStatus(checkResult);
		return renderResult(Global.TRUE, text("启用点检结果成功"));
	}
	
	/**
	 * 删除点检结果
	 */
	@RequiresPermissions("test:checkResult:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CheckResult checkResult) {
		checkResultService.delete(checkResult);
		return renderResult(Global.TRUE, text("删除点检结果成功！"));
	}
	
}