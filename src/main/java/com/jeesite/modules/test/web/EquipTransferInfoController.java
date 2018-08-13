/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test.entity.EquipInfo;
import com.jeesite.modules.test.entity.EquipTransferInfo;
import com.jeesite.modules.test.service.EquipInfoService;
import com.jeesite.modules.test.service.EquipTransferInfoService;
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
import java.util.List;

/**
 * 设备调拨信息Controller
 * @author dang
 * @version 2018-08-06
 */
@Controller
@RequestMapping(value = "${adminPath}/test/equipTransferInfo")
public class EquipTransferInfoController extends BaseController {

	@Autowired
	private EquipTransferInfoService equipTransferInfoService;

	@Autowired
	private EquipInfoService equipInfoService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public EquipTransferInfo get(String transferCode, boolean isNewRecord) {
		return equipTransferInfoService.get(transferCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:equipTransferInfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(EquipTransferInfo equipTransferInfo, Model model) {
		model.addAttribute("equipTransferInfo", equipTransferInfo);
		return "modules/test/equipTransferInfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:equipTransferInfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<EquipTransferInfo> listData(EquipTransferInfo equipTransferInfo, HttpServletRequest request, HttpServletResponse response) {
		Page<EquipTransferInfo> page = equipTransferInfoService.findPage(new Page<EquipTransferInfo>(request, response), equipTransferInfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:equipTransferInfo:view")
	@RequestMapping(value = "form")
	public String form(EquipTransferInfo equipTransferInfo, Model model) {
		model.addAttribute("equipTransferInfo", equipTransferInfo);
		return "modules/test/equipTransferInfoForm";
	}

	/**
	 * 保存设备调拨
	 */
	@RequiresPermissions("test:equipTransferInfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated EquipTransferInfo equipTransferInfo) {
		equipTransferInfoService.save(equipTransferInfo);//先提交调拨设备，再修改设备状态
		EquipInfo equipInfo = new EquipInfo();
		equipInfo.setEquipId(equipTransferInfo.getEquipCode());
		List<EquipInfo> list = equipInfoService.findList(equipInfo);
		if (list.size()>0){
			EquipInfo equipInfo2 = list.get(0);//这个地方必须再声明一个EquipInfo对象来存放被调拨设备的实体对象，否则多次调拨不同的设备时会出现脏数据
			equipInfo2.setEquipStatus("3");//将调拨设备的改为调拨状态
			//equipInfoService.updateStatus(equipInfo);//这种方式更新不了设备状态
			equipInfoService.update(equipInfo2);
		}
		return renderResult(Global.TRUE, text("保存设备调拨成功！"));
	}
	
	/**
	 * 停用设备调拨
	 */
	@RequiresPermissions("test:equipTransferInfo:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(EquipTransferInfo equipTransferInfo) {
		equipTransferInfo.setStatus(EquipTransferInfo.STATUS_DISABLE);
		equipTransferInfoService.updateStatus(equipTransferInfo);
		return renderResult(Global.TRUE, text("停用设备调拨成功"));
	}
	
	/**
	 * 启用设备调拨
	 */
	@RequiresPermissions("test:equipTransferInfo:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(EquipTransferInfo equipTransferInfo) {
		equipTransferInfo.setStatus(EquipTransferInfo.STATUS_NORMAL);
		equipTransferInfoService.updateStatus(equipTransferInfo);
		return renderResult(Global.TRUE, text("启用设备调拨成功"));
	}
	
	/**
	 * 删除设备调拨
	 */
	@RequiresPermissions("test:equipTransferInfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(EquipTransferInfo equipTransferInfo) {
		equipTransferInfoService.delete(equipTransferInfo);
		return renderResult(Global.TRUE, text("删除设备调拨成功！"));
	}
	
}