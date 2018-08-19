/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.Extend;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.TreeEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 巡检计划Entity
 * @author dyl
 * @version 2018-08-17
 */
@Table(name="inspect_plan", alias="a", columns={
		@Column(name="inspect_plan_code", attrName="inspectPlanCode", label="巡检计划编号", isPK=true),
		@Column(name="inspect_plan_name", attrName="inspectPlanName", label="巡检计划名称", queryType=QueryType.LIKE),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="tree_name", attrName="treeName", label="节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="inspecter_rode_code", attrName="inspecterRodeCode", label="巡检路径编号"),
		@Column(name="inspect_rode_name", attrName="inspectRodeName", label="巡检路径名称", queryType=QueryType.LIKE),
		@Column(name="inspect_plan_date", attrName="inspectPlanDate", label="计划巡检时间"),
		@Column(name="inspect_part", attrName="inspectPart", label="巡检部位"),
		@Column(name="inspect_content", attrName="inspectContent", label="巡检内容"),
		@Column(name="inspect_goal", attrName="inspectGoal", label="巡检目标"),
		@Column(name="inspect_cycle", attrName="inspectCycle", label="巡检周期"),
		@Column(name="inspect_mehod", attrName="inspectMehod", label="巡检方法"),
		@Column(name="inspect_run_status", attrName="inspectRunStatus", label="巡检时设备状态", comment="巡检时设备状态（0-运行、1-停止）"),
		@Column(name="inspecter_code", attrName="inspecterCode", label="巡检员编号"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = EquipInfo.class, attrName = "equipInfo", alias = "e",
				on = "e.inspect_plan_code = a.inspect_plan_code", columns = {
				@Column(name = "equip_id", label = "设备编号", queryType = QueryType.LIKE)
		})
}, orderBy="a.tree_sorts, a.inspect_plan_code"
)
public class InspectPlan extends TreeEntity<InspectPlan> {
	
	private static final long serialVersionUID = 1L;
	private String inspectPlanCode;		// 巡检计划编号
	private String inspectPlanName;		// 巡检计划名称
	private String treeName;		// 节点名称
	private String inspecterRodeCode;		// 巡检路径编号
	private String inspectRodeName;		// 巡检路径名称
	private Date inspectPlanDate;		// 计划巡检时间
	private String inspectPart;		// 巡检部位
	private String inspectContent;		// 巡检内容
	private String inspectGoal;		// 巡检目标
	private String inspectCycle;		// 巡检周期
	private String inspectMehod;		// 巡检方法
	private String inspectRunStatus;		// 巡检时设备状态（0-运行、1-停止）
	private String inspecterCode;		// 巡检员编号
	private Extend extend;		// 扩展字段
	private EquipInfo equipInfo;
	public EquipInfo getEquipInfo() { return equipInfo; }

	public void setEquipInfo(EquipInfo equipInfo) { this.equipInfo = equipInfo; }
	
	public InspectPlan() {
		this(null);
	}

	public InspectPlan(String id){
		super(id);
	}
	
	@Override
	public InspectPlan getParent() {
		return parent;
	}

	@Override
	public void setParent(InspectPlan parent) {
		this.parent = parent;
	}
	
	public String getInspectPlanCode() {
		return inspectPlanCode;
	}

	public void setInspectPlanCode(String inspectPlanCode) {
		this.inspectPlanCode = inspectPlanCode;
	}
	
	@Length(min=0, max=255, message="巡检计划名称长度不能超过 255 个字符")
	public String getInspectPlanName() {
		return inspectPlanName;
	}

	public void setInspectPlanName(String inspectPlanName) {
		this.inspectPlanName = inspectPlanName;
	}
	
	@Length(min=0, max=200, message="节点名称长度不能超过 200 个字符")
	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	
	@Length(min=0, max=255, message="巡检路径编号长度不能超过 255 个字符")
	public String getInspecterRodeCode() {
		return inspecterRodeCode;
	}

	public void setInspecterRodeCode(String inspecterRodeCode) {
		this.inspecterRodeCode = inspecterRodeCode;
	}
	
	@Length(min=0, max=255, message="巡检路径名称长度不能超过 255 个字符")
	public String getInspectRodeName() {
		return inspectRodeName;
	}

	public void setInspectRodeName(String inspectRodeName) {
		this.inspectRodeName = inspectRodeName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectPlanDate() {
		return inspectPlanDate;
	}

	public void setInspectPlanDate(Date inspectPlanDate) {
		this.inspectPlanDate = inspectPlanDate;
	}
	
	@Length(min=0, max=255, message="巡检部位长度不能超过 255 个字符")
	public String getInspectPart() {
		return inspectPart;
	}

	public void setInspectPart(String inspectPart) {
		this.inspectPart = inspectPart;
	}
	
	@Length(min=0, max=255, message="巡检内容长度不能超过 255 个字符")
	public String getInspectContent() {
		return inspectContent;
	}

	public void setInspectContent(String inspectContent) {
		this.inspectContent = inspectContent;
	}
	
	@Length(min=0, max=255, message="巡检目标长度不能超过 255 个字符")
	public String getInspectGoal() {
		return inspectGoal;
	}

	public void setInspectGoal(String inspectGoal) {
		this.inspectGoal = inspectGoal;
	}
	
	@Length(min=0, max=64, message="巡检周期长度不能超过 64 个字符")
	public String getInspectCycle() {
		return inspectCycle;
	}

	public void setInspectCycle(String inspectCycle) {
		this.inspectCycle = inspectCycle;
	}
	
	@Length(min=0, max=255, message="巡检方法长度不能超过 255 个字符")
	public String getInspectMehod() {
		return inspectMehod;
	}

	public void setInspectMehod(String inspectMehod) {
		this.inspectMehod = inspectMehod;
	}
	
	@Length(min=0, max=1, message="巡检时设备状态长度不能超过 1 个字符")
	public String getInspectRunStatus() {
		return inspectRunStatus;
	}

	public void setInspectRunStatus(String inspectRunStatus) {
		this.inspectRunStatus = inspectRunStatus;
	}
	
	@Length(min=0, max=64, message="巡检员编号长度不能超过 64 个字符")
	public String getInspecterCode() {
		return inspecterCode;
	}

	public void setInspecterCode(String inspecterCode) {
		this.inspecterCode = inspecterCode;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}