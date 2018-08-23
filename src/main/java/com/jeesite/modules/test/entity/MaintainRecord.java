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
import java.util.List;
import com.jeesite.common.collect.ListUtils;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 维修工单Entity
 * @author dyl
 * @version 2018-08-23
 */
@Table(name="maintain_record", alias="a", columns={
		@Column(name="record_code", attrName="recordCode", label="工单编号", isPK=true),
		@Column(name="notice_code", attrName="noticeCode", label="通知单编号"),
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="component_name", attrName="componentName", label="备品备件名称", queryType=QueryType.LIKE),
		@Column(name="component_number", attrName="componentNumber", label="备品备件数量"),
		@Column(name="maintainer_code", attrName="maintainerCode", label="维修人员编号"),
		@Column(name="maintain_start_time", attrName="maintainStartTime", label="维修开始时间"),
		@Column(name="maintain_end_time", attrName="maintainEndTime", label="维修结束时间"),
		@Column(name="maintain_hours", attrName="maintainHours", label="维修工时"),
		@Column(name="maintain_content", attrName="maintainContent", label="维修内容"),
		@Column(name="maintain_method", attrName="maintainMethod", label="维修方法"),
		@Column(name="maintain_run_status", attrName="maintainRunStatus", label="维修时设备运行状态", comment="维修时设备运行状态（0-运行，1-停止）"),
		@Column(name="maintain_result_depict", attrName="maintainResultDepict", label="维修结果描述"),
		@Column(name="maintain_result_type", attrName="maintainResultType", label="维修结果类型", comment="维修结果类型（0-成功，1-失败）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, orderBy="a.update_date DESC"
)
public class MaintainRecord extends DataEntity<MaintainRecord> {
	
	private static final long serialVersionUID = 1L;
	private String recordCode;		// 工单编号
	private String noticeCode;		// 通知单编号
	private String componentCode;		// 备品备件编号
	private String componentName;		// 备品备件名称
	private Long componentNumber;		// 备品备件数量
	private String maintainerCode;		// 维修人员编号
	private Date maintainStartTime;		// 维修开始时间
	private Date maintainEndTime;		// 维修结束时间
	private Double maintainHours;		// 维修工时
	private String maintainContent;		// 维修内容
	private String maintainMethod;		// 维修方法
	private String maintainRunStatus;		// 维修时设备运行状态（0-运行，1-停止）
	private String maintainResultDepict;		// 维修结果描述
	private String maintainResultType;		// 维修结果类型（0-成功，1-失败）
	private Extend extend;		// 扩展字段
	private List<MaintainRecordChild> maintainRecordChildList = ListUtils.newArrayList();		// 子表列表
	
	public MaintainRecord() {
		this(null);
	}

	public MaintainRecord(String id){
		super(id);
	}
	
	public String getRecordCode() {
		return recordCode;
	}

	public void setRecordCode(String recordCode) {
		this.recordCode = recordCode;
	}
	
	@Length(min=0, max=64, message="通知单编号长度不能超过 64 个字符")
	public String getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
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
	
	@Length(min=0, max=64, message="维修人员编号长度不能超过 64 个字符")
	public String getMaintainerCode() {
		return maintainerCode;
	}

	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintainStartTime() {
		return maintainStartTime;
	}

	public void setMaintainStartTime(Date maintainStartTime) {
		this.maintainStartTime = maintainStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintainEndTime() {
		return maintainEndTime;
	}

	public void setMaintainEndTime(Date maintainEndTime) {
		this.maintainEndTime = maintainEndTime;
	}
	
	public Double getMaintainHours() {
		return maintainHours;
	}

	public void setMaintainHours(Double maintainHours) {
		this.maintainHours = maintainHours;
	}
	
	@Length(min=0, max=255, message="维修内容长度不能超过 255 个字符")
	public String getMaintainContent() {
		return maintainContent;
	}

	public void setMaintainContent(String maintainContent) {
		this.maintainContent = maintainContent;
	}
	
	@Length(min=0, max=255, message="维修方法长度不能超过 255 个字符")
	public String getMaintainMethod() {
		return maintainMethod;
	}

	public void setMaintainMethod(String maintainMethod) {
		this.maintainMethod = maintainMethod;
	}
	
	@Length(min=0, max=1, message="维修时设备运行状态长度不能超过 1 个字符")
	public String getMaintainRunStatus() {
		return maintainRunStatus;
	}

	public void setMaintainRunStatus(String maintainRunStatus) {
		this.maintainRunStatus = maintainRunStatus;
	}
	
	@Length(min=0, max=255, message="维修结果描述长度不能超过 255 个字符")
	public String getMaintainResultDepict() {
		return maintainResultDepict;
	}

	public void setMaintainResultDepict(String maintainResultDepict) {
		this.maintainResultDepict = maintainResultDepict;
	}
	
	@Length(min=0, max=1, message="维修结果类型长度不能超过 1 个字符")
	public String getMaintainResultType() {
		return maintainResultType;
	}

	public void setMaintainResultType(String maintainResultType) {
		this.maintainResultType = maintainResultType;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
	public List<MaintainRecordChild> getMaintainRecordChildList() {
		return maintainRecordChildList;
	}

	public void setMaintainRecordChildList(List<MaintainRecordChild> maintainRecordChildList) {
		this.maintainRecordChildList = maintainRecordChildList;
	}
	
}