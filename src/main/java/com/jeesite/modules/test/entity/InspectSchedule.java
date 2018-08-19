/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
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
 * 巡检安排Entity
 * @author dyl
 * @version 2018-08-17
 */
@Table(name="inspect_schedule", alias="a", columns={
		@Column(name="inspect_schedule_code", attrName="inspectScheduleCode", label="巡检安排编号", isPK=true),
		@Column(name="inspect_plan_code", attrName="inspectPlanCode", label="巡检计划编号"),
		@Column(name="inspect_plan_name", attrName="inspectPlanName", label="巡检计划名称", queryType=QueryType.LIKE),
		@Column(name="inspecter_code", attrName="inspecterCode", label="巡检员编号"),
		@Column(name="inspect_finish_status", attrName="inspectFinishStatus", label="巡检完成状态", comment="巡检完成状态（0-未完成  1-已完成）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u1",
				on = "u1.user_code = a.inspecter_code", columns = {
				//@Column(name = "user_code", label = "用户编码", isPK = true),
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
//		@JoinTable(type = Type.LEFT_JOIN, entity = Office.class, attrName = "office", alias = "u2",
//				on = "u2.office_code = u1.user_name", columns = {
//				@Column(name = "office_code", label = "机构编码", isPK = true),
//				@Column(name = "office_name", label = "机构名称", isQuery = false),
//		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = InspectPlan.class, attrName = "inspectPlan", alias = "p",
				on = "p.inspect_plan_code = a.inspect_plan_code", columns = {
				@Column(name = "tree_name", label = "节点名称", isQuery = true),
		})
}, orderBy="a.update_date DESC"
)
public class InspectSchedule extends DataEntity<InspectSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String inspectScheduleCode;		// 巡检安排编号
	private String inspectPlanCode;		// 巡检计划编号
	private String inspectPlanName;		// 巡检计划名称
	private String inspecterCode;		// 巡检员编号
	private String inspectFinishStatus;		// 巡检完成状态（0-未完成  1-已完成）
	private Extend extend;		// 扩展字段
	private User user;		// 巡检员编号
	private Office office;		// 机构
	private InspectPlan inspectPlan;
	public InspectPlan getInspectPlan() { return inspectPlan; }

	public void setInspectPlan(InspectPlan inspectPlan) { this.inspectPlan = inspectPlan; }

	public Office getOffice() { return office; }

	public void setOffice(Office office) { this.office = office; }

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }
	
	public InspectSchedule() {
		this(null);
	}

	public InspectSchedule(String id){
		super(id);
	}
	
	public String getInspectScheduleCode() {
		return inspectScheduleCode;
	}

	public void setInspectScheduleCode(String inspectScheduleCode) {
		this.inspectScheduleCode = inspectScheduleCode;
	}
	
	@Length(min=0, max=64, message="巡检计划编号长度不能超过 64 个字符")
	public String getInspectPlanCode() {
		return inspectPlanCode;
	}

	public void setInspectPlanCode(String inspectPlanCode) {
		this.inspectPlanCode = inspectPlanCode;
	}
	
	@Length(min=0, max=255, message="巡检计划名称长度不能超过 255 个字符")
	public String getInspectPlanName() {
		return inspectPlanName;
	}

	public void setInspectPlanName(String inspectPlanName) {
		this.inspectPlanName = inspectPlanName;
	}
	
	@Length(min=0, max=64, message="巡检员编号长度不能超过 64 个字符")
	public String getInspecterCode() {
		return inspecterCode;
	}

	public void setInspecterCode(String inspecterCode) {
		this.inspecterCode = inspecterCode;
	}
	
	@Length(min=0, max=1, message="巡检完成状态长度不能超过 1 个字符")
	public String getInspectFinishStatus() {
		return inspectFinishStatus;
	}

	public void setInspectFinishStatus(String inspectFinishStatus) {
		this.inspectFinishStatus = inspectFinishStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}