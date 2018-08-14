/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.modules.sys.entity.Office;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.Extend;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 点检安排Entity
 * @author jyf
 * @version 2018-08-14
 */
@Table(name="check_schedule", alias="a", columns={
		@Column(name="chack_schedule_code", attrName="chackScheduleCode", label="点检安排编号", isPK=true),
		@Column(name="chack_plan_code", attrName="chackPlanCode", label="点检计划编号"),
		@Column(name="chack_plan_name", attrName="chackPlanName", label="点检计划名称", queryType=QueryType.LIKE),
		@Column(name="checker_code", attrName="checkerCode.userCode", label="点检员编号"),
		@Column(name="checker_name", attrName="checkerName.officeCode", label="机构", queryType=QueryType.LIKE),
		@Column(name="check_start_date", attrName="checkStartDate", label="点检开始时间"),
		@Column(name="check_end_date", attrName="checkEndDate", label="点检结束时间"),
		@Column(name="check_finish", attrName="checkFinish", label="点检完成情况", comment="点检完成情况（0-未完成  1-已完成）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity=User.class, attrName="checkerCode", alias="u4",
			on="u4.user_code = a.checker_code", columns={
				@Column(name="user_code", label="用户编码", isPK=true),
				@Column(name="user_name", label="用户名称", isQuery=false),
		}),
		@JoinTable(type=Type.LEFT_JOIN, entity=Office.class, attrName="checkerName", alias="u5",
			on="u5.office_code = a.checker_name", columns={
				@Column(name="office_code", label="机构编码", isPK=true),
				@Column(name="office_name", label="机构名称", isQuery=false),
		}),
	}, orderBy="a.update_date DESC"
)
public class CheckSchedule extends DataEntity<CheckSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String chackScheduleCode;		// 点检安排编号
	private String chackPlanCode;		// 点检计划编号
	private String chackPlanName;		// 点检计划名称
	private User checkerCode;		// 点检员编号
	private Office checkerName;		// 机构
	private Date checkStartDate;		// 点检开始时间
	private Date checkEndDate;		// 点检结束时间
	private String checkFinish;		// 点检完成情况（0-未完成  1-已完成）
	private Extend extend;		// 扩展字段
	
	public CheckSchedule() {
		this(null);
	}

	public CheckSchedule(String id){
		super(id);
	}
	
	public String getChackScheduleCode() {
		return chackScheduleCode;
	}

	public void setChackScheduleCode(String chackScheduleCode) {
		this.chackScheduleCode = chackScheduleCode;
	}
	
	@Length(min=0, max=64, message="点检计划编号长度不能超过 64 个字符")
	public String getChackPlanCode() {
		return chackPlanCode;
	}

	public void setChackPlanCode(String chackPlanCode) {
		this.chackPlanCode = chackPlanCode;
	}
	
	@Length(min=0, max=255, message="点检计划名称长度不能超过 255 个字符")
	public String getChackPlanName() {
		return chackPlanName;
	}

	public void setChackPlanName(String chackPlanName) {
		this.chackPlanName = chackPlanName;
	}
	
	public User getCheckerCode() {
		return checkerCode;
	}

	public void setCheckerCode(User checkerCode) {
		this.checkerCode = checkerCode;
	}
	
	public Office getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(Office checkerName) {
		this.checkerName = checkerName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckStartDate() {
		return checkStartDate;
	}

	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCheckEndDate() {
		return checkEndDate;
	}

	public void setCheckEndDate(Date checkEndDate) {
		this.checkEndDate = checkEndDate;
	}
	
	@Length(min=0, max=1, message="点检完成情况长度不能超过 1 个字符")
	public String getCheckFinish() {
		return checkFinish;
	}

	public void setCheckFinish(String checkFinish) {
		this.checkFinish = checkFinish;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}