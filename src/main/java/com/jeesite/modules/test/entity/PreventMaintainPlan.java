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
 * 预防性维护计划Entity
 * @author dyl
 * @version 2018-08-20
 */
@Table(name="prevent_maintain_plan", alias="a", columns={
		@Column(name="maintain_plan_code", attrName="maintainPlanCode", label="维护计划编号", isPK=true),
		@Column(includeEntity=TreeEntity.class),
		@Column(name="miantian_tree_name", attrName="miantianTreeName", label="维护节点名称", queryType=QueryType.LIKE, isTreeName=true),
		@Column(name="equip_code", attrName="equipCode", label="设备编号"),
		@Column(name="miantainer_code", attrName="miantainerCode", label="维护人员编号"),
		@Column(name="oil_part", attrName="oilPart", label="给油脂部位"),
		@Column(name="oil_way", attrName="oilWay", label="给油脂方式", comment="给油脂方式（0-手动，1-油枪，2-螺丝刀）"),
		@Column(name="oil_label_code", attrName="oilLabelCode", label="润滑油牌号"),
		@Column(name="supply_oil_mass", attrName="supplyOilMass", label="补充油量"),
		@Column(name="supply_cycle", attrName="supplyCycle", label="补充周期", comment="补充周期（0-20天，1-45天，2-60天，3-90天）"),
		@Column(name="change_oil_mass", attrName="changeOilMass", label="更换油量"),
		@Column(name="change_cycle", attrName="changeCycle", label="更换周期", comment="更换周期（0-1Y，2-2Y，3-3Y）"),
		@Column(name="maintain_date", attrName="maintainDate", label="维护时间"),
		@Column(name="maintain_run_status", attrName="maintainRunStatus", label="维护时设备运行状态", comment="维护时设备运行状态（0-停止，1-运行）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.tree_sorts, a.maintain_plan_code"
)
public class PreventMaintainPlan extends TreeEntity<PreventMaintainPlan> {
	
	private static final long serialVersionUID = 1L;
	private String maintainPlanCode;		// 维护计划编号
	private String miantianTreeName;		// 维护节点名称
	private String equipCode;		// 设备编号
	private String miantainerCode;		// 维护人员编号
	private String oilPart;		// 给油脂部位
	private String oilWay;		// 给油脂方式（0-手动，1-油枪，2-螺丝刀）
	private String oilLabelCode;		// 润滑油牌号
	private String supplyOilMass;		// 补充油量
	private String supplyCycle;		// 补充周期（0-20天，1-45天，2-60天，3-90天）
	private String changeOilMass;		// 更换油量
	private String changeCycle;		// 更换周期（0-1Y，2-2Y，3-3Y）
	private Date maintainDate;		// 维护时间
	private String maintainRunStatus;		// 维护时设备运行状态（0-停止，1-运行）
	private Extend extend;		// 扩展字段
	
	public PreventMaintainPlan() {
		this(null);
	}

	public PreventMaintainPlan(String id){
		super(id);
	}
	
	@Override
	public PreventMaintainPlan getParent() {
		return parent;
	}

	@Override
	public void setParent(PreventMaintainPlan parent) {
		this.parent = parent;
	}
	
	public String getMaintainPlanCode() {
		return maintainPlanCode;
	}

	public void setMaintainPlanCode(String maintainPlanCode) {
		this.maintainPlanCode = maintainPlanCode;
	}
	
	@Length(min=0, max=200, message="维护节点名称长度不能超过 200 个字符")
	public String getMiantianTreeName() {
		return miantianTreeName;
	}

	public void setMiantianTreeName(String miantianTreeName) {
		this.miantianTreeName = miantianTreeName;
	}
	
	@Length(min=0, max=64, message="设备编号长度不能超过 64 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=64, message="维护人员编号长度不能超过 64 个字符")
	public String getMiantainerCode() {
		return miantainerCode;
	}

	public void setMiantainerCode(String miantainerCode) {
		this.miantainerCode = miantainerCode;
	}
	
	@Length(min=0, max=255, message="给油脂部位长度不能超过 255 个字符")
	public String getOilPart() {
		return oilPart;
	}

	public void setOilPart(String oilPart) {
		this.oilPart = oilPart;
	}
	
	@Length(min=0, max=1, message="给油脂方式长度不能超过 1 个字符")
	public String getOilWay() {
		return oilWay;
	}

	public void setOilWay(String oilWay) {
		this.oilWay = oilWay;
	}
	
	@Length(min=0, max=255, message="润滑油牌号长度不能超过 255 个字符")
	public String getOilLabelCode() {
		return oilLabelCode;
	}

	public void setOilLabelCode(String oilLabelCode) {
		this.oilLabelCode = oilLabelCode;
	}
	
	@Length(min=0, max=255, message="补充油量长度不能超过 255 个字符")
	public String getSupplyOilMass() {
		return supplyOilMass;
	}

	public void setSupplyOilMass(String supplyOilMass) {
		this.supplyOilMass = supplyOilMass;
	}
	
	@Length(min=0, max=1, message="补充周期长度不能超过 1 个字符")
	public String getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(String supplyCycle) {
		this.supplyCycle = supplyCycle;
	}
	
	@Length(min=0, max=255, message="更换油量长度不能超过 255 个字符")
	public String getChangeOilMass() {
		return changeOilMass;
	}

	public void setChangeOilMass(String changeOilMass) {
		this.changeOilMass = changeOilMass;
	}
	
	@Length(min=0, max=1, message="更换周期长度不能超过 1 个字符")
	public String getChangeCycle() {
		return changeCycle;
	}

	public void setChangeCycle(String changeCycle) {
		this.changeCycle = changeCycle;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(Date maintainDate) {
		this.maintainDate = maintainDate;
	}
	
	@Length(min=0, max=1, message="维护时设备运行状态长度不能超过 1 个字符")
	public String getMaintainRunStatus() {
		return maintainRunStatus;
	}

	public void setMaintainRunStatus(String maintainRunStatus) {
		this.maintainRunStatus = maintainRunStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}