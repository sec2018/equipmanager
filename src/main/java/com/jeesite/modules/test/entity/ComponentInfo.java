/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.NotBlank;
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
 * 备品备件Entity
 * @author jyf
 * @version 2018-08-07
 */
@Table(name="component_info", alias="a", columns={
		@Column(name="id", attrName="id", label="索引号", isPK=true),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="component_name", attrName="componentName", label="备品备件名称", queryType=QueryType.LIKE),
		@Column(name="component_type", attrName="componentType", label="备品备件类型"),
		@Column(name="component_model", attrName="componentModel", label="备品备件型号"),
		@Column(name="component_price", attrName="componentPrice", label="备品备件价格"),
		@Column(name="component_number", attrName="componentNumber", label="备品备件数量"),
		@Column(name="component_status", attrName="componentStatus", label="备品备件状态", comment="备品备件状态（0-充足、1-短缺）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.update_date DESC"
)
public class ComponentInfo extends DataEntity<ComponentInfo> {
	
	private static final long serialVersionUID = 1L;
	private String componentCode;		// 备品备件编号
	private String componentName;		// 备品备件名称
	private String componentType;		// 备品备件类型
	private String componentModel;		// 备品备件型号
	private Integer componentPrice;		// 备品备件价格
	private Long componentNumber;		// 备品备件数量
	private String componentStatus;		// 备品备件状态（0-充足、1-短缺）
	private Extend extend;		// 扩展字段
	
	public ComponentInfo() {
		this(null);
	}

	public ComponentInfo(String id){
		super(id);
	}

	@Length(min=0, max=64, message="备品备件编号长度不能超过 64 个字符")
	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	@Length(min=0, max=255, message="备品备件名称长度不能超过 255 个字符")
	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	@Length(min=0, max=255, message="备品备件类型长度不能超过 255 个字符")
	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	
	@Length(min=0, max=255, message="备品备件型号长度不能超过 255 个字符")
	public String getComponentModel() {
		return componentModel;
	}

	public void setComponentModel(String componentModel) {
		this.componentModel = componentModel;
	}
	
	public Integer getComponentPrice() {
		return componentPrice;
	}

	public void setComponentPrice(Integer componentPrice) {
		this.componentPrice = componentPrice;
	}
	
	public Long getComponentNumber() {
		return componentNumber;
	}

	public void setComponentNumber(Long componentNumber) {
		this.componentNumber = componentNumber;
	}
	
	@Length(min=0, max=1, message="备品备件状态长度不能超过 1 个字符")
	public String getComponentStatus() {
		return componentStatus;
	}

	public void setComponentStatus(String componentStatus) {
		this.componentStatus = componentStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}