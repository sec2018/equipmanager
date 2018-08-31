/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jeesite.modules.test.entity.ComponentInfo;
import com.jeesite.modules.test.entity.EquipInfo;
import com.jeesite.modules.test.service.ComponentInfoService;
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
import com.jeesite.modules.test.entity.PreventMaintainPlan;
import com.jeesite.modules.test.service.PreventMaintainPlanService;

/**
 * 预防性维护计划Controller
 * @author dyl
 * @version 2018-08-20
 */
@Controller
@RequestMapping(value = "${adminPath}/test/preventMaintainPlan")
public class PreventMaintainPlanController extends BaseController {

	@Autowired
	private PreventMaintainPlanService preventMaintainPlanService;

	 @Autowired
	 private ComponentInfoService componentInfoService;

	 @Autowired
	 private EquipInfoSelfService equipInfoSelfService;
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public PreventMaintainPlan get(String maintainPlanCode, boolean isNewRecord) {
		return preventMaintainPlanService.get(maintainPlanCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:preventMaintainPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(PreventMaintainPlan preventMaintainPlan, Model model) {
		model.addAttribute("preventMaintainPlan", preventMaintainPlan);
		return "modules/test/preventMaintainPlanList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:preventMaintainPlan:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<PreventMaintainPlan> listData(PreventMaintainPlan preventMaintainPlan) {
		if (StringUtils.isBlank(preventMaintainPlan.getParentCode())) {
			preventMaintainPlan.setParentCode(PreventMaintainPlan.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getMiantianTreeName())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getEquipCode())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getMiantainerCode())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getOilPart())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getOilWay())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getOilLabelCode())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getSupplyOilMass())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getSupplyCycle())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getChangeOilMass())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getChangeCycle())){
			preventMaintainPlan.setParentCode(null);
		}
		//手动修改，将Date类型转化为CharSequence
		if (StringUtils.isEmpty((CharSequence)preventMaintainPlan.getMaintainDate())){
			preventMaintainPlan.setParentCode(null);
		}
//		if (StringUtils.isNotBlank(preventMaintainPlan.getMaintainDate())){
//			preventMaintainPlan.setParentCode(null);
//		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getMaintainRunStatus())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getStatus())){
			preventMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(preventMaintainPlan.getRemarks())){
			preventMaintainPlan.setParentCode(null);
		}
		List<PreventMaintainPlan> list = preventMaintainPlanService.findList(preventMaintainPlan);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:preventMaintainPlan:view")
	@RequestMapping(value = "form")
	public String form(PreventMaintainPlan preventMaintainPlan, Model model) {
		//
		EquipInfo equipInfo = null;
		Set<String> equipInfoSet = equipInfoSelfService.findAllEquipId();
		List<EquipInfo> equipInfoList = new ArrayList<EquipInfo>();
		for (String s:equipInfoSet) {
			equipInfo = new EquipInfo();
			equipInfo.setEquipId(s);
			equipInfoList.add(equipInfo);
		}
		model.addAttribute("equipInfoList", equipInfoList);
		//获得预防性维护计划给油脂部位
		List<PreventMaintainPlan> preventMaintainPlanList = preventMaintainPlanService.findList(preventMaintainPlan);
		model.addAttribute("preventMaintainPlanList", preventMaintainPlanList);
		//从数据库获取所有备品备件信息传到前端显示
		ComponentInfo componentInfo = new ComponentInfo();
		List<ComponentInfo> componentInfoList = componentInfoService.findList(componentInfo);
		model.addAttribute("componentInfoList", componentInfoList);
		// 创建并初始化下一个节点信息
		preventMaintainPlan = createNextNode(preventMaintainPlan);
		model.addAttribute("preventMaintainPlan", preventMaintainPlan);
		return "modules/test/preventMaintainPlanForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("test:preventMaintainPlan:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public PreventMaintainPlan createNextNode(PreventMaintainPlan preventMaintainPlan) {
		if (StringUtils.isNotBlank(preventMaintainPlan.getParentCode())){
			preventMaintainPlan.setParent(preventMaintainPlanService.get(preventMaintainPlan.getParentCode()));
		}
		if (preventMaintainPlan.getIsNewRecord()) {
			PreventMaintainPlan where = new PreventMaintainPlan();
			where.setParentCode(preventMaintainPlan.getParentCode());
			PreventMaintainPlan last = preventMaintainPlanService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				preventMaintainPlan.setTreeSort(last.getTreeSort() + 30);
				preventMaintainPlan.setMaintainPlanCode(IdGen.nextCode(last.getMaintainPlanCode()));
			}else if (preventMaintainPlan.getParent() != null){
				preventMaintainPlan.setMaintainPlanCode(preventMaintainPlan.getParent().getMaintainPlanCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (preventMaintainPlan.getTreeSort() == null){
			preventMaintainPlan.setTreeSort(PreventMaintainPlan.DEFAULT_TREE_SORT);
		}
		return preventMaintainPlan;
	}

	/**
	 * 保存预防性维护计划
	 */
	@RequiresPermissions("test:preventMaintainPlan:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated PreventMaintainPlan preventMaintainPlan) {
		preventMaintainPlanService.save(preventMaintainPlan);
		return renderResult(Global.TRUE, text("保存预防性维护计划成功！"));
	}
	
	/**
	 * 停用预防性维护计划
	 */
	@RequiresPermissions("test:preventMaintainPlan:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(PreventMaintainPlan preventMaintainPlan) {
		PreventMaintainPlan where = new PreventMaintainPlan();
		where.setStatus(PreventMaintainPlan.STATUS_NORMAL);
		where.setParentCodes("," + preventMaintainPlan.getId() + ",");
		long count = preventMaintainPlanService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该预防性维护计划包含未停用的子预防性维护计划！"));
		}
		preventMaintainPlan.setStatus(PreventMaintainPlan.STATUS_DISABLE);
		preventMaintainPlanService.updateStatus(preventMaintainPlan);
		return renderResult(Global.TRUE, text("停用预防性维护计划成功"));
	}
	
	/**
	 * 启用预防性维护计划
	 */
	@RequiresPermissions("test:preventMaintainPlan:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(PreventMaintainPlan preventMaintainPlan) {
		preventMaintainPlan.setStatus(PreventMaintainPlan.STATUS_NORMAL);
		preventMaintainPlanService.updateStatus(preventMaintainPlan);
		return renderResult(Global.TRUE, text("启用预防性维护计划成功"));
	}
	
	/**
	 * 删除预防性维护计划
	 */
	@RequiresPermissions("test:preventMaintainPlan:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(PreventMaintainPlan preventMaintainPlan) {
		preventMaintainPlanService.delete(preventMaintainPlan);
		return renderResult(Global.TRUE, text("删除预防性维护计划成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("test:preventMaintainPlan:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<PreventMaintainPlan> list = preventMaintainPlanService.findList(new PreventMaintainPlan());
		for (int i=0; i<list.size(); i++){
			PreventMaintainPlan e = list.get(i);
			// 过滤非正常的数据
			if (!PreventMaintainPlan.STATUS_NORMAL.equals(e.getStatus())){
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
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getMaintainPlanCode(), e.getMiantianTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("test:preventMaintainPlan:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(PreventMaintainPlan preventMaintainPlan){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		preventMaintainPlanService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}