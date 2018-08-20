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
 * 预防性维护计划安排Entity
 * @author dyl
 * @version 2018-08-20
 */
@Table(name="prevent_maintian_schedule", alias="a", columns={
		@Column(name="maintain_schedule_code", attrName="maintainScheduleCode", label="维护安排编号", isPK=true),
		@Column(name="maintain_plan_code", attrName="maintainPlanCode", label="维护计划编号"),
		@Column(name="miantianer_code", attrName="miantianerCode", label="维护员编号"),
		@Column(name="maintain_date", attrName="maintainDate", label="维护时间"),
		@Column(name="maintain_finish_status", attrName="maintainFinishStatus", label="维护完成状态", comment="维护完成状态（0-未完成  1-已完成）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u1",
				on = "u1.user_code = a.miantianer_code", columns = {
				//@Column(name = "user_code", label = "用户编码", isPK = true),
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = PreventMaintainPlan.class, attrName = "preventMaintainPlan", alias = "p",
				on = "p.maintain_plan_code = a.maintain_plan_code", columns = {
				@Column(name = "miantian_tree_name", label = "节点名称", isQuery = true),
		})
}, orderBy="a.update_date DESC"
)
public class PreventMaintianSchedule extends DataEntity<PreventMaintianSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String maintainScheduleCode;		// 维护安排编号
	private String maintainPlanCode;		// 维护计划编号
	private String miantianerCode;		// 维护员编号
	private Date maintainDate;		// 维护时间
	private String maintainFinishStatus;		// 维护完成状态（0-未完成  1-已完成）
	private Extend extend;		// 扩展字段
	private User user;
	private PreventMaintainPlan preventMaintainPlan;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PreventMaintainPlan getPreventMaintainPlan() {
		return preventMaintainPlan;
	}

	public void setPreventMaintainPlan(PreventMaintainPlan preventMaintainPlan) {
		this.preventMaintainPlan = preventMaintainPlan;
	}
	
	public PreventMaintianSchedule() {
		this(null);
	}

	public PreventMaintianSchedule(String id){
		super(id);
	}
	
	public String getMaintainScheduleCode() {
		return maintainScheduleCode;
	}

	public void setMaintainScheduleCode(String maintainScheduleCode) {
		this.maintainScheduleCode = maintainScheduleCode;
	}
	
	@Length(min=0, max=64, message="维护计划编号长度不能超过 64 个字符")
	public String getMaintainPlanCode() {
		return maintainPlanCode;
	}

	public void setMaintainPlanCode(String maintainPlanCode) {
		this.maintainPlanCode = maintainPlanCode;
	}
	
	@Length(min=0, max=64, message="维护员编号长度不能超过 64 个字符")
	public String getMiantianerCode() {
		return miantianerCode;
	}

	public void setMiantianerCode(String miantianerCode) {
		this.miantianerCode = miantianerCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintainDate() {
		return maintainDate;
	}

	public void setMaintainDate(Date maintainDate) {
		this.maintainDate = maintainDate;
	}
	
	@Length(min=0, max=1, message="维护完成状态长度不能超过 1 个字符")
	public String getMaintainFinishStatus() {
		return maintainFinishStatus;
	}

	public void setMaintainFinishStatus(String maintainFinishStatus) {
		this.maintainFinishStatus = maintainFinishStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}