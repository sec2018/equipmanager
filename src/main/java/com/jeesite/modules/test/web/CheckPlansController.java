/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import java.util.List;
import java.util.Map;

import com.jeesite.modules.test.entity.EquipInfo;
import com.jeesite.modules.test.service.EquipInfoService;
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
import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.collect.MapUtils;
import com.jeesite.common.lang.StringUtils;
import com.jeesite.common.idgen.IdGen;
import com.jeesite.modules.sys.utils.UserUtils;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.test.entity.CheckPlans;
import com.jeesite.modules.test.service.CheckPlansService;

/**
 * 点检计划Controller
 * @author jyf
 * @version 2018-08-14
 */
@Controller
@RequestMapping(value = "${adminPath}/test/checkPlans")
public class CheckPlansController extends BaseController {

	@Autowired
	private CheckPlansService checkPlansService;

	@Autowired
	private EquipInfoService equipInfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public CheckPlans get(String chackPlanCode, boolean isNewRecord) {
		return checkPlansService.get(chackPlanCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:checkPlans:view")
	@RequestMapping(value = {"list", ""})
	public String list(CheckPlans checkPlans, Model model) {
		model.addAttribute("checkPlans", checkPlans);
		return "modules/test/checkPlansList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:checkPlans:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<CheckPlans> listData(CheckPlans checkPlans) {
		if (StringUtils.isBlank(checkPlans.getParentCode())) {
			checkPlans.setParentCode(CheckPlans.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(checkPlans.getTreeName())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getEquipCode())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckPart())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckContent())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckGoal())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckCycle())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckMethod())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckRunStatus())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getCheckerCode())){
			checkPlans.setParentCode(null);
		}
		//手动修改，将Date类型转化为CharSequence
		if(StringUtils.isEmpty((CharSequence)checkPlans.getCheckDate())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getStatus())){
			checkPlans.setParentCode(null);
		}
		if (StringUtils.isNotBlank(checkPlans.getRemarks())){
			checkPlans.setParentCode(null);
		}
		List<CheckPlans> list = checkPlansService.findList(checkPlans);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:checkPlans:view")
	@RequestMapping(value = "form")
	public String form(CheckPlans checkPlans, Model model) {
		//初始化设备信息实例作为查询条件
		EquipInfo equipInfo = new EquipInfo();
		//查询所有设备信息
		List<EquipInfo> equipInfoList = equipInfoService.findList(equipInfo);
		//将设备信息传到前端
		model.addAttribute("equipInfoList", equipInfoList);
		//查询设备部位信息传到前端
		List<CheckPlans> checkPlansList = checkPlansService.findList(checkPlans);
		model.addAttribute("checkPlansList", checkPlansList);
		// 创建并初始化下一个节点信息
		checkPlans = createNextNode(checkPlans);
		model.addAttribute("checkPlans", checkPlans);
		return "modules/test/checkPlansForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("test:checkPlans:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public CheckPlans createNextNode(CheckPlans checkPlans) {
		if (StringUtils.isNotBlank(checkPlans.getParentCode())){
			checkPlans.setParent(checkPlansService.get(checkPlans.getParentCode()));
		}
		if (checkPlans.getIsNewRecord()) {
			CheckPlans where = new CheckPlans();
			where.setParentCode(checkPlans.getParentCode());
			CheckPlans last = checkPlansService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				checkPlans.setTreeSort(last.getTreeSort() + 30);
				checkPlans.setChackPlanCode(IdGen.nextCode(last.getChackPlanCode()));
			}else if (checkPlans.getParent() != null){
				checkPlans.setChackPlanCode(checkPlans.getParent().getChackPlanCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (checkPlans.getTreeSort() == null){
			checkPlans.setTreeSort(CheckPlans.DEFAULT_TREE_SORT);
		}
		return checkPlans;
	}

	/**
	 * 保存点检计划
	 */
	@RequiresPermissions("test:checkPlans:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated CheckPlans checkPlans) {
		checkPlansService.save(checkPlans);
		return renderResult(Global.TRUE, text("保存点检计划成功！"));
	}
	
	/**
	 * 停用点检计划
	 */
	@RequiresPermissions("test:checkPlans:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(CheckPlans checkPlans) {
		CheckPlans where = new CheckPlans();
		where.setStatus(CheckPlans.STATUS_NORMAL);
		where.setParentCodes("," + checkPlans.getId() + ",");
		long count = checkPlansService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该点检计划包含未停用的子点检计划！"));
		}
		checkPlans.setStatus(CheckPlans.STATUS_DISABLE);
		checkPlansService.updateStatus(checkPlans);
		return renderResult(Global.TRUE, text("停用点检计划成功"));
	}
	
	/**
	 * 启用点检计划
	 */
	@RequiresPermissions("test:checkPlans:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(CheckPlans checkPlans) {
		checkPlans.setStatus(CheckPlans.STATUS_NORMAL);
		checkPlansService.updateStatus(checkPlans);
		return renderResult(Global.TRUE, text("启用点检计划成功"));
	}
	
	/**
	 * 删除点检计划
	 */
	@RequiresPermissions("test:checkPlans:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(CheckPlans checkPlans) {
		checkPlansService.delete(checkPlans);
		return renderResult(Global.TRUE, text("删除点检计划成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("test:checkPlans:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<CheckPlans> list = checkPlansService.findList(new CheckPlans());
		for (int i=0; i<list.size(); i++){
			CheckPlans e = list.get(i);
			// 过滤非正常的数据
			if (!CheckPlans.STATUS_NORMAL.equals(e.getStatus())){
				continue;
			}
			// 过滤被排除的编码（包括所有子级）
			if (StringUtils.isNotBlank(excludeCode)){
				if (e.getId().equals(excludeCode)){
					continue;
				}
				if (e.getParentCodes().contains("," + excludeCode + ",")){
					continue;
				}
			}
			Map<String, Object> map = MapUtils.newHashMap();
			map.put("id", e.getId());
			map.put("pId", e.getParentCode());
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getChackPlanCode(), e.getTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("test:checkPlans:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(CheckPlans checkPlans){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		checkPlansService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}