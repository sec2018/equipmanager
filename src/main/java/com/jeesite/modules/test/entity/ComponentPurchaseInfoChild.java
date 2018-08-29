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
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 备品备件入库Entity
 * @author jyf
 * @version 2018-08-29
 */
@Table(name="component_purchase_info_child", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="purchase_info_code", attrName="purchaseInfoCode.purchaseInfoCode", label="备品备件采购信息编号"),
		@Column(name="purchase_apply_code", attrName="purchaseApplyCode", label="备品备件采购申请编号"),
		@Column(name="purchase_sort", attrName="purchaseSort", label="purchase_sort"),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="purchase_number", attrName="purchaseNumber", label="备品备件采购数量"),
		@Column(name="purchase_cost", attrName="purchaseCost", label="备品备件采购费用"),
		@Column(name="manufacture", attrName="manufacture", label="备品备件生产厂商"),
		@Column(name="purchase_date", attrName="purchaseDate", label="备品备件采购时间"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.create_date ASC"
)
public class ComponentPurchaseInfoChild extends DataEntity<ComponentPurchaseInfoChild> {
	
	private static final long serialVersionUID = 1L;
	private ComponentPurchaseInfo purchaseInfoCode;		// 备品备件采购信息编号 父类
	private String purchaseApplyCode;		// 备品备件采购申请编号
	private Long purchaseSort;		// purchase_sort
	private String componentCode;		// 备品备件编号
	private Long purchaseNumber;		// 备品备件采购数量
	private Integer purchaseCost;		// 备品备件采购费用
	private String manufacture;		// 备品备件生产厂商
	private Date purchaseDate;		// 备品备件采购时间
	private Extend extend;		// 扩展字段
	
	public ComponentPurchaseInfoChild() {
		this(null);
	}


	public ComponentPurchaseInfoChild(ComponentPurchaseInfo purchaseInfoCode){
		this.purchaseInfoCode = purchaseInfoCode;
	}
	
	@Length(min=0, max=255, message="备品备件采购信息编号长度不能超过 255 个字符")
	public ComponentPurchaseInfo getPurchaseInfoCode() {
		return purchaseInfoCode;
	}

	public void setPurchaseInfoCode(ComponentPurchaseInfo purchaseInfoCode) {
		this.purchaseInfoCode = purchaseInfoCode;
	}
	
	@Length(min=0, max=255, message="备品备件采购申请编号长度不能超过 255 个字符")
	public String getPurchaseApplyCode() {
		return purchaseApplyCode;
	}

	public void setPurchaseApplyCode(String purchaseApplyCode) {
		this.purchaseApplyCode = purchaseApplyCode;
	}
	
	public Long getPurchaseSort() {
		return purchaseSort;
	}

	public void setPurchaseSort(Long purchaseSort) {
		this.purchaseSort = purchaseSort;
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
	
	@Length(min=0, max=255, message="备品备件生产厂商长度不能超过 255 个字符")
	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}