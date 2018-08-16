/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.Office;
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
 * 点检结果Entity
 * @author jyf
 * @version 2018-08-16
 */
@Table(name="check_result", alias="a", columns={
		@Column(name="id", attrName="id", label="点检结果编号", isPK=true),
		@Column(name="check_plan_code", attrName="checkPlanCode", label="点检计划编号"),
		@Column(name="check_plan_name", attrName="checkPlanName", label="点检计划名称", queryType=QueryType.LIKE),
		@Column(name="checker_id", attrName="checkerId", label="点检员编号"),
		@Column(name="check_actual_date", attrName="checkActualDate", label="实际点检时间",queryType = QueryType.LTE),
		@Column(name="check_hours", attrName="checkHours", label="点检工时"),
		@Column(name="check__depict", attrName="checkDepict", label="点检结果描述"),
		@Column(name="check_status", attrName="checkStatus", label="点检结果类型", comment="点检结果类型（0正常，1异常）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity= CheckPlans.class, attrName="CheckPlans", alias="u10",
				on="u10.chack_plan_code = a.check_plan_code", columns={
				@Column(name="tree_name", label="节点名称", isQuery=true),
		}),
		@JoinTable(type=Type.LEFT_JOIN, entity= User.class, attrName="testUser", alias="u11",
				on="u11.user_code = a.checker_id", columns={
				@Column(name="user_code", label="用户编码", isPK=true),
				@Column(name="user_name", label="用户名称", isQuery=false),
		}),
//		@JoinTable(type=Type.LEFT_JOIN, entity= CheckSchedule.class, attrName="CheckSchedule", alias="u11",
//				on="u11.chack_plan_code = u10.parent_code", columns={
//				@Column(name="checker_code", label="点检员编号", isQuery=true),
//				@Column(name="checker_name", label="点检员名称", isQuery=true),
//		})
}, orderBy="a.update_date DESC"
)
public class CheckResult extends DataEntity<CheckResult> {
	
	private static final long serialVersionUID = 1L;
	private String checkPlanCode;		// 点检计划编号
	private String checkPlanName;		// 点检计划名称
	private String checkerId;		// 点检员编号
	private Date checkActualDate;		// 实际点检时间
	private Double checkHours;		// 点检工时
	private String checkDepict;		// 点检结果描述
	private String checkStatus;		// 点检结果类型（0正常，1异常）
	private Extend extend;		// 扩展字段
	private CheckSchedule checkSchedule;
	private CheckPlans  checkPlans;
	private User testUser;		// 用户选择

	public CheckPlans getCheckPlans() {
		return checkPlans;
	}
	public void setCheckPlans(CheckPlans checkPlans) {
		this.checkPlans = checkPlans;
	}

	public CheckSchedule getCheckSchedule() {
		return checkSchedule;
	}
	public User getTestUser() {
		return testUser;
	}

	public void setTestUser(User testUser) {
		this.testUser = testUser;
	}

	public void setCheckSchedule(CheckSchedule checkSchedule) {
		this.checkSchedule = checkSchedule;
	}


	
	public CheckResult() {
		this(null);
	}

	public CheckResult(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="点检计划编号长度不能超过 64 个字符")
	public String getCheckPlanCode() {
		return checkPlanCode;
	}

	public void setCheckPlanCode(String checkPlanCode) {
		this.checkPlanCode = checkPlanCode;
	}
	
	@Length(min=0, max=255, message="点检计划名称长度不能超过 255 个字符")
	public String getCheckPlanName() {
		return checkPlanName;
	}

	public void setCheckPlanName(String checkPlanName) {
		this.checkPlanName = checkPlanName;
	}
	
	@Length(min=0, max=64, message="点检员编号长度不能超过 64 个字符")
	public String getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(String checkerId) {
		this.checkerId = checkerId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckActualDate() {
		return checkActualDate;
	}

	public void setCheckActualDate(Date checkActualDate) {
		this.checkActualDate = checkActualDate;
	}
	
	public Double getCheckHours() {
		return checkHours;
	}

	public void setCheckHours(Double checkHours) {
		this.checkHours = checkHours;
	}
	
	@Length(min=0, max=50, message="点检结果描述长度不能超过 50 个字符")
	public String getCheckDepict() {
		return checkDepict;
	}

	public void setCheckDepict(String checkDepict) {
		this.checkDepict = checkDepict;
	}
	
	@Length(min=0, max=255, message="点检结果类型长度不能超过 255 个字符")
	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}