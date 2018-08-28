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
import com.jeesite.modules.test.entity.RegularMaintainPlan;
import com.jeesite.modules.test.service.RegularMaintainPlanService;

/**
 * 定修计划维护Controller
 * @author dyl
 * @version 2018-08-21
 */
@Controller
@RequestMapping(value = "${adminPath}/test/regularMaintainPlan")
public class RegularMaintainPlanController extends BaseController {

	@Autowired
	private RegularMaintainPlanService regularMaintainPlanService;
	@Autowired
	private EquipInfoService equipInfoService;//注入设备信息的service
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public RegularMaintainPlan get(String regularPlanCode, boolean isNewRecord) {
		return regularMaintainPlanService.get(regularPlanCode, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("test:regularMaintainPlan:view")
	@RequestMapping(value = {"list", ""})
	public String list(RegularMaintainPlan regularMaintainPlan, Model model) {
		model.addAttribute("regularMaintainPlan", regularMaintainPlan);
		return "modules/test/regularMaintainPlanList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("test:regularMaintainPlan:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public List<RegularMaintainPlan> listData(RegularMaintainPlan regularMaintainPlan) {
		if (StringUtils.isBlank(regularMaintainPlan.getParentCode())) {
			regularMaintainPlan.setParentCode(RegularMaintainPlan.ROOT_CODE);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getRegularTreeName())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getEquipCode())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getComponentCode())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getRegularMaintainPart())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getRegularMaintainContent())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getRegularMaintainGoal())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getRegularMaintainCycle())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getMaintainerCode())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getStatus())){
			regularMaintainPlan.setParentCode(null);
		}
		if (StringUtils.isNotBlank(regularMaintainPlan.getRemarks())){
			regularMaintainPlan.setParentCode(null);
		}
		List<RegularMaintainPlan> list = regularMaintainPlanService.findList(regularMaintainPlan);
		return list;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("test:regularMaintainPlan:view")
	@RequestMapping(value = "form")
	public String form(RegularMaintainPlan regularMaintainPlan, Model model) {
		//初始化设备信息实例作为查询条件
		EquipInfo equipInfo = new EquipInfo();
		//查询所有的设备信息
		List<EquipInfo> equipList = equipInfoService.findList(equipInfo);
		//将设备列表信息传入前台
		model.addAttribute("equipList", equipList);
		// 创建并初始化下一个节点信息
		regularMaintainPlan = createNextNode(regularMaintainPlan);
		model.addAttribute("regularMaintainPlan", regularMaintainPlan);
		return "modules/test/regularMaintainPlanForm";
	}
	
	/**
	 * 创建并初始化下一个节点信息，如：排序号、默认值
	 */
	@RequiresPermissions("test:regularMaintainPlan:edit")
	@RequestMapping(value = "createNextNode")
	@ResponseBody
	public RegularMaintainPlan createNextNode(RegularMaintainPlan regularMaintainPlan) {
		if (StringUtils.isNotBlank(regularMaintainPlan.getParentCode())){
			regularMaintainPlan.setParent(regularMaintainPlanService.get(regularMaintainPlan.getParentCode()));
		}
		if (regularMaintainPlan.getIsNewRecord()) {
			RegularMaintainPlan where = new RegularMaintainPlan();
			where.setParentCode(regularMaintainPlan.getParentCode());
			RegularMaintainPlan last = regularMaintainPlanService.getLastByParentCode(where);
			// 获取到下级最后一个节点
			if (last != null){
				regularMaintainPlan.setTreeSort(last.getTreeSort() + 30);
				regularMaintainPlan.setRegularPlanCode(IdGen.nextCode(last.getRegularPlanCode()));
			}else if (regularMaintainPlan.getParent() != null){
				regularMaintainPlan.setRegularPlanCode(regularMaintainPlan.getParent().getRegularPlanCode() + "001");
			}
		}
		// 以下设置表单默认数据
		if (regularMaintainPlan.getTreeSort() == null){
			regularMaintainPlan.setTreeSort(RegularMaintainPlan.DEFAULT_TREE_SORT);
		}
		return regularMaintainPlan;
	}

	/**
	 * 保存定修计划维护
	 */
	@RequiresPermissions("test:regularMaintainPlan:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated RegularMaintainPlan regularMaintainPlan) {
		regularMaintainPlanService.save(regularMaintainPlan);
		return renderResult(Global.TRUE, text("保存定修计划维护成功！"));
	}
	
	/**
	 * 停用定修计划维护
	 */
	@RequiresPermissions("test:regularMaintainPlan:edit")
	@RequestMapping(value = "disable")
	@ResponseBody
	public String disable(RegularMaintainPlan regularMaintainPlan) {
		RegularMaintainPlan where = new RegularMaintainPlan();
		where.setStatus(RegularMaintainPlan.STATUS_NORMAL);
		where.setParentCodes("," + regularMaintainPlan.getId() + ",");
		long count = regularMaintainPlanService.findCount(where);
		if (count > 0) {
			return renderResult(Global.FALSE, text("该定修计划维护包含未停用的子定修计划维护！"));
		}
		regularMaintainPlan.setStatus(RegularMaintainPlan.STATUS_DISABLE);
		regularMaintainPlanService.updateStatus(regularMaintainPlan);
		return renderResult(Global.TRUE, text("停用定修计划维护成功"));
	}
	
	/**
	 * 启用定修计划维护
	 */
	@RequiresPermissions("test:regularMaintainPlan:edit")
	@RequestMapping(value = "enable")
	@ResponseBody
	public String enable(RegularMaintainPlan regularMaintainPlan) {
		regularMaintainPlan.setStatus(RegularMaintainPlan.STATUS_NORMAL);
		regularMaintainPlanService.updateStatus(regularMaintainPlan);
		return renderResult(Global.TRUE, text("启用定修计划维护成功"));
	}
	
	/**
	 * 删除定修计划维护
	 */
	@RequiresPermissions("test:regularMaintainPlan:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(RegularMaintainPlan regularMaintainPlan) {
		regularMaintainPlanService.delete(regularMaintainPlan);
		return renderResult(Global.TRUE, text("删除定修计划维护成功！"));
	}
	
	/**
	 * 获取树结构数据
	 * @param excludeCode 排除的Code
	 * @param isShowCode 是否显示编码（true or 1：显示在左侧；2：显示在右侧；false or null：不显示）
	 * @return
	 */
	@RequiresPermissions("test:regularMaintainPlan:view")
	@RequestMapping(value = "treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData(String excludeCode, String isShowCode) {
		List<Map<String, Object>> mapList = ListUtils.newArrayList();
		List<RegularMaintainPlan> list = regularMaintainPlanService.findList(new RegularMaintainPlan());
		for (int i=0; i<list.size(); i++){
			RegularMaintainPlan e = list.get(i);
			// 过滤非正常的数据
			if (!RegularMaintainPlan.STATUS_NORMAL.equals(e.getStatus())){
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
			map.put("name", StringUtils.getTreeNodeName(isShowCode, e.getRegularPlanCode(), e.getRegularTreeName()));
			mapList.add(map);
		}
		return mapList;
	}

	/**
	 * 修复表结构相关数据
	 */
	@RequiresPermissions("test:regularMaintainPlan:edit")
	@RequestMapping(value = "fixTreeData")
	@ResponseBody
	public String fixTreeData(RegularMaintainPlan regularMaintainPlan){
		if (!UserUtils.getUser().isAdmin()){
			return renderResult(Global.FALSE, "操作失败，只有管理员才能进行修复！");
		}
		regularMaintainPlanService.fixTreeData();
		return renderResult(Global.TRUE, "数据修复成功");
	}
	
}