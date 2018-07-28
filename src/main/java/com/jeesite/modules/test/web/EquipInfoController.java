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
import com.jeesite.modules.test.entity.EquipInfo;
import com.jeesite.modules.test.service.EquipInfoService;

/**
 * equip_infoController
 * @author jyf
 * @version 2018-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/test/equipInfo")
public class EquipInfoController extends BaseController {

	@Autowired
	private EquipInfoService equipInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EquipInfo get(String id, boolean isNewRecord) {
		return equipInfoService.get(id, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:equipInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipInfo equipInfo, Model model) {
		model.addAttribute("equipInfo", equipInfo);
		return "modules/test/equipInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:equipInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EquipInfo> listData(EquipInfo equipInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<EquipInfo> page = equipInfoService.findPage(new Page<EquipInfo>(request, response), equipInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:equipInfo:view")
	@RequestMapping(value = "form")
	public String form(EquipInfo equipInfo, Model model) {
		model.addAttribute("equipInfo", equipInfo);
		return "modules/test/equipInfoForm";
	}

	/**
	 * 保存equip_info
	 */
	@RequiresPermissions("test:equipInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EquipInfo equipInfo) {
		equipInfoService.save(equipInfo);
		return renderResult(Global.TRUE, text("保存equip_info成功！"));
	}
	
	/**
	 * 停用equip_info
	 */
	@RequiresPermissions("test:equipInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(EquipInfo equipInfo) {
		equipInfo.setStatus(EquipInfo.STATUS_DISABLE);
		equipInfoService.updateStatus(equipInfo);
		return renderResult(Global.TRUE, text("停用equip_info成功"));
	}
	
	/**
	 * 启用equip_info
	 */
	@RequiresPermissions("test:equipInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(EquipInfo equipInfo) {
		equipInfo.setStatus(EquipInfo.STATUS_NORMAL);
		equipInfoService.updateStatus(equipInfo);
		return renderResult(Global.TRUE, text("启用equip_info成功"));
	}
	
	/**
	 * 删除equip_info
	 */
	@RequiresPermissions("test:equipInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EquipInfo equipInfo) {
		equipInfoService.delete(equipInfo);
		return renderResult(Global.TRUE, text("删除equip_info成功！"));
	}
	
}