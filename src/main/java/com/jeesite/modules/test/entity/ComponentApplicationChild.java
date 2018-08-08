/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

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
 * component_applicationEntity
 * @author jyf
 * @version 2018-08-08
 */
@Table(name="component_application_child", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="application_code", attrName="applicationCode.applicationCode", label="备品备件申领编号"),
		@Column(name="equip_code", attrName="equipCode", label="装配设备编号"),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="applicant_code", attrName="applicantCode", label="备品备件申领人编号"),
		@Column(name="approval_code", attrName="approvalCode", label="备品备件申领审批人编号"),
		@Column(name="application_number", attrName="applicationNumber", label="申领数量"),
		@Column(name="application_reason", attrName="applicationReason", label="申领原因"),
		@Column(name="application_submit_date", attrName="applicationSubmitDate", label="申领提交时间"),
		@Column(name="application_end_date", attrName="applicationEndDate", label="申领完成时间"),
		@Column(name="application_status", attrName="applicationStatus", label="申领状态", comment="申领状态(0-待审核 1-审核通过 2-驳回)"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.create_date ASC"
)
public class ComponentApplicationChild extends DataEntity<ComponentApplicationChild> {
	
	private static final long serialVersionUID = 1L;
	private ComponentApplication applicationCode;		// 备品备件申领编号 父类
	private String equipCode;		// 装配设备编号
	private String componentCode;		// 备品备件编号
	private String applicantCode;		// 备品备件申领人编号
	private String approvalCode;		// 备品备件申领审批人编号
	private Long applicationNumber;		// 申领数量
	private String applicationReason;		// 申领原因
	private Date applicationSubmitDate;		// 申领提交时间
	private Date applicationEndDate;		// 申领完成时间
	private String applicationStatus;		// 申领状态(0-待审核 1-审核通过 2-驳回)
	private Extend extend;		// 扩展字段
	
	public ComponentApplicationChild() {
		this(null);
	}


	public ComponentApplicationChild(ComponentApplication applicationCode){
		this.applicationCode = applicationCode;
	}
	
	@Length(min=0, max=255, message="备品备件申领编号长度不能超过 255 个字符")
	public ComponentApplication getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(ComponentApplication applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	@Length(min=0, max=255, message="装配设备编号长度不能超过 255 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=255, message="备品备件编号长度不能超过 255 个字符")
	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	@Length(min=0, max=255, message="备品备件申领人编号长度不能超过 255 个字符")
	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	
	@Length(min=0, max=255, message="备品备件申领审批人编号长度不能超过 255 个字符")
	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	public Long getApplicationNumber() {
		return applicationNumber;
	}

	public void setApplicationNumber(Long applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	
	@Length(min=0, max=255, message="申领原因长度不能超过 255 个字符")
	public String getApplicationReason() {
		return applicationReason;
	}

	public void setApplicationReason(String applicationReason) {
		this.applicationReason = applicationReason;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplicationSubmitDate() {
		return applicationSubmitDate;
	}

	public void setApplicationSubmitDate(Date applicationSubmitDate) {
		this.applicationSubmitDate = applicationSubmitDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplicationEndDate() {
		return applicationEndDate;
	}

	public void setApplicationEndDate(Date applicationEndDate) {
		this.applicationEndDate = applicationEndDate;
	}
	
	@Length(min=0, max=1, message="申领状态长度不能超过 1 个字符")
	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}