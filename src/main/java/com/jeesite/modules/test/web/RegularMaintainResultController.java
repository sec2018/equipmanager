/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jeesite.modules.test.entity.ComponentInfo;
import com.jeesite.modules.test.service.ComponentInfoService;
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
import com.jeesite.modules.test.entity.RegularMaintainResult;
import com.jeesite.modules.test.service.RegularMaintainResultService;

import java.util.List;

/**
 * 定修结果维护Controller
 * @author dyl
 * @version 2018-08-21
 */
@Controller
@RequestMapping(value = "${adminPath}/test/regularMaintainResult")
public class RegularMaintainResultController extends BaseController {

	@Autowired
	private RegularMaintainResultService regularMaintainResultService;

	@Autowired
	private ComponentInfoService componentInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public RegularMaintainResult get(String regularResultCode, boolean isNewRecord) {
		return regularMaintainResultService.get(regularResultCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:regularMaintainResult:view")
	@RequestMapping(value = {"list", ""})
	public String list(RegularMaintainResult regularMaintainResult, Model model) {
		model.addAttribute("regularMaintainResult", regularMaintainResult);
		return "modules/test/regularMaintainResultList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:regularMaintainResult:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<RegularMaintainResult> listData(RegularMaintainResult regularMaintainResult, HttpServletRequest request, HttpServletResponse response) {
		Page<RegularMaintainResult> page = regularMaintainResultService.findPage(new Page<RegularMaintainResult>(request, response), regularMaintainResult); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:regularMaintainResult:view")
	@RequestMapping(value = "form")
	public String form(RegularMaintainResult regularMaintainResult, Model model) {
		//从数据库获取所有备品备件信息传到前端显示
		ComponentInfo componentInfo = new ComponentInfo();
		List<ComponentInfo> componentInfoList = componentInfoService.findList(componentInfo);
		model.addAttribute("componentInfoList", componentInfoList);

		model.addAttribute("regularMaintainResult", regularMaintainResult);
		return "modules/test/regularMaintainResultForm";
	}

	/**
	 * 保存定修结果维护
	 */
	@RequiresPermissions("test:regularMaintainResult:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated RegularMaintainResult regularMaintainResult) {
		regularMaintainResultService.save(regularMaintainResult);
		return renderResult(Global.TRUE, text("保存定修结果维护成功！"));
	}
	
	/**
	 * 停用定修结果维护
	 */
	@RequiresPermissions("test:regularMaintainResult:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(RegularMaintainResult regularMaintainResult) {
		regularMaintainResult.setStatus(RegularMaintainResult.STATUS_DISABLE);
		regularMaintainResultService.updateStatus(regularMaintainResult);
		return renderResult(Global.TRUE, text("停用定修结果维护成功"));
	}
	
	/**
	 * 启用定修结果维护
	 */
	@RequiresPermissions("test:regularMaintainResult:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(RegularMaintainResult regularMaintainResult) {
		regularMaintainResult.setStatus(RegularMaintainResult.STATUS_NORMAL);
		regularMaintainResultService.updateStatus(regularMaintainResult);
		return renderResult(Global.TRUE, text("启用定修结果维护成功"));
	}
	
	/**
	 * 删除定修结果维护
	 */
	@RequiresPermissions("test:regularMaintainResult:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(RegularMaintainResult regularMaintainResult) {
		regularMaintainResultService.delete(regularMaintainResult);
		return renderResult(Global.TRUE, text("删除定修结果维护成功！"));
	}
	
}