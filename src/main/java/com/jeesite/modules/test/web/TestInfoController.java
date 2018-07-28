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
import com.jeesite.modules.test.entity.TestInfo;
import com.jeesite.modules.test.service.TestInfoService;

/**
 * test_infoController
 * @author jyf
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/test/testInfo")
public class TestInfoController extends BaseController {

	@Autowired
	private TestInfoService testInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TestInfo get(String id, boolean isNewRecord) {
		return testInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:testInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(TestInfo testInfo, Model model) {
		model.addAttribute("testInfo", testInfo);
		return "modules/test/testInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:testInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TestInfo> listData(TestInfo testInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<TestInfo> page = testInfoService.findPage(new Page<TestInfo>(request, response), testInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:testInfo:view")
	@RequestMapping(value = "form")
	public String form(TestInfo testInfo, Model model) {
		model.addAttribute("testInfo", testInfo);
		return "modules/test/testInfoForm";
	}

	/**
	 * 保存test_info
	 */
	@RequiresPermissions("test:testInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TestInfo testInfo) {
		testInfoService.save(testInfo);
		return renderResult(Global.TRUE, text("保存test_info成功！"));
	}
	
	/**
	 * 停用test_info
	 */
	@RequiresPermissions("test:testInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(TestInfo testInfo) {
		testInfo.setStatus(TestInfo.STATUS_DISABLE);
		testInfoService.updateStatus(testInfo);
		return renderResult(Global.TRUE, text("停用test_info成功"));
	}
	
	/**
	 * 启用test_info
	 */
	@RequiresPermissions("test:testInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(TestInfo testInfo) {
		testInfo.setStatus(TestInfo.STATUS_NORMAL);
		testInfoService.updateStatus(testInfo);
		return renderResult(Global.TRUE, text("启用test_info成功"));
	}
	
	/**
	 * 删除test_info
	 */
	@RequiresPermissions("test:testInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TestInfo testInfo) {
		testInfoService.delete(testInfo);
		return renderResult(Global.TRUE, text("删除test_info成功！"));
	}
	
}