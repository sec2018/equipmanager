/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.modules.test.entity.EquipScrappedInfo;
import com.jeesite.modules.test.service.EquipScrappedInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import java.util.Iterator;
import java.util.List;

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
		User user = UserUtils.getUser();
		Subject subject = UserUtils.getSubject();


		if(subject.hasRole("repairmen")){
			equipScrappedInfo.setScrappedApplicant(user.getLoginCode());
		}

		if(subject.hasRole("equipManager")){
			equipScrappedInfo.setScrappedApproval(user.getLoginCode());
		}



		Page<EquipScrappedInfo> page = equipScrappedInfoService.findPage(new Page<EquipScrappedInfo>(request, response), equipScrappedInfo);
		Iterator<EquipScrappedInfo> iterator =  page.getList().iterator();
		while (iterator.hasNext()){
			EquipScrappedInfo equipScrappedInfo2 = iterator.next();
			if(equipScrappedInfo2.getScrappedApproval()==null||equipScrappedInfo2.getScrappedApproval()==""){
				equipScrappedInfo2.setScrappedApproval(equipScrappedInfo2.getEquipInfo().getEquipManager());
				equipScrappedInfoService.update(equipScrappedInfo2);
			}else{

			}

		}
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
         //报废审批人设置 (有问题)

//		String approval = equipScrappedInfo.getEquipInfo().getEquipManager();
//		equipScrappedInfo.setScrappedApproval(approval);

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