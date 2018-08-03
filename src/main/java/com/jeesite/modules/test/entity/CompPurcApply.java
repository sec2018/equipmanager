/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.entity.UserRole;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import com.jeesite.common.entity.Extend;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * comp_purc_applyEntity
 * @author jyf
 * @version 2018-08-02
 */
@Table(name="comp_purc_apply", alias="a", columns={
		@Column(name="purchase_apply_code", attrName="purchaseApplyCode", label="备品备件采购申请编号", isPK=true),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="purchase_number", attrName="purchaseNumber", label="备品备件采购数量"),
		@Column(name="purchase_cost", attrName="purchaseCost", label="总费用" ,queryType=QueryType.LTE),
		@Column(name="apply_status", attrName="applyStatus", label="备品备件采购状态", comment="备品备件采购状态(0-待审核 1-审核通过 2-驳回)"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable = {
		@JoinTable(type= Type.LEFT_JOIN, entity = UserRole.class ,  attrName = "userRole" , alias = "u12", on ="a.extend_s2=u12.role_code" ,columns = {
				@Column(name="user_code", label="用户编码", isPK=true),
				@Column(name="role_code", label="用户角色", isQuery=true),

		}),
		@JoinTable(type= Type.LEFT_JOIN, entity = User.class ,  attrName = "user" , alias = "u11", on ="u12.user_code=u11.user_code" ,columns = {
				@Column(name="user_code", label="用户编码", isPK=true),
				@Column(name="user_name", label="用户名称", isQuery=true),
				@Column(name="login_code", label="用户角色", isQuery=true),
		}),


    }, orderBy="a.update_date DESC"
)
public class CompPurcApply extends DataEntity<CompPurcApply> {
	
	private static final long serialVersionUID = 1L;
	private String purchaseApplyCode;		// 备品备件采购申请编号
	private String componentCode;		// 备品备件编号
	private Long purchaseNumber;		// 备品备件采购数量
	private Integer purchaseCost;		// 总费用
	private String applyStatus;		// 备品备件采购状态(0-待审核 1-审核通过 2-驳回)
	private Extend extend;		// 扩展字段
	private List<CompPurcApplyChild> compPurcApplyChildList = ListUtils.newArrayList();		// 子表列表
	private User user; //用户
	private UserRole userRole;  //用户角色

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public CompPurcApply() {
		this(null);
	}

	public CompPurcApply(String id){
		super(id);
	}
	
	public String getPurchaseApplyCode() {
		return purchaseApplyCode;
	}

	public void setPurchaseApplyCode(String purchaseApplyCode) {
		this.purchaseApplyCode = purchaseApplyCode;
	}
	
	//@NotBlank(message="备品备件编号不能为空")
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
	
	public List<CompPurcApplyChild> getCompPurcApplyChildList() {
		return compPurcApplyChildList;
	}

	public void setCompPurcApplyChildList(List<CompPurcApplyChild> compPurcApplyChildList) {
		this.compPurcApplyChildList = compPurcApplyChildList;
	}
	
}