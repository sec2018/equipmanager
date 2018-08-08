/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.entity.UserRole;
import org.hibernate.validator.constraints.NotBlank;
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
 * component_applicationEntity
 * @author jyf
 * @version 2018-08-08
 */
@Table(name="component_application", alias="a", columns={
		@Column(name="component_code", attrName="componentCode", label="备品备件编号"),
		@Column(name="application_code", attrName="applicationCode", label="备品备件申领编号", isPK=true),
		@Column(name="equip_code", attrName="equipCode", label="装配设备编号"),
		@Column(name="applicant_code", attrName="applicantCode", label="备品备件申领人编号"),
		@Column(name="approval_code", attrName="approvalCode", label="备品备件申领审批人编号"),
		@Column(name="apply_sort", attrName="applySort", label="apply_sort"),
		@Column(name="application_number", attrName="applicationNumber", label="申领数量"),
		@Column(name="application_reason", attrName="applicationReason", label="申领原因"),
		@Column(name="application_submit_date", attrName="applicationSubmitDate", label="申领提交时间"),
		@Column(name="application_end_date", attrName="applicationEndDate", label="申领完成时间"),
		@Column(name="application_status", attrName="applicationStatus", label="申领状态", comment="申领状态(0-待审核 1-审核通过 2-驳回)"),
		@Column(includeEntity=DataEntity.class),
		@Column(includeEntity=Extend.class, attrName="extend"),
	},joinTable={
		@JoinTable(type= Type.LEFT_JOIN, entity = UserRole.class ,  attrName = "userRole" , alias = "u12", on ="a.approval_code=u12.role_code" ,columns = {
				@Column(name="user_code", label="用户编码", isPK=true),
				@Column(name="role_code", label="用户角色", isQuery=true),
		}),
		@JoinTable(type=Type.LEFT_JOIN, entity= User.class, attrName="testUser", alias="u10",
				on="u10.user_code = u12.user_code", columns={
				@Column(name="user_code", label="用户编码", isPK=true),
				@Column(name="user_name", label="用户名称", isQuery=false),
		}),
//		@JoinTable(type= Type.LEFT_JOIN, entity = ComponentApplicationChild.class ,  attrName = "componentApplyChild" , alias = "u8", on ="a.application_code=u8.application_code" ,columns = {
//				@Column(name="component_code", label="备品备件编码", isPK=true),
//				@Column(name="equip_code", label="设备编码", isQuery=true),
//		}),
//		@JoinTable(type= Type.LEFT_JOIN, entity = ComponentInfo.class ,  attrName = "componentInfo" , alias = "u13", on ="u8.component_code=u13.component_code" ,columns = {
//				@Column(name="component_name", label="备品备件名称", isQuery=true),
//				@Column(name="create_by", label="创建者", isQuery=true),
//		}),
//		@JoinTable(type=Type.LEFT_JOIN, entity= Office.class, attrName="testOffice", alias="u11",
//				on="u11.office_code = a.dept_id", columns={
//				@Column(name="office_code", label="机构编码", isPK=true),
//				@Column(name="office_name", label="机构名称", isQuery=true),
//		}),
//		@JoinTable(type=Type.LEFT_JOIN, entity= Employee.class, attrName="employee", alias="u12",
//				on="u12.emp_code = a.equip_manager_code", columns={
//				@Column(name="emp_code", label="员工编码", isPK=true),
//				@Column(name="office_code", label="机构名称", isQuery=true),
//				@Column(name="office_name", label="机构名称", isQuery=true),
//		})

}, orderBy="a.update_date DESC"
)
public class ComponentApplication extends DataEntity<ComponentApplication> {
	
	private static final long serialVersionUID = 1L;
	private String componentCode;		// 备品备件编号
	private String applicationCode;		// 备品备件申领编号
	private String equipCode;		// 装配设备编号
	private String applicantCode;		// 备品备件申领人编号
	private String approvalCode;		// 备品备件申领审批人编号
	private Long applySort;		// apply_sort
	private Long applicationNumber;		// 申领数量
	private String applicationReason;		// 申领原因
	private Date applicationSubmitDate;		// 申领提交时间
	private Date applicationEndDate;		// 申领完成时间
	private String applicationStatus;		// 申领状态(0-待审核 1-审核通过 2-驳回)
	private Extend extend;		// 扩展字段
	private List<ComponentApplicationChild> componentApplicationChildList = ListUtils.newArrayList();		// 子表列表
	private User testUser;		// 用户选择
	private Office testOffice;		// 机构选择
	private Employee employee; //员工（部门）数据
	private UserRole userRole;  //用户角色
	private ComponentInfo componentInfo ; //备品备件
	//private ComponentApplicationChild componentApplicationChild;   //备品备件申领子表
	public ComponentInfo getComponentInfo() {
		return componentInfo;
	}

	public void setComponentInfo(ComponentInfo componentInfo) {
		this.componentInfo = componentInfo;
	}
//	public ComponentApplicationChild getComponentApplicationChild() {
//		return componentApplicationChild;
//	}

//	public void setComponentApplicationChild(ComponentApplicationChild componentApplicationChild) {
//		this.componentApplicationChild = componentApplicationChild;
//	}



	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}



	public User getTestUser() {
		return testUser;
	}

	public void setTestUser(User testUser) {
		this.testUser = testUser;
	}


	public Office getTestOffice() {
		return testOffice;
	}

	public void setTestOffice(Office testOffice) {
		this.testOffice = testOffice;
	}
	
	public ComponentApplication() {
		this(null);
	}

	public ComponentApplication(String id){
		super(id);
	}
	

	@Length(min=0, max=255, message="备品备件编号长度不能超过 255 个字符")
	public String getComponentCode() {
		return componentCode;
	}

	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}
	
	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	
	@Length(min=0, max=255, message="装配设备编号长度不能超过 255 个字符")
	public String getEquipCode() {
		return equipCode;
	}

	public void setEquipCode(String equipCode) {
		this.equipCode = equipCode;
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
	
	public Long getApplySort() {
		return applySort;
	}

	public void setApplySort(Long applySort) {
		this.applySort = applySort;
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
	
	public List<ComponentApplicationChild> getComponentApplicationChildList() {
		return componentApplicationChildList;
	}

	public void setComponentApplicationChildList(List<ComponentApplicationChild> componentApplicationChildList) {
		this.componentApplicationChildList = componentApplicationChildList;
	}
	
}