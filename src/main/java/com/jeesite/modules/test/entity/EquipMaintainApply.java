/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 设备报修申请Entity
 * @author dang
 * @version 2018-08-02
 */
@Table(name="equip_maintain_apply", alias="a", columns={
		@Column(name="apply_code", attrName="applyCode", label="申请编号", isPK=true),
		@Column(name="equip_code", attrName="equipCode", label="设备编号"),
		@Column(name="applicant_code", attrName="applicantCode", label="申请人编号"),
		@Column(name="approver_code", attrName="approverCode", label="审批人编号"),
		@Column(name="maintainer_code", attrName="maintainerCode", label="维修人编号"),
		@Column(name="apply_date", attrName="applyDate", label="申请时间"),
		@Column(name="apply_reason", attrName="applyReason", label="报修原因"),
		@Column(name="apply_status", attrName="applyStatus", label="报修状态", comment="报修状态（0待承接，1已承接，2维修成功，3维修失败）"),
		@Column(name="apply_level", attrName="applyLevel", label="申请级别", comment="申请级别（0一般，1紧急）"),
		@Column(includeEntity=DataEntity.class),
	}, joinTable= {
		@JoinTable(type = Type.JOIN, entity = EquipInfo.class, attrName = "equipInfo", alias = "e",
				on = "e.equip_id = a.equip_code", columns = {
				@Column(name = "equip_manager_code", label = "设备管理员编号", queryType = QueryType.LIKE),
				@Column(name = "equip_manager", label = "设备管理员名称", queryType = QueryType.LIKE)
		}),
		@JoinTable(type = Type.JOIN, entity = User.class, attrName = "user", alias = "u",
				on = "u.login_code = a.applicant_code", columns = {
				@Column(name = "user_name", label = "申请人姓名", queryType = QueryType.LIKE)
		}),
},orderBy="a.update_date DESC"
)
public class EquipMaintainApply extends DataEntity<EquipMaintainApply> {
	
	private static final long serialVersionUID = 1L;
	private String applyCode;		// 申请编号
	private String equipCode;		// 设备编号
	private String applicantCode;		// 申请人编号
	private String approverCode;		// 审批人编号
	private String maintainerCode;		// 维修人编号
	private Date applyDate;		// 申请时间
	private String applyReason;		// 报修原因
	private String applyStatus;		// 报修状态（0待承接，1已承接，2维修成功，3维修失败）
	private String applyLevel;		// 申请级别（0一般，1紧急）
	private EquipInfo equipInfo;  //声明设备信息，在前台显示设备的相关信息
	private User user;//声明用户信息实例，在前台显示用户的相关信息
	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }

	public EquipInfo getEquipInfo() { return equipInfo; }

	public void setEquipInfo(EquipInfo equipInfo) { this.equipInfo = equipInfo; }
	public EquipMaintainApply() {
		this(null);
	}

	public EquipMaintainApply(String id){
		super(id);
	}
	
	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
	
	@NotBlank(message="设备编号不能为空")
	@Length(min=0, max=255, message="设备编号长度不能超过 255 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=255, message="申请人编号长度不能超过 255 个字符")
	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	
	@Length(min=0, max=255, message="审批人编号长度不能超过 255 个字符")
	public String getApproverCode() {
		return approverCode;
	}

	public void setApproverCode(String approverCode) {
		this.approverCode = approverCode;
	}
	
	@Length(min=0, max=255, message="维修人编号长度不能超过 255 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	
	@Length(min=0, max=255, message="报修原因长度不能超过 255 个字符")
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	
	@Length(min=0, max=1, message="报修状态长度不能超过 1 个字符")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Length(min=0, max=255, message="申请级别长度不能超过 255 个字符")
	public String getApplyLevel() {
		return applyLevel;
	}

	public void setApplyLevel(String applyLevel) {
		this.applyLevel = applyLevel;
	}
	
}