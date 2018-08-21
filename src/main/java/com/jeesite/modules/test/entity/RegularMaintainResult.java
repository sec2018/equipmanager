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
 * 定修结果维护Entity
 * @author dyl
 * @version 2018-08-21
 */
@Table(name="regular_maintain_result", alias="a", columns={
		@Column(name="regular_result_code", attrName="regularResultCode", label="定修结果编号", isPK=true),
		@Column(name="regular_plan_code", attrName="regularPlanCode", label="定修计划编号"),
		@Column(name="maintainer_code", attrName="maintainerCode", label="定修员编号"),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="component_name", attrName="componentName", label="备品备件名称", queryType=QueryType.LIKE),
		@Column(name="component_number", attrName="componentNumber", label="备品备件数量"),
		@Column(name="regular_start_date", attrName="regularStartDate", label="定修开始时间"),
		@Column(name="regular_end_date", attrName="regularEndDate", label="定修结束时间"),
		@Column(name="regular_hours", attrName="regularHours", label="定修工时"),
		@Column(name="regular_maintain_method", attrName="regularMaintainMethod", label="定修方法"),
		@Column(name="regular_run_status", attrName="regularRunStatus", label="定修时设备运行状态", comment="定修时设备运行状态（0-运行，1-停止）"),
		@Column(name="regular_result_depict", attrName="regularResultDepict", label="定修结果描述"),
		@Column(name="regular_result_type", attrName="regularResultType", label="定修结果类型", comment="定修结果类型（0-成功、1-失败）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u",
				on = "u.user_code = a.maintainer_code", columns = {
				//@Column(name = "user_code", label = "用户编码", isPK = true),
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = RegularMaintainPlan.class, attrName = "regularMaintainPlan", alias = "p",
				on = "p.regular_plan_code = a.regular_plan_code", columns = {
				@Column(name = "regular_tree_name", label = "节点名称", isQuery = true),
		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = ComponentInfo.class, attrName = "componentInfo", alias = "c",
				on = "c.component_code = a.component_code", columns = {
				@Column(name = "component_name", label = "备品备件名称", isQuery = true),
		})
}, orderBy="a.update_date DESC"
)
public class RegularMaintainResult extends DataEntity<RegularMaintainResult> {
	
	private static final long serialVersionUID = 1L;
	private String regularResultCode;		// 定修结果编号
	private String regularPlanCode;		// 定修计划编号
	private String maintainerCode;		// 定修员编号
	private String componentCode;		// 备品备件编号
	private String componentName;		// 备品备件名称
	private Long componentNumber;		// 备品备件数量
	private Date regularStartDate;		// 定修开始时间
	private Date regularEndDate;		// 定修结束时间
	private Double regularHours;		// 定修工时
	private String regularMaintainMethod;		// 定修方法
	private String regularRunStatus;		// 定修时设备运行状态（0-运行，1-停止）
	private String regularResultDepict;		// 定修结果描述
	private String regularResultType;		// 定修结果类型（0-成功、1-失败）
	private Extend extend;		// 扩展字段
	private User user;
	private RegularMaintainPlan regularMaintainPlan;
	private ComponentInfo componentInfo;

	public ComponentInfo getComponentInfo() { return componentInfo; }

	public void setComponentInfo(ComponentInfo componentInfo) { this.componentInfo = componentInfo; }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RegularMaintainPlan getRegularMaintainPlan() {
		return regularMaintainPlan;
	}

	public void setRegularMaintainPlan(RegularMaintainPlan regularMaintainPlan) {
		this.regularMaintainPlan = regularMaintainPlan;
	}
	public RegularMaintainResult() {
		this(null);
	}

	public RegularMaintainResult(String id){
		super(id);
	}
	
	public String getRegularResultCode() {
		return regularResultCode;
	}

	public void setRegularResultCode(String regularResultCode) {
		this.regularResultCode = regularResultCode;
	}
	
	@Length(min=0, max=64, message="定修计划编号长度不能超过 64 个字符")
	public String getRegularPlanCode() {
		return regularPlanCode;
	}

	public void setRegularPlanCode(String regularPlanCode) {
		this.regularPlanCode = regularPlanCode;
	}
	
	@Length(min=0, max=64, message="定修员编号长度不能超过 64 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
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
	
	public Long getComponentNumber() {
		return componentNumber;
	}

	public void setComponentNumber(Long componentNumber) {
		this.componentNumber = componentNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegularStartDate() {
		return regularStartDate;
	}

	public void setRegularStartDate(Date regularStartDate) {
		this.regularStartDate = regularStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegularEndDate() {
		return regularEndDate;
	}

	public void setRegularEndDate(Date regularEndDate) {
		this.regularEndDate = regularEndDate;
	}
	
	public Double getRegularHours() {
		return regularHours;
	}

	public void setRegularHours(Double regularHours) {
		this.regularHours = regularHours;
	}
	
	@Length(min=0, max=255, message="定修方法长度不能超过 255 个字符")
	public String getRegularMaintainMethod() {
		return regularMaintainMethod;
	}

	public void setRegularMaintainMethod(String regularMaintainMethod) {
		this.regularMaintainMethod = regularMaintainMethod;
	}
	
	@Length(min=0, max=1, message="定修时设备运行状态长度不能超过 1 个字符")
	public String getRegularRunStatus() {
		return regularRunStatus;
	}

	public void setRegularRunStatus(String regularRunStatus) {
		this.regularRunStatus = regularRunStatus;
	}
	
	@Length(min=0, max=255, message="定修结果描述长度不能超过 255 个字符")
	public String getRegularResultDepict() {
		return regularResultDepict;
	}

	public void setRegularResultDepict(String regularResultDepict) {
		this.regularResultDepict = regularResultDepict;
	}
	
	@Length(min=0, max=1, message="定修结果类型长度不能超过 1 个字符")
	public String getRegularResultType() {
		return regularResultType;
	}

	public void setRegularResultType(String regularResultType) {
		this.regularResultType = regularResultType;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}