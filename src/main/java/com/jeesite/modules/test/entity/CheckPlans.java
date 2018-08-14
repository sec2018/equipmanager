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
 * 点检计划Entity
 * @author jyf
 * @version 2018-08-14
 */
@Table(name="check_plans", alias="a", columns={
		@Column(name="chack_plan_code", attrName="chackPlanCode", label="点检计划编号", isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="tree_name", attrName="treeName", label="节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="equip_code", attrName="equipCode", label="设备编号"),
		@Column(name="check_part", attrName="checkPart", label="点检部位" ,queryType=QueryType.LIKE),
		@Column(name="check_content", attrName="checkContent", label="点检内容" ,queryType=QueryType.LIKE),
		@Column(name="check_goal", attrName="checkGoal", label="点检目标",queryType=QueryType.LIKE),
		@Column(name="check_cycle", attrName="checkCycle", label="点检周期"),
		@Column(name="check_method", attrName="checkMethod", label="点检方法",queryType=QueryType.LIKE),
		@Column(name="check_run_status", attrName="checkRunStatus", label="点检时设备运行状态"),
		@Column(name="checker_code", attrName="checkerCode", label="点检员编号"),
		@Column(name="check_date", attrName="checkDate", label="点检时间" ,queryType=QueryType.LTE),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.tree_sorts, a.chack_plan_code"
)
public class CheckPlans extends TreeEntity<CheckPlans> {
	
	private static final long serialVersionUID = 1L;
	private String chackPlanCode;		// 点检计划编号
	private String treeName;		// 节点名称
	private String equipCode;		// 设备编号
	private String checkPart;		// 点检部位
	private String checkContent;		// 点检内容
	private String checkGoal;		// 点检目标
	private String checkCycle;		// 点检周期
	private String checkMethod;		// 点检方法
	private String checkRunStatus;		// 点检时设备运行状态
	private String checkerCode;		// 点检员编号
	private Date checkDate;		// 点检时间
	private Extend extend;		// 扩展字段
	
	public CheckPlans() {
		this(null);
	}

	public CheckPlans(String id){
		super(id);
	}
	
	@Override
	public CheckPlans getParent() {
		return parent;
	}

	@Override
	public void setParent(CheckPlans parent) {
		this.parent = parent;
	}
	
	public String getChackPlanCode() {
		return chackPlanCode;
	}

	public void setChackPlanCode(String chackPlanCode) {
		this.chackPlanCode = chackPlanCode;
	}
	
	@Length(min=0, max=200, message="节点名称长度不能超过 200 个字符")
	public String getTreeName() {
		return treeName;
	}

	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}
	
	@Length(min=0, max=64, message="设备编号长度不能超过 64 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=50, message="点检部位长度不能超过 50 个字符")
	public String getCheckPart() {
		return checkPart;
	}

	public void setCheckPart(String checkPart) {
		this.checkPart = checkPart;
	}
	
	@Length(min=0, max=50, message="点检内容长度不能超过 50 个字符")
	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
	}
	
	@Length(min=0, max=255, message="点检目标长度不能超过 255 个字符")
	public String getCheckGoal() {
		return checkGoal;
	}

	public void setCheckGoal(String checkGoal) {
		this.checkGoal = checkGoal;
	}
	
	@Length(min=0, max=64, message="点检周期长度不能超过 64 个字符")
	public String getCheckCycle() {
		return checkCycle;
	}

	public void setCheckCycle(String checkCycle) {
		this.checkCycle = checkCycle;
	}
	
	@Length(min=0, max=50, message="点检方法长度不能超过 50 个字符")
	public String getCheckMethod() {
		return checkMethod;
	}

	public void setCheckMethod(String checkMethod) {
		this.checkMethod = checkMethod;
	}
	
	@Length(min=0, max=1, message="点检时设备运行状态长度不能超过 1 个字符")
	public String getCheckRunStatus() {
		return checkRunStatus;
	}

	public void setCheckRunStatus(String checkRunStatus) {
		this.checkRunStatus = checkRunStatus;
	}
	
	@Length(min=0, max=255, message="点检员编号长度不能超过 255 个字符")
	public String getCheckerCode() {
		return checkerCode;
	}

	public void setCheckerCode(String checkerCode) {
		this.checkerCode = checkerCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}