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
import com.jeesite.modules.test.entity.STest;
import com.jeesite.modules.test.service.STestService;

/**
 * 学生表Controller
 * @author jyf
 * @version 2018-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/test/sTest")
public class STestController extends BaseController {

	@Autowired
	private STestService sTestService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public STest get(String id, boolean isNewRecord) {
		return sTestService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:sTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(STest sTest, Model model) {
		model.addAttribute("sTest", sTest);
		return "modules/test/sTestList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:sTest:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<STest> listData(STest sTest, HttpServletRequest request, HttpServletResponse response) {
		Page<STest> page = sTestService.findPage(new Page<STest>(request, response), sTest); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:sTest:view")
	@RequestMapping(value = "form")
	public String form(STest sTest, Model model) {
		model.addAttribute("sTest", sTest);
		return "modules/test/sTestForm";
	}

	/**
	 * 保存学生表
	 */
	@RequiresPermissions("test:sTest:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated STest sTest) {
		sTestService.save(sTest);
		return renderResult(Global.TRUE, text("保存学生表成功！"));
	}
	
	/**
	 * 停用学生表
	 */
	@RequiresPermissions("test:sTest:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(STest sTest) {
		sTest.setStatus(STest.STATUS_DISABLE);
		sTestService.updateStatus(sTest);
		return renderResult(Global.TRUE, text("停用学生表成功"));
	}
	
	/**
	 * 启用学生表
	 */
	@RequiresPermissions("test:sTest:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(STest sTest) {
		sTest.setStatus(STest.STATUS_NORMAL);
		sTestService.updateStatus(sTest);
		return renderResult(Global.TRUE, text("启用学生表成功"));
	}
	
	/**
	 * 删除学生表
	 */
	@RequiresPermissions("test:sTest:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(STest sTest) {
		sTestService.delete(sTest);
		return renderResult(Global.TRUE, text("删除学生表成功！"));
	}
	
}