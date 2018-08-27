/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

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
 * 故障通知单Entity
 * @author dyl
 * @version 2018-08-22
 */
@Table(name="trouble_notice", alias="a", columns={
		@Column(name="notice_code", attrName="noticeCode", label="通知单编号", isPK=true),
		@Column(name="notice_type", attrName="noticeType", label="通知单类型", comment="通知单类型（0-点检异常，1-巡检异常，2-事故）"),
		@Column(name="plan_code", attrName="planCode", label="点巡检计划编号"),
		@Column(name="equip_code", attrName="equipCode", label="设备编号"),
		@Column(name="maintainer_code", attrName="user.userCode", label="维修人员编号"),//通过搜索框来选择维修人员
		@Column(name="trouble_type", attrName="troubleType", label="故障类型", comment="故障类型（0-硬件，1-软件）"),
		@Column(name="trouble_content", attrName="troubleContent", label="故障内容"),
		@Column(name="notice_status", attrName="noticeStatus", label="通知单状态", comment="通知单状态（0-待指派，1-已指派，2-待承接，3-已承接）"),
		@Column(name="trouble_level", attrName="troubleLevel", label="故障级别", comment="故障级别（0-一般，1-严重，2-非常严重）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u",
				on = "u.user_code = a.maintainer_code", columns = {
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
//		<以下关联查询是想获取点巡检计划名称，但是点巡检计划编号分布在两个表中，操作不方便，暂时不予考虑>
//		@JoinTable(type = Type.LEFT_JOIN, entity = InspectPlan.class, attrName = "inspectPlan", alias = "p",
//				on = "p.inspect_plan_code = a.plan_code", columns = {
//				@Column(name = "tree_name", label = "节点名称", isQuery = true),
//		})
}, orderBy="a.update_date DESC"
)
public class TroubleNotice extends DataEntity<TroubleNotice> {
	
	private static final long serialVersionUID = 1L;
	private String noticeCode;		// 通知单编号
	private String noticeType;		// 通知单类型（0-点检异常，1-巡检异常，2-事故）
	private String planCode;		// 点巡检计划编号
	private String equipCode;		// 设备编号
	private String maintainerCode;		// 维修人员编号
	private String troubleType;		// 故障类型（0-硬件，1-软件）
	private String troubleContent;		// 故障内容
	private String noticeStatus;		// 通知单状态（0-待指派，1-已指派，2-待承接，3-已承接）
	private String troubleLevel;		// 故障级别（0-一般，1-严重，2-非常严重）
	private Extend extend;		// 扩展字段
	private User user;

	public InspectPlan getInspectPlan() {
		return inspectPlan;
	}

	public void setInspectPlan(InspectPlan inspectPlan) {
		this.inspectPlan = inspectPlan;
	}

	private InspectPlan inspectPlan;

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }
	
	public TroubleNotice() {
		this(null);
	}

	public TroubleNotice(String id){
		super(id);
	}
	
	public String getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}
	
	@Length(min=0, max=1, message="通知单类型长度不能超过 1 个字符")
	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
	
	@Length(min=0, max=64, message="点巡检计划编号长度不能超过 64 个字符")
	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = planCode;
	}
	
	@Length(min=0, max=64, message="设备编号长度不能超过 64 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=64, message="维修人员编号长度不能超过 64 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	
	@Length(min=0, max=1, message="故障类型长度不能超过 1 个字符")
	public String getTroubleType() {
		return troubleType;
	}

	public void setTroubleType(String troubleType) {
		this.troubleType = troubleType;
	}
	
	@Length(min=0, max=255, message="故障内容长度不能超过 255 个字符")
	public String getTroubleContent() {
		return troubleContent;
	}

	public void setTroubleContent(String troubleContent) {
		this.troubleContent = troubleContent;
	}
	
	@Length(min=0, max=1, message="通知单状态长度不能超过 1 个字符")
	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}
	
	@Length(min=0, max=1, message="故障级别长度不能超过 1 个字符")
	public String getTroubleLevel() {
		return troubleLevel;
	}

	public void setTroubleLevel(String troubleLevel) {
		this.troubleLevel = troubleLevel;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}