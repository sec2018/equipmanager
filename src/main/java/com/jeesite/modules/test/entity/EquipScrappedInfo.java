/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * equip_scrapped_infoEntity
 * @author jyf
 * @version 2018-07-27
 */
@Table(name="equip_scrapped_info", alias="a", columns={
		@Column(name="scrapped_id", attrName="scrappedId", label="报废编号", isPK=true),
		@Column(name="equip_id", attrName="equipId", label="设备编号"),
		@Column(name="scrapped_date", attrName="scrappedDate", label="报废时间"),
		@Column(name="scrapped_reason", attrName="scrappedReason", label="报废原因"),
		@Column(name="scrapped_applicant", attrName="scrappedApplicant", label="报废申请人"),
		@Column(name="scrapped_approval", attrName="scrappedApproval", label="报废审批人"),
		@Column(name="apply_status", attrName="applyStatus", label="申请状态", comment="申请状态（0-待审核 1-已审核）"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="del_flag", attrName="delFlag", label="删除标记"),
	},joinTable={
		@JoinTable(type= Type.JOIN, entity=EquipInfo.class, attrName="equipInfo", alias="u10",
				on="u10.equip_id = a.equip_id", columns={
				@Column(name="equip_manager", label="设备管理员", queryType =  QueryType.LIKE)
		})
}, orderBy="a.update_date DESC"
)
public class EquipScrappedInfo extends DataEntity<EquipScrappedInfo> {
	
	private static final long serialVersionUID = 1L;
	private String scrappedId;		// 报废编号
	private String equipId;		// 设备编号
	private Date scrappedDate;		// 报废时间
	private String scrappedReason;		// 报废原因
	private String scrappedApplicant;		// 报废申请人
	private String scrappedApproval;		// 报废审批人
	private String applyStatus;		// 申请状态（0-待审核 1-已审核）
	private String delFlag;		// 删除标记
	private EquipInfo equipInfo;  //设备信息表

	public EquipInfo getEquipInfo() {
		return equipInfo;
	}

	public void setEquipInfo(EquipInfo equipInfo) {
		this.equipInfo = equipInfo;
	}


	
	public EquipScrappedInfo() {
		this(null);
	}

	public EquipScrappedInfo(String id){
		super(id);
	}
	
	public String getScrappedId() {
		return scrappedId;
	}

	public void setScrappedId(String scrappedId) {
		this.scrappedId = scrappedId;
	}
	
	@NotBlank(message="设备编号不能为空")
	@Length(min=0, max=64, message="设备编号长度不能超过 64 个字符")
	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getScrappedDate() {
		return scrappedDate;
	}

	public void setScrappedDate(Date scrappedDate) {
		this.scrappedDate = scrappedDate;
	}
	
	@Length(min=0, max=50, message="报废原因长度不能超过 50 个字符")
	public String getScrappedReason() {
		return scrappedReason;
	}

	public void setScrappedReason(String scrappedReason) {
		this.scrappedReason = scrappedReason;
	}
	
	@Length(min=0, max=50, message="报废申请人长度不能超过 50 个字符")
	public String getScrappedApplicant() {
		return scrappedApplicant;
	}

	public void setScrappedApplicant(String scrappedApplicant) {
		this.scrappedApplicant = scrappedApplicant;
	}
	
	@Length(min=0, max=50, message="报废审批人长度不能超过 50 个字符")
	public String getScrappedApproval() {
		return scrappedApproval;
	}

	public void setScrappedApproval(String scrappedApproval) {
		this.scrappedApproval = scrappedApproval;
	}
	
	@Length(min=0, max=1, message="申请状态长度不能超过 1 个字符")
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	
	@Length(min=0, max=1, message="删除标记长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}