/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import com.jeesite.common.entity.Extend;
import java.util.Date;
import java.util.List;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 定修计划维护Entity
 * @author dyl
 * @version 2018-08-21
 */
@Table(name="regular_maintain_plan", alias="a", columns={
		@Column(name="regular_plan_code", attrName="regularPlanCode", label="定修计划编号", isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="regular_tree_name", attrName="regularTreeName", label="定修节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="equip_code", attrName="equipCode", label="设备编号"),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="regular_maintain_part", attrName="regularMaintainPart", label="定修部位"),
		@Column(name="regular_maintain_content", attrName="regularMaintainContent", label="定修内容"),
		@Column(name="regular_maintain_goal", attrName="regularMaintainGoal", label="定修目标"),
		@Column(name="regular_maintain_cycle", attrName="regularMaintainCycle", label="定修周期", comment="定修周期（0-月，1-季度，2-年）"),
		@Column(name="maintainer_code", attrName="maintainerCode", label="定修员编号"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.tree_sorts, a.regular_plan_code"
)
public class RegularMaintainPlan extends TreeEntity<RegularMaintainPlan> {
	
	private static final long serialVersionUID = 1L;
	private String regularPlanCode;		// 定修计划编号
	private String regularTreeName;		// 定修节点名称
	private String equipCode;		// 设备编号
	private String componentCode;		// 备品备件编号
	private String regularMaintainPart;		// 定修部位
	private String regularMaintainContent;		// 定修内容
	private String regularMaintainGoal;		// 定修目标
	private String regularMaintainCycle;		// 定修周期（0-月，1-季度，2-年）
	private String maintainerCode;		// 定修员编号
	private Extend extend;		// 扩展字段
	//test code by dang
	//private EquipInfo equipInfo;
	//private String[] equipPlans;
	//private List<EquipInfo> equipInfoList;
//	private List<EquipPlan> equipPlanList;
//
//	public List<EquipPlan> getEquipPlanList() {
//		return equipPlanList;
//	}
//
//	public void setEquipPlanList(List<EquipPlan> equipPlanList) {
//		this.equipPlanList = equipPlanList;
//	}
//	public List<EquipInfo> getEquipInfoList() {
//		return equipInfoList;
//	}
//
//	public void setEquipInfoList(List<EquipInfo> equipInfoList) {
//		this.equipInfoList = equipInfoList;
//	}

//	public String[] getEquipPlans(){
//		List<String> list = ListUtils.extractToList(equipPlanList, "equipCode");
//		return (String[])list.toArray(new String[list.size()]);
//	}
//
//	public void setEquipPlans(String[] equipPlans) {
//		String[] var2 = equipPlans;
//		int var3 = equipPlans.length;
//		for(int var4 = 0; var4 < var3; ++var4) {
//			String val = var2[var4];
//			if (StringUtils.isNotBlank(val)) {
//				EquipPlan equipPlan = new EquipPlan();
//				equipPlan.setEquipCode(val);
//				equipPlanList.add(equipPlan);
//			}
//		}
//	}

//	public EquipInfo getEquipInfo() {
//		return equipInfo;
//	}
//
//	public void setEquipInfo(EquipInfo equipInfo) {
//		this.equipInfo = equipInfo;
//	}
	
	public RegularMaintainPlan() {
		this(null);
	}

	public RegularMaintainPlan(String id){
		super(id);
	}
	
	@Override
	public RegularMaintainPlan getParent() {
		return parent;
	}

	@Override
	public void setParent(RegularMaintainPlan parent) {
		this.parent = parent;
	}
	
	public String getRegularPlanCode() {
		return regularPlanCode;
	}

	public void setRegularPlanCode(String regularPlanCode) {
		this.regularPlanCode = regularPlanCode;
	}
	
	@Length(min=0, max=200, message="定修节点名称长度不能超过 200 个字符")
	public String getRegularTreeName() {
		return regularTreeName;
	}

	public void setRegularTreeName(String regularTreeName) {
		this.regularTreeName = regularTreeName;
	}
	
	@Length(min=0, max=64, message="设备编号长度不能超过 64 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=64, message="备品备件编号长度不能超过 64 个字符")
	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	@Length(min=0, max=255, message="定修部位长度不能超过 255 个字符")
	public String getRegularMaintainPart() {
		return regularMaintainPart;
	}

	public void setRegularMaintainPart(String regularMaintainPart) {
		this.regularMaintainPart = regularMaintainPart;
	}
	
	@Length(min=0, max=255, message="定修内容长度不能超过 255 个字符")
	public String getRegularMaintainContent() {
		return regularMaintainContent;
	}

	public void setRegularMaintainContent(String regularMaintainContent) {
		this.regularMaintainContent = regularMaintainContent;
	}
	
	@Length(min=0, max=255, message="定修目标长度不能超过 255 个字符")
	public String getRegularMaintainGoal() {
		return regularMaintainGoal;
	}

	public void setRegularMaintainGoal(String regularMaintainGoal) {
		this.regularMaintainGoal = regularMaintainGoal;
	}
	
	@Length(min=0, max=1, message="定修周期长度不能超过 1 个字符")
	public String getRegularMaintainCycle() {
		return regularMaintainCycle;
	}

	public void setRegularMaintainCycle(String regularMaintainCycle) {
		this.regularMaintainCycle = regularMaintainCycle;
	}
	
	@Length(min=0, max=64, message="定修员编号长度不能超过 64 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}