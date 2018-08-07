/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test.entity.EquipMaintainApply;
import com.jeesite.modules.test.service.EquipMaintainApplyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * 设备报修申请Controller
 * @author dang
 * @version 2018-08-02
 */
@Controller
@RequestMapping(value = "${adminPath}/test/equipMaintainApply")
public class EquipMaintainApplyController extends BaseController {

	@Autowired
	private EquipMaintainApplyService equipMaintainApplyService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EquipMaintainApply get(String applyCode, boolean isNewRecord) {
		return equipMaintainApplyService.get(applyCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:equipMaintainApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipMaintainApply equipMaintainApply, Model model) {
		model.addAttribute("equipMaintainApply", equipMaintainApply);
		return "modules/test/equipMaintainApplyList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:equipMaintainApply:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EquipMaintainApply> listData(EquipMaintainApply equipMaintainApply, HttpServletRequest request, HttpServletResponse response) {
		Page<EquipMaintainApply> page = equipMaintainApplyService.findPage(new Page<EquipMaintainApply>(request, response), equipMaintainApply);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:equipMaintainApply:view")
	@RequestMapping(value = "form")
	public String form(EquipMaintainApply equipMaintainApply, Model model) {
		model.addAttribute("equipMaintainApply", equipMaintainApply);
		return "modules/test/equipMaintainApplyForm";
	}

	/**
	 * 保存设备报修
	 */
	@RequiresPermissions("test:equipMaintainApply:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EquipMaintainApply equipMaintainApply) {
		equipMaintainApplyService.save(equipMaintainApply);
		return renderResult(Global.TRUE, text("保存设备报修成功！"));
	}
	
	/**
	 * 停用设备报修
	 */
	@RequiresPermissions("test:equipMaintainApply:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(EquipMaintainApply equipMaintainApply) {
		equipMaintainApply.setStatus(EquipMaintainApply.STATUS_DISABLE);
		equipMaintainApplyService.updateStatus(equipMaintainApply);
		return renderResult(Global.TRUE, text("停用设备报修成功"));
	}
	
	/**
	 * 启用设备报修
	 */
	@RequiresPermissions("test:equipMaintainApply:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(EquipMaintainApply equipMaintainApply) {
		equipMaintainApply.setStatus(EquipMaintainApply.STATUS_NORMAL);
		equipMaintainApplyService.updateStatus(equipMaintainApply);
		return renderResult(Global.TRUE, text("启用设备报修成功"));
	}
	
	/**
	 * 删除设备报修
	 */
	@RequiresPermissions("test:equipMaintainApply:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EquipMaintainApply equipMaintainApply) {
		equipMaintainApplyService.delete(equipMaintainApply);
		return renderResult(Global.TRUE, text("删除设备报修成功！"));
	}
	
}