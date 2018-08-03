/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import com.jeesite.common.entity.Extend;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * comp_purc_applyEntity
 * @author jyf
 * @version 2018-08-02
 */
@Table(name="comp_purc_apply_child", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="purchase_apply_code", attrName="purchaseApplyCode.purchaseApplyCode", label="备品备件采购申请编号"),
		@Column(name="purc_sort", attrName="purcSort", label="purc_sort"),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="purchase_number", attrName="purchaseNumber", label="备品备件采购数量"),
		@Column(name="purchase_cost", attrName="purchaseCost", label="总费用"),
		@Column(name="apply_status", attrName="applyStatus", label="备品备件采购状态", comment="备品备件采购状态(0-待审核 1-审核通过 2-驳回)"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.create_date ASC"
)
public class CompPurcApplyChild extends DataEntity<CompPurcApplyChild> {
	
	private static final long serialVersionUID = 1L;
	private CompPurcApply purchaseApplyCode;		// 备品备件采购申请编号 父类
	private Long purcSort;		// purc_sort
	private String componentCode;		// 备品备件编号
	private Long purchaseNumber;		// 备品备件采购数量
	private Integer purchaseCost;		// 总费用
	private String applyStatus;		// 备品备件采购状态(0-待审核 1-审核通过 2-驳回)
	private Extend extend;		// 扩展字段
	
	public CompPurcApplyChild() {
		this(null);
	}


	public CompPurcApplyChild(CompPurcApply purchaseApplyCode){
		this.purchaseApplyCode = purchaseApplyCode;
	}
	
	@Length(min=0, max=64, message="备品备件采购申请编号长度不能超过 64 个字符")
	public CompPurcApply getPurchaseApplyCode() {
		return purchaseApplyCode;
	}

	public void setPurchaseApplyCode(CompPurcApply purchaseApplyCode) {
		this.purchaseApplyCode = purchaseApplyCode;
	}
	
	public Long getPurcSort() {
		return purcSort;
	}

	public void setPurcSort(Long purcSort) {
		this.purcSort = purcSort;
	}
	
	@Length(min=0, max=255, message="备品备件编号长度不能超过 255 个字符")
	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	public Long getPurchaseNumber() {
		return purchaseNumber;
	}

	public void setPurchaseNumber(Long purchaseNumber) {
		this.purchaseNumber = purchaseNumber;
	}
	
	public Integer getPurchaseCost() {
		return purchaseCost;
	}

	public void setPurchaseCost(Integer purchaseCost) {
		this.purchaseCost = purchaseCost;
	}
	
	@Length(min=0, max=255, message="备品备件采购状态长度不能超过 255 个字符")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}