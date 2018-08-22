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
import com.jeesite.modules.test.entity.TroubleNotice;
import com.jeesite.modules.test.service.TroubleNoticeService;

/**
 * 故障通知单Controller
 * @author dyl
 * @version 2018-08-22
 */
@Controller
@RequestMapping(value = "${adminPath}/test/troubleNotice")
public class TroubleNoticeController extends BaseController {

	@Autowired
	private TroubleNoticeService troubleNoticeService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public TroubleNotice get(String noticeCode, boolean isNewRecord) {
		return troubleNoticeService.get(noticeCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:troubleNotice:view")
	@RequestMapping(value = {"list", ""})
	public String list(TroubleNotice troubleNotice, Model model) {
		model.addAttribute("troubleNotice", troubleNotice);
		return "modules/test/troubleNoticeList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:troubleNotice:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<TroubleNotice> listData(TroubleNotice troubleNotice, HttpServletRequest request, HttpServletResponse response) {
		Page<TroubleNotice> page = troubleNoticeService.findPage(new Page<TroubleNotice>(request, response), troubleNotice); 
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:troubleNotice:view")
	@RequestMapping(value = "form")
	public String form(TroubleNotice troubleNotice, Model model) {
		model.addAttribute("troubleNotice", troubleNotice);
		return "modules/test/troubleNoticeForm";
	}

	/**
	 * 保存故障通知单
	 */
	@RequiresPermissions("test:troubleNotice:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated TroubleNotice troubleNotice) {
		troubleNoticeService.save(troubleNotice);
		return renderResult(Global.TRUE, text("保存故障通知单成功！"));
	}
	
	/**
	 * 停用故障通知单
	 */
	@RequiresPermissions("test:troubleNotice:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(TroubleNotice troubleNotice) {
		troubleNotice.setStatus(TroubleNotice.STATUS_DISABLE);
		troubleNoticeService.updateStatus(troubleNotice);
		return renderResult(Global.TRUE, text("停用故障通知单成功"));
	}
	
	/**
	 * 启用故障通知单
	 */
	@RequiresPermissions("test:troubleNotice:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(TroubleNotice troubleNotice) {
		troubleNotice.setStatus(TroubleNotice.STATUS_NORMAL);
		troubleNoticeService.updateStatus(troubleNotice);
		return renderResult(Global.TRUE, text("启用故障通知单成功"));
	}
	
	/**
	 * 删除故障通知单
	 */
	@RequiresPermissions("test:troubleNotice:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(TroubleNotice troubleNotice) {
		troubleNoticeService.delete(troubleNotice);
		return renderResult(Global.TRUE, text("删除故障通知单成功！"));
	}
	
}