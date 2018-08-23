/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.modules.sys.entity.Employee;
import com.jeesite.modules.sys.entity.Office;
import com.jeesite.modules.sys.entity.Role;
import com.jeesite.modules.sys.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * equip_infoEntity
 * @author jyf
 * @version 2018-07-27
 */
@Table(extFromKeys="dsf",extWhereKeys="dsf",name="equip_info", alias="a", columns={
		@Column(name="id", attrName="id", label="索引号", isPK=true),
		@Column(name="equip_id", attrName="equipId", label="设备编号"),
		@Column(name="equip_manager_code", attrName="equipManagerCode", label="设备管理员编号"),
		@Column(name="equip_manager", attrName="equipManager", label="设备管理员"),
		@Column(name="equip_name", attrName="equipName", label="设备名称", queryType=QueryType.LIKE),
		@Column(name="equip_type", attrName="equipType", label="设备类型"),
		@Column(name="equip_model", attrName="equipModel", label="设备型号"),
		@Column(name="manufacture", attrName="manufacture", label="生产厂商"),
		@Column(name="equip_price", attrName="equipPrice", label="设备价格"),
		@Column(name="equip_position", attrName="equipPosition", label="安放地点"),
		@Column(name="dept_id", attrName="deptId", label="设备所属部门"),
		@Column(name="equip_status", attrName="equipStatus", label="设备状态", comment="设备状态（0-正常 1-报修 2-报废）"),
		@Column(name="buy_time", attrName="buyTime", label="购置时间"),
		@Column(name="equip_life", attrName="equipLife", label="设备使用年限"),
		@Column(name="check_plan_code", attrName="checkPlanCode", label="点检计划编号"),
		@Column(name="inspect_plan_code", attrName="inspectPlanCode", label="巡检计划编号"),
		@Column(includeEntity=DataEntity.class),
		@Column(name="del_flag", attrName="delFlag", label="删除标记"),
	}, joinTable={
		@JoinTable(type=Type.LEFT_JOIN, entity= User.class, attrName="testUser", alias="u10",
		on="u10.user_code = a.equip_manager_code", columns={
		@Column(name="user_code", label="用户编码", isPK=true),
		@Column(name="user_name", label="用户名称", isQuery=false),
        }),
//		@JoinTable(type=Type.LEFT_JOIN, entity= Office.class, attrName="testOffice", alias="u11",
//				on="u11.office_code = a.dept_id", columns={
//				@Column(name="office_code", label="机构编码", isPK=true),
//				@Column(name="office_name", label="机构名称", isQuery=true),
//		}),
//		<通过下面的关联查询获取设备所属部门20180823>
		@JoinTable(type=Type.LEFT_JOIN, entity= Employee.class, attrName="employee", alias="u12",
				on="u12.emp_code = a.equip_manager_code", columns={
				//@Column(name="emp_code", label="员工编码", isPK=true),
				@Column(name="office_code", label="机构名称", isQuery=true),
				//@Column(name="office_name", label="机构名称", isQuery=true),
		}),
		@JoinTable(type=Type.LEFT_JOIN, entity= Office.class, attrName="testOffice", alias="u11",
				on="u11.office_code = u12.office_code", columns={
				//@Column(name="office_code", label="机构编码", isPK=true),
				@Column(name="office_name", label="机构名称", isQuery=true),
		})


}, orderBy="a.update_date DESC"
)
public class EquipInfo extends DataEntity<EquipInfo> {
	
	private static final long serialVersionUID = 1L;
	private String equipId;		// 设备编号
	private String equipManagerCode;		// 设备管理员编号
	private String equipManager;		// 设备管理员
	private String equipName;		// 设备名称
	private String equipType;		// 设备类型
	private String equipModel;		// 设备型号
	private String manufacture;		// 生产厂商
	private Double equipPrice;		// 设备价格
	private String equipPosition;		// 安放地点
	private String deptId;		// 设备所属部门
	private String equipStatus;		// 设备状态（0-正常 1-报修 2-报废）
	private Date buyTime;		// 购置时间
	private String equipLife;		// 设备使用年限
	private String checkPlanCode;		// 点检计划编号
	private String inspectPlanCode;		// 巡检计划编号
	private String delFlag;		// 删除标记
	private User testUser;		// 用户选择
	private Office testOffice;		// 机构选择
	private Employee employee; //员工（部门）数据
	public String getCheckPlanCode() { return checkPlanCode; }

	public void setCheckPlanCode(String checkPlanCode) { this.checkPlanCode = checkPlanCode; }

	public String getInspectPlanCode() { return inspectPlanCode; }

	public void setInspectPlanCode(String inspectPlanCode) { this.inspectPlanCode = inspectPlanCode; }

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




	
	public EquipInfo() {
		this(null);
	}

	public EquipInfo(String id){
		super(id);
	}
	
	@Length(min=0, max=64, message="设备编号长度不能超过 64 个字符")
	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}
	
	@Length(min=0, max=64, message="设备管理员编号长度不能超过 64 个字符")
	public String getEquipManagerCode() {
		return equipManagerCode;
	}

	public void setEquipManagerCode(String equipManagerCode) {
		this.equipManagerCode = equipManagerCode;
	}
	
	@Length(min=0, max=255, message="设备管理员长度不能超过 255 个字符")
	public String getEquipManager() {
		return equipManager;
	}

	public void setEquipManager(String equipManager) {
		this.equipManager = equipManager;
	}
	
	@Length(min=0, max=255, message="设备名称长度不能超过 255 个字符")
	public String getEquipName() {
		return equipName;
	}

	public void setEquipName(String equipName) {
		this.equipName = equipName;
	}
	
	@Length(min=0, max=255, message="设备类型长度不能超过 255 个字符")
	public String getEquipType() {
		return equipType;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}
	
	@Length(min=0, max=255, message="设备型号长度不能超过 255 个字符")
	public String getEquipModel() {
		return equipModel;
	}

	public void setEquipModel(String equipModel) {
		this.equipModel = equipModel;
	}
	
	@Length(min=0, max=255, message="生产厂商长度不能超过 255 个字符")
	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	
	public Double getEquipPrice() {
		return equipPrice;
	}

	public void setEquipPrice(Double equipPrice) {
		this.equipPrice = equipPrice;
	}
	
	@Length(min=0, max=255, message="安放地点长度不能超过 255 个字符")
	public String getEquipPosition() {
		return equipPosition;
	}

	public void setEquipPosition(String equipPosition) {
		this.equipPosition = equipPosition;
	}
	

	@Length(min=0, max=255, message="设备所属部门长度不能超过 255 个字符")
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
	@Length(min=0, max=1, message="设备状态长度不能超过 1 个字符")
	public String getEquipStatus() {
		return equipStatus;
	}

	public void setEquipStatus(String equipStatus) {
		this.equipStatus = equipStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	
	@Length(min=0, max=255, message="设备使用年限长度不能超过 255 个字符")
	public String getEquipLife() {
		return equipLife;
	}

	public void setEquipLife(String equipLife) {
		this.equipLife = equipLife;
	}
	
	@Length(min=0, max=1, message="删除标记长度不能超过 1 个字符")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
}