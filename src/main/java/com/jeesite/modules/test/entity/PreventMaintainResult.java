/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.modules.sys.entity.User;
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
 * 预防性维护结果Entity
 * @author dyl
 * @version 2018-08-20
 */
@Table(name="prevent_maintain_result", alias="a", columns={
		@Column(name="maintian_result_code", attrName="maintianResultCode", label="维护结果编号", isPK=true),
		@Column(name="maintain_plan_code", attrName="maintainPlanCode", label="维护计划编号"),
		@Column(name="maintainer_code", attrName="maintainerCode", label="维护员编号"),
		@Column(name="maintain_start_date", attrName="maintainStartDate", label="维护开始时间"),
		@Column(name="maintian_end_date", attrName="maintianEndDate", label="维护结束时间"),
		@Column(name="maintian_hours", attrName="maintianHours", label="维护工时"),
		@Column(name="maintain_result_depict", attrName="maintainResultDepict", label="维护结果描述"),
		@Column(name="maintian_result_type", attrName="maintianResultType", label="维护结果类型", comment="维护结果类型（0-正常、1-异常）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u",
				on = "u.user_code = a.maintainer_code", columns = {
				//@Column(name = "user_code", label = "用户编码", isPK = true),
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = PreventMaintainPlan.class, attrName = "preventMaintainPlan", alias = "p",
				on = "p.maintain_plan_code = a.maintain_plan_code", columns = {
				@Column(name = "miantian_tree_name", label = "节点名称", isQuery = true),
		})
}, orderBy="a.update_date DESC"
)
public class PreventMaintainResult extends DataEntity<PreventMaintainResult> {
	
	private static final long serialVersionUID = 1L;
	private String maintianResultCode;		// 维护结果编号
	private String maintainPlanCode;		// 维护计划编号
	private String maintainerCode;		// 维护员编号
	private Date maintainStartDate;		// 维护开始时间
	private Date maintianEndDate;		// 维护结束时间
	private Double maintianHours;		// 维护工时
	private String maintainResultDepict;		// 维护结果描述
	private String maintianResultType;		// 维护结果类型（0-正常、1-异常）
	private Extend extend;		// 扩展字段
	private User user;
	private PreventMaintainPlan preventMaintainPlan;

	public PreventMaintainPlan getPreventMaintainPlan() {
		return preventMaintainPlan;
	}

	public void setPreventMaintainPlan(PreventMaintainPlan preventMaintainPlan) {
		this.preventMaintainPlan = preventMaintainPlan;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public PreventMaintainResult() {
		this(null);
	}

	public PreventMaintainResult(String id){
		super(id);
	}
	
	public String getMaintianResultCode() {
		return maintianResultCode;
	}

	public void setMaintianResultCode(String maintianResultCode) {
		this.maintianResultCode = maintianResultCode;
	}
	
	@Length(min=0, max=64, message="维护计划编号长度不能超过 64 个字符")
	public String getMaintainPlanCode() {
		return maintainPlanCode;
	}

	public void setMaintainPlanCode(String maintainPlanCode) {
		this.maintainPlanCode = maintainPlanCode;
	}
	
	@Length(min=0, max=64, message="维护员编号长度不能超过 64 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintainStartDate() {
		return maintainStartDate;
	}

	public void setMaintainStartDate(Date maintainStartDate) {
		this.maintainStartDate = maintainStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintianEndDate() {
		return maintianEndDate;
	}

	public void setMaintianEndDate(Date maintianEndDate) {
		this.maintianEndDate = maintianEndDate;
	}
	
	public Double getMaintianHours() {
		return maintianHours;
	}

	public void setMaintianHours(Double maintianHours) {
		this.maintianHours = maintianHours;
	}
	
	@Length(min=0, max=255, message="维护结果描述长度不能超过 255 个字符")
	public String getMaintainResultDepict() {
		return maintainResultDepict;
	}

	public void setMaintainResultDepict(String maintainResultDepict) {
		this.maintainResultDepict = maintainResultDepict;
	}
	
	@Length(min=0, max=1, message="维护结果类型长度不能超过 1 个字符")
	public String getMaintianResultType() {
		return maintianResultType;
	}

	public void setMaintianResultType(String maintianResultType) {
		this.maintianResultType = maintianResultType;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}