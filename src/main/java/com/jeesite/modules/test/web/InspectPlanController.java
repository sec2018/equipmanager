/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.jeesite.modules.test.entity.EquipInfo;
//import com.jeesite.modules.test.service.EquipInfoSelfService;
import com.jeesite.modules.test.service.EquipInfoService;
import com.jeesite.modules.test.service.selfService.EquipInfoSelfService;
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
import com.jeesite.modules.test.entity.InspectPlan;
import com.jeesite.modules.test.service.InspectPlanService;

/**
 * 巡检计划Controller
 * @author dyl
 * @version 2018-08-17
 */
@Controller
@RequestMapping(value = "${adminPath}/test/inspectPlan")
public class InspectPlanController extends BaseController {

	@Autowired
	private InspectPlanService inspectPlanService;

	@Autowired
	private EquipInfoService equipInfoService;

	@Autowired
	private EquipInfoSelfService equipInfoSelfService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public InspectPlan get(String inspectPlanCode, boolean isNewRecord) {
		return inspectPlanService.get(inspectPlanCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:inspectPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(InspectPlan inspectPlan, Model model) {
		model.addAttribute("inspectPlan", inspectPlan);
		return "modules/test/inspectPlanList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:inspectPlan:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<InspectPlan> listData(InspectPlan inspectPlan) {
		if (StringUtils.isBlank(inspectPlan.getParentCode())) {
			inspectPlan.setParentCode(InspectPlan.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectPlanName())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getTreeName())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspecterRodeCode())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectRodeName())){
			inspectPlan.setParentCode(null);
		}
		//手动修改，将Date类型转化为CharSequence
		if (StringUtils.isEmpty((CharSequence)inspectPlan.getInspectPlanDate())){
			inspectPlan.setParentCode(null);
		}
//		if (StringUtils.isNotBlank(inspectPlan.getInspectPlanDate())){
//			inspectPlan.setParentCode(null);
//		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectPart())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectContent())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectGoal())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectCycle())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectMehod())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspectRunStatus())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getInspecterCode())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getStatus())){
			inspectPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(inspectPlan.getRemarks())){
			inspectPlan.setParentCode(null);
		}
		List<InspectPlan> list = inspectPlanService.findList(inspectPlan);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:inspectPlan:view")
	@RequestMapping(value = "form")
	public String form(InspectPlan inspectPlan, Model model) {
		//从数据库查询设备列表信息传到前端显示
//		EquipInfo equipInfo = new EquipInfo();
		Set<String> equipInfoList = equipInfoSelfService.findEquipAllType();
		model.addAttribute("equipInfoList", equipInfoList);
		//查询设备部位信息传到前端
		List<InspectPlan> inspectPlanList = inspectPlanService.findList(inspectPlan);
		model.addAttribute("inspectPlanList", inspectPlanList);
		// 创建并初始化下一个节点信息
		inspectPlan = createNextNode(inspectPlan);
		model.addAttribute("inspectPlan", inspectPlan);
		return "modules/test/inspectPlanForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("test:inspectPlan:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public InspectPlan createNextNode(InspectPlan inspectPlan) {
		if (StringUtils.isNotBlank(inspectPlan.getParentCode())){
			inspectPlan.setParent(inspectPlanService.get(inspectPlan.getParentCode()));
		}
		if (inspectPlan.getIsNewRecord()) {
			InspectPlan where = new InspectPlan();
			where.setParentCode(inspectPlan.getParentCode());
			InspectPlan last = inspectPlanService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				inspectPlan.setTreeSort(last.getTreeSort() + 30);
				inspectPlan.setInspectPlanCode(IdGen.nextCode(last.getInspectPlanCode()));
			}else if (inspectPlan.getParent() != null){
				inspectPlan.setInspectPlanCode(inspectPlan.getParent().getInspectPlanCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (inspectPlan.getTreeSort() == null){
			inspectPlan.setTreeSort(InspectPlan.DEFAULT_TREE_SORT);
		}
		return inspectPlan;
	}

	/**
	 * 保存巡检计划
	 */
	@RequiresPermissions("test:inspectPlan:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated InspectPlan inspectPlan) {
		inspectPlanService.save(inspectPlan);
		return renderResult(Global.TRUE, text("保存巡检计划成功！"));
	}
	
	/**
	 * 停用巡检计划
	 */
	@RequiresPermissions("test:inspectPlan:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(InspectPlan inspectPlan) {
		InspectPlan where = new InspectPlan();
		where.setStatus(InspectPlan.STATUS_NORMAL);
		where.setParentCodes("," + inspectPlan.getId() + ",");
		long count = inspectPlanService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该巡检计划包含未停用的子巡检计划！"));
		}
		inspectPlan.setStatus(InspectPlan.STATUS_DISABLE);
		inspectPlanService.updateStatus(inspectPlan);
		return renderResult(Global.TRUE, text("停用巡检计划成功"));
	}
	
	/**
	 * 启用巡检计划
	 */
	@RequiresPermissions("test:inspectPlan:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(InspectPlan inspectPlan) {
		inspectPlan.setStatus(InspectPlan.STATUS_NORMAL);
		inspectPlanService.updateStatus(inspectPlan);
		return renderResult(Global.TRUE, text("启用巡检计划成功"));
	}
	
	/**
	 * 删除巡检计划
	 */
	@RequiresPermissions("test:inspectPlan:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(InspectPlan inspectPlan) {
		inspectPlanService.delete(inspectPlan);
		return renderResult(Global.TRUE, text("删除巡检计划成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("test:inspectPlan:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<InspectPlan> list = inspectPlanService.findList(new InspectPlan());
		for (int i=0; i<list.size(); i++){
			InspectPlan e = list.get(i);
			// 过滤非正常的数据
			if (!InspectPlan.STATUS_NORMAL.equals(e.getStatus())){
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
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getInspectPlanCode(), e.getTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("test:inspectPlan:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(InspectPlan inspectPlan){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		inspectPlanService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}