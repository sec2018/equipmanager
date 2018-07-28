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
import com.jeesite.modules.test.entity.GTest;
import com.jeesite.modules.test.service.GTestService;

/**
 * 班级表Controller
 * @author jyf
 * @version 2018-07-21
 */
@Controller
@RequestMapping(value = "${adminPath}/test/gTest")
public class GTestController extends BaseController {

	@Autowired
	private GTestService gTestService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public GTest get(String id, boolean isNewRecord) {
		return gTestService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:gTest:view")
	@RequestMapping(value = {"list", ""})
	public String list(GTest gTest, Model model) {
		model.addAttribute("gTest", gTest);
		return "modules/test/gTestList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:gTest:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<GTest> listData(GTest gTest, HttpServletRequest request, HttpServletResponse response) {
		Page<GTest> page = gTestService.findPage(new Page<GTest>(request, response), gTest); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:gTest:view")
	@RequestMapping(value = "form")
	public String form(GTest gTest, Model model) {
		model.addAttribute("gTest", gTest);
		return "modules/test/gTestForm";
	}

	/**
	 * 保存班级
	 */
	@RequiresPermissions("test:gTest:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated GTest gTest) {
		gTestService.save(gTest);
		return renderResult(Global.TRUE, text("保存班级成功！"));
	}
	
	/**
	 * 停用班级
	 */
	@RequiresPermissions("test:gTest:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(GTest gTest) {
		gTest.setStatus(GTest.STATUS_DISABLE);
		gTestService.updateStatus(gTest);
		return renderResult(Global.TRUE, text("停用班级成功"));
	}
	
	/**
	 * 启用班级
	 */
	@RequiresPermissions("test:gTest:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(GTest gTest) {
		gTest.setStatus(GTest.STATUS_NORMAL);
		gTestService.updateStatus(gTest);
		return renderResult(Global.TRUE, text("启用班级成功"));
	}
	
	/**
	 * 删除班级
	 */
	@RequiresPermissions("test:gTest:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(GTest gTest) {
		gTestService.delete(gTest);
		return renderResult(Global.TRUE, text("删除班级成功！"));
	}
	
}