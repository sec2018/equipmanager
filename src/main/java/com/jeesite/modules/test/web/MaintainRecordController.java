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
import com.jeesite.modules.test.entity.MaintainRecord;
import com.jeesite.modules.test.service.MaintainRecordService;

/**
 * 维修工单Controller
 * @author dyl
 * @version 2018-08-23
 */
@Controller
@RequestMapping(value = "${adminPath}/test/maintainRecord")
public class MaintainRecordController extends BaseController {

	@Autowired
	private MaintainRecordService maintainRecordService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public MaintainRecord get(String recordCode, boolean isNewRecord) {
		return maintainRecordService.get(recordCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:maintainRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaintainRecord maintainRecord, Model model) {
		model.addAttribute("maintainRecord", maintainRecord);
		return "modules/test/maintainRecordList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:maintainRecord:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<MaintainRecord> listData(MaintainRecord maintainRecord, HttpServletRequest request, HttpServletResponse response) {
		Page<MaintainRecord> page = maintainRecordService.findPage(new Page<MaintainRecord>(request, response), maintainRecord); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:maintainRecord:view")
	@RequestMapping(value = "form")
	public String form(MaintainRecord maintainRecord, Model model) {
		model.addAttribute("maintainRecord", maintainRecord);
		return "modules/test/maintainRecordForm";
	}

	/**
	 * 保存维修工单
	 */
	@RequiresPermissions("test:maintainRecord:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated MaintainRecord maintainRecord) {
		maintainRecordService.save(maintainRecord);
		return renderResult(Global.TRUE, text("保存维修工单成功！"));
	}
	
	/**
	 * 停用维修工单
	 */
	@RequiresPermissions("test:maintainRecord:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(MaintainRecord maintainRecord) {
		maintainRecord.setStatus(MaintainRecord.STATUS_DISABLE);
		maintainRecordService.updateStatus(maintainRecord);
		return renderResult(Global.TRUE, text("停用维修工单成功"));
	}
	
	/**
	 * 启用维修工单
	 */
	@RequiresPermissions("test:maintainRecord:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(MaintainRecord maintainRecord) {
		maintainRecord.setStatus(MaintainRecord.STATUS_NORMAL);
		maintainRecordService.updateStatus(maintainRecord);
		return renderResult(Global.TRUE, text("启用维修工单成功"));
	}
	
	/**
	 * 删除维修工单
	 */
	@RequiresPermissions("test:maintainRecord:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(MaintainRecord maintainRecord) {
		maintainRecordService.delete(maintainRecord);
		return renderResult(Global.TRUE, text("删除维修工单成功！"));
	}
	
}