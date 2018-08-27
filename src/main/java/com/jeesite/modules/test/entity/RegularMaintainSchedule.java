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
 * 定修安排Entity
 * @author dyl
 * @version 2018-08-21
 */
@Table(name="regular_maintain_schedule", alias="a", columns={
		@Column(name="regular_schedule_code", attrName="regularScheduleCode", label="定修安排编号", isPK=true),
		@Column(name="regular_plan_code", attrName="regularPlanCode", label="定修计划编号"),
		@Column(name="regular_plan_name", attrName="regularPlanName", label="定修计划名称", queryType=QueryType.LIKE),
		@Column(name="maintainer_code", attrName="user.userCode", label="定修员编号"),//通过搜索框来选择定修人员
		@Column(name="regular_maintain_date", attrName="regularMaintainDate", label="定修时间"),
		@Column(name="regular_finish_status", attrName="regularFinishStatus", label="定修完成状态", comment="定修完成状态（0-未完成  1-已完成）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u1",
				on = "u1.user_code = a.maintainer_code", columns = {
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = RegularMaintainPlan.class, attrName = "regularMaintainPlan", alias = "p",
				on = "p.regular_plan_code = a.regular_plan_code", columns = {
				@Column(name = "regular_tree_name", label = "节点名称", isQuery = true),
		})
}, orderBy="a.update_date DESC"
)
public class RegularMaintainSchedule extends DataEntity<RegularMaintainSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String regularScheduleCode;		// 定修安排编号
	private String regularPlanCode;		// 定修计划编号
	private String regularPlanName;		// 定修计划名称
	private String maintainerCode;		// 定修员编号
	private Date regularMaintainDate;		// 定修时间
	private String regularFinishStatus;		// 定修完成状态（0-未完成  1-已完成）
	private Extend extend;		// 扩展字段
	private User user;
	private RegularMaintainPlan regularMaintainPlan;

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
	
	public RegularMaintainSchedule() {
		this(null);
	}

	public RegularMaintainSchedule(String id){
		super(id);
	}
	
	public String getRegularScheduleCode() {
		return regularScheduleCode;
	}

	public void setRegularScheduleCode(String regularScheduleCode) {
		this.regularScheduleCode = regularScheduleCode;
	}
	
	@Length(min=0, max=64, message="定修计划编号长度不能超过 64 个字符")
	public String getRegularPlanCode() {
		return regularPlanCode;
	}

	public void setRegularPlanCode(String regularPlanCode) {
		this.regularPlanCode = regularPlanCode;
	}
	
	@Length(min=0, max=255, message="定修计划名称长度不能超过 255 个字符")
	public String getRegularPlanName() {
		return regularPlanName;
	}

	public void setRegularPlanName(String regularPlanName) {
		this.regularPlanName = regularPlanName;
	}
	
	@Length(min=0, max=64, message="定修员编号长度不能超过 64 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegularMaintainDate() {
		return regularMaintainDate;
	}

	public void setRegularMaintainDate(Date regularMaintainDate) {
		this.regularMaintainDate = regularMaintainDate;
	}
	
	@Length(min=0, max=1, message="定修完成状态长度不能超过 1 个字符")
	public String getRegularFinishStatus() {
		return regularFinishStatus;
	}

	public void setRegularFinishStatus(String regularFinishStatus) {
		this.regularFinishStatus = regularFinishStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}