/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.entity.Extend;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 设备调拨信息Entity
 * @author dang
 * @version 2018-08-06
 */
@Table(name="equip_transfer_info", alias="a", columns={
		@Column(name="transfer_code", attrName="transferCode", label="调拨编号", isPK=true),
		@Column(name="equip_code", attrName="equipCode", label="设备编号"),
		@Column(name="transfer_type", attrName="transferType", label="调拨类型", comment="调拨类型（临时、永久）"),
		@Column(name="transfer_date", attrName="transferDate", label="调拨时间"),
		@Column(name="return_date", attrName="returnDate", label="归还时间"),
		@Column(name="dept_out_code", attrName="deptOutCode", label="调出部门"),
		@Column(name="dept_in_code", attrName="deptInCode", label="调入部门"),
		@Column(name="transfer_position", attrName="transferPosition", label="安放地点"),
		@Column(name="transfer_reason", attrName="transferReason", label="调拨原因"),
		@Column(name="applicant_code", attrName="applicantCode", label="申请者编号"),
		@Column(name="approval_code", attrName="approvalCode", label="审批者编号"),
		@Column(name="transfer_status", attrName="transferStatus", label="调拨状态", comment="调拨状态(0待审核，1审核通过，2驳回)"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	}, joinTable= {
		@JoinTable(type = Type.JOIN, entity = EquipInfo.class, attrName = "equipInfo", alias = "e",
				on = "e.equip_id = a.equip_code", columns = {
				@Column(name = "equip_manager_code", label = "审批者编号", queryType = QueryType.LIKE),
				@Column(name = "equip_manager", label = "审批者名称", queryType = QueryType.LIKE)
		}),
//		@JoinTable(type = Type.JOIN, entity = User.class, attrName = "user", alias = "u",
//				on = "u.user_code = a.applicant_code", columns = {
//				@Column(name = "user_name", label = "申请人姓名", queryType = QueryType.LIKE)
//		}),
		@JoinTable(type = Type.JOIN, entity = Employee.class, attrName = "employee", alias = "e2",
				on = "e2.emp_code = a.applicant_code", columns = {
				@Column(name="emp_name", label="员工姓名", queryType = QueryType.LIKE),
				@Column(name="office_name", label="调入部门", queryType = QueryType.LIKE)
		}),
//		@JoinTable(type=Type.LEFT_JOIN, entity=Office.class, attrName="office", alias="o",
//				on="o.office_code = a.dept_in_code", columns={
//				@Column(name="office_code", label="部门编号", isPK=true),
//				@Column(name="office_name", label="部门名称", isQuery=false),
//		}),
},orderBy="a.update_date DESC"
)
public class EquipTransferInfo extends DataEntity<EquipTransferInfo> {
	
	private static final long serialVersionUID = 1L;
	private String transferCode;		// 调拨编号
	private String equipCode;		// 设备编号
	private String transferType;		// 调拨类型（临时、永久）
	private Date transferDate;		// 调拨时间
	private Date returnDate;		// 归还时间
	private String deptOutCode;		// 调出部门
	private String deptInCode;		// 调入部门
	private String transferPosition;		// 安放地点
	private String transferReason;		// 调拨原因
	private String applicantCode;		// 申请者编号
	private String approvalCode;		// 审批者编号
	private String transferStatus;		// 调拨状态(0待审核，1审核通过，2驳回)
	private Extend extend;		// 扩展字段
	private EquipInfo equipInfo;  //声明设备信息，在前台显示设备的相关信息
	private User user;//声明用户信息实例，在前台显示用户的相关信息
	//private Office office;
	private  Employee employee;
	public Employee getEmployee() { return employee; }

	public void setEmployee(Employee employee) { this.employee = employee; }

	//public Office getOffice() { return office; }

	//public void setOffice(Office office) { this.office = office; }

	public User getUser() { return user; }

	public void setUser(User user) { this.user = user; }

	public EquipInfo getEquipInfo() { return equipInfo; }

	public void setEquipInfo(EquipInfo equipInfo) { this.equipInfo = equipInfo; }
	
	public EquipTransferInfo() {
		this(null);
	}

	public EquipTransferInfo(String id){
		super(id);
	}
	
	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}
	
	@NotBlank(message="设备编号不能为空")
	@Length(min=0, max=255, message="设备编号长度不能超过 255 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
	}
	
	@Length(min=0, max=1, message="调拨类型长度不能超过 1 个字符")
	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTransferDate() {
		return transferDate;
	}

	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	@Length(min=0, max=255, message="调出部门长度不能超过 255 个字符")
	public String getDeptOutCode() {
		return deptOutCode;
	}

	public void setDeptOutCode(String deptOutCode) {
		this.deptOutCode = deptOutCode;
	}
	
	@Length(min=0, max=255, message="调入部门长度不能超过 255 个字符")
	public String getDeptInCode() {
		return deptInCode;
	}

	public void setDeptInCode(String deptInCode) {
		this.deptInCode = deptInCode;
	}
	
	@Length(min=0, max=255, message="安放地点长度不能超过 255 个字符")
	public String getTransferPosition() {
		return transferPosition;
	}

	public void setTransferPosition(String transferPosition) {
		this.transferPosition = transferPosition;
	}
	
	@Length(min=0, max=255, message="调拨原因长度不能超过 255 个字符")
	public String getTransferReason() {
		return transferReason;
	}

	public void setTransferReason(String transferReason) {
		this.transferReason = transferReason;
	}
	
	@Length(min=0, max=255, message="申请者编号长度不能超过 255 个字符")
	public String getApplicantCode() {
		return applicantCode;
	}

	public void setApplicantCode(String applicantCode) {
		this.applicantCode = applicantCode;
	}
	
	@Length(min=0, max=255, message="审批者编号长度不能超过 255 个字符")
	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}
	
	@Length(min=0, max=1, message="调拨状态长度不能超过 1 个字符")
	public String getTransferStatus() {
		return transferStatus;
	}

	public void setTransferStatus(String transferStatus) {
		this.transferStatus = transferStatus;
	}
	
	public Extend getExtend() {
		return extend;
	}

	public void setExtend(Extend extend) {
		this.extend = extend;
	}
	
}