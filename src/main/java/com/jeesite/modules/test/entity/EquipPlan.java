/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;


import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 设备与计划关联信息Entity
 * @author dyl
 * @version 2018-08-29
 */
@Table(name="equip_plan", alias="a", columns={
		@Column(name="equip_code", attrName="equipCode", label="设备编号", isPK=true),
		@Column(name="plan_code", attrName="planCode", label="计划编号", isPK=true),
	}, orderBy="a.equip_code DESC, a.plan_code DESC"
)
public class EquipPlan extends DataEntity<EquipPlan> {
	
	private static final long serialVersionUID = 1L;
	private String equipCode;		// 设备编号
	private String planCode;		// 计划编号
	
	public EquipPlan() {
		this((String)null, (String)null);
	}

	public EquipPlan(String equipCode, String planCode){
		this.equipCode = equipCode;
		this.planCode = planCode;
	}
	
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	
}