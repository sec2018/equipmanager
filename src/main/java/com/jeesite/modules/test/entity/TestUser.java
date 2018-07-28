/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * test_userEntity
 * @author jyf
 * @version 2018-07-12
 */
@Table(name="test_user", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="user_name", attrName="userName", label="user_name", queryType=QueryType.LIKE),
		@Column(name="user_password", attrName="userPassword", label="user_password"),
	}, orderBy="a.id DESC"
)
public class TestUser extends DataEntity<TestUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// user_name
	private String userPassword;		// user_password
	
	public TestUser() {
		this(null);
	}

	public TestUser(String id){
		super(id);
	}
	
	@NotBlank(message="user_name不能为空")
	@Length(min=0, max=255, message="user_name长度不能超过 255 个字符")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=255, message="user_password长度不能超过 255 个字符")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}