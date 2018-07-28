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
import com.jeesite.modules.test.entity.EquipScrappedInfo;
import com.jeesite.modules.test.service.EquipScrappedInfoService;

/**
 * equip_scrapped_infoController
 * @author jyf
 * @version 2018-07-27
 */
@Controller
@RequestMapping(value = "${adminPath}/test/equipScrappedInfo")
public class EquipScrappedInfoController extends BaseController {

	@Autowired
	private EquipScrappedInfoService equipScrappedInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EquipScrappedInfo get(String scrappedId, boolean isNewRecord) {
		return equipScrappedInfoService.get(scrappedId, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:equipScrappedInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipScrappedInfo equipScrappedInfo, Model model) {
		model.addAttribute("equipScrappedInfo", equipScrappedInfo);
		return "modules/test/equipScrappedInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:equipScrappedInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EquipScrappedInfo> listData(EquipScrappedInfo equipScrappedInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<EquipScrappedInfo> page = equipScrappedInfoService.findPage(new Page<EquipScrappedInfo>(request, response), equipScrappedInfo); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:equipScrappedInfo:view")
	@RequestMapping(value = "form")
	public String form(EquipScrappedInfo equipScrappedInfo, Model model) {
		model.addAttribute("equipScrappedInfo", equipScrappedInfo);
		return "modules/test/equipScrappedInfoForm";
	}

	/**
	 * 保存equip_scrapped_info
	 */
	@RequiresPermissions("test:equipScrappedInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EquipScrappedInfo equipScrappedInfo) {
		equipScrappedInfoService.save(equipScrappedInfo);
		return renderResult(Global.TRUE, text("保存equip_scrapped_info成功！"));
	}
	
	/**
	 * 停用equip_scrapped_info
	 */
	@RequiresPermissions("test:equipScrappedInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(EquipScrappedInfo equipScrappedInfo) {
		equipScrappedInfo.setStatus(EquipScrappedInfo.STATUS_DISABLE);
		equipScrappedInfoService.updateStatus(equipScrappedInfo);
		return renderResult(Global.TRUE, text("停用equip_scrapped_info成功"));
	}
	
	/**
	 * 启用equip_scrapped_info
	 */
	@RequiresPermissions("test:equipScrappedInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(EquipScrappedInfo equipScrappedInfo) {
		equipScrappedInfo.setStatus(EquipScrappedInfo.STATUS_NORMAL);
		equipScrappedInfoService.updateStatus(equipScrappedInfo);
		return renderResult(Global.TRUE, text("启用equip_scrapped_info成功"));
	}
	
	/**
	 * 删除equip_scrapped_info
	 */
	@RequiresPermissions("test:equipScrappedInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EquipScrappedInfo equipScrappedInfo) {
		equipScrappedInfoService.delete(equipScrappedInfo);
		return renderResult(Global.TRUE, text("删除equip_scrapped_info成功！"));
	}
	
}