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
 * 巡检结果Entity
 * @author dyl
 * @version 2018-08-17
 */
@Table(name="inspect_result", alias="a", columns={
		@Column(name="inspect_result_code", attrName="inspectResultCode", label="巡检结果编号", isPK=true),
		@Column(name="inspect_plan_code", attrName="inspectPlanCode", label="巡检计划编号"),
		@Column(name="inspect_plan_name", attrName="inspectPlanName", label="巡检计划名称", queryType=QueryType.LIKE),
		@Column(name="inspecter_code", attrName="inspecterCode", label="巡检员编号"),
		@Column(name="inspect_start_date", attrName="inspectStartDate", label="巡检开始时间"),
		@Column(name="inspect_end_date", attrName="inspectEndDate", label="巡检结束时间"),
		@Column(name="inspect_hours", attrName="inspectHours", label="巡检工时"),
		@Column(name="inspect_result_depict", attrName="inspectResultDepict", label="巡检结果描述"),
		@Column(name="inspect_result_image", attrName="inspectResultImage", label="巡检结果图片"),
		@Column(name="inspect_result_type", attrName="inspectResultType", label="巡检结果类型", comment="巡检结果类型（0-正常、1-异常）"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	},joinTable= {
		@JoinTable(type = Type.LEFT_JOIN, entity = User.class, attrName = "user", alias = "u",
				on = "u.user_code = a.inspecter_code", columns = {
				//@Column(name = "user_code", label = "用户编码", isPK = true),
				@Column(name = "user_name", label = "用户名称", isQuery = false),
		}),
		@JoinTable(type = Type.LEFT_JOIN, entity = InspectPlan.class, attrName = "inspectPlan", alias = "p",
				on = "p.inspect_plan_code = a.inspect_plan_code", columns = {
				@Column(name = "tree_name", label = "节点名称", isQuery = true),
		})
}, orderBy="a.update_date DESC"
)
public class InspectResult extends DataEntity<InspectResult> {
	
	private static final long serialVersionUID = 1L;
	private String inspectResultCode;		// 巡检结果编号
	private String inspectPlanCode;		// 巡检计划编号
	private String inspectPlanName;		// 巡检计划名称
	private String inspecterCode;		// 巡检员编号
	private Date inspectStartDate;		// 巡检开始时间
	private Date inspectEndDate;		// 巡检结束时间
	private Double inspectHours;		// 巡检工时
	private String inspectResultDepict;		// 巡检结果描述
	private String inspectResultImage;		// 巡检结果图片
	private String inspectResultType;		// 巡检结果类型（0-正常、1-异常）
	private Extend extend;		// 扩展字段
	private User user;
	private InspectPlan inspectPlan;

	public InspectPlan getInspectPlan() { return inspectPlan; }

	public void setInspectPlan(InspectPlan inspectPlan) { this.inspectPlan = inspectPlan; }

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }
	
	public InspectResult() {
		this(null);
	}

	public InspectResult(String id){
		super(id);
	}
	
	public String getInspectResultCode() {
		return inspectResultCode;
	}

	public void setInspectResultCode(String inspectResultCode) {
		this.inspectResultCode = inspectResultCode;
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
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectStartDate() {
		return inspectStartDate;
	}

	public void setInspectStartDate(Date inspectStartDate) {
		this.inspectStartDate = inspectStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectEndDate() {
		return inspectEndDate;
	}

	public void setInspectEndDate(Date inspectEndDate) {
		this.inspectEndDate = inspectEndDate;
	}
	
	public Double getInspectHours() {
		return inspectHours;
	}

	public void setInspectHours(Double inspectHours) {
		this.inspectHours = inspectHours;
	}
	
	@Length(min=0, max=255, message="巡检结果描述长度不能超过 255 个字符")
	public String getInspectResultDepict() {
		return inspectResultDepict;
	}

	public void setInspectResultDepict(String inspectResultDepict) {
		this.inspectResultDepict = inspectResultDepict;
	}
	
	@Length(min=0, max=255, message="巡检结果图片长度不能超过 255 个字符")
	public String getInspectResultImage() {
		return inspectResultImage;
	}

	public void setInspectResultImage(String inspectResultImage) {
		this.inspectResultImage = inspectResultImage;
	}
	
	@Length(min=0, max=1, message="巡检结果类型长度不能超过 1 个字符")
	public String getInspectResultType() {
		return inspectResultType;
	}

	public void setInspectResultType(String inspectResultType) {
		this.inspectResultType = inspectResultType;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}