/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 班级表Entity
 * @author jyf
 * @version 2018-07-21
 */
@Table(name="g_test", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="grade", attrName="grade", label="grade"),
	}, orderBy="a.id DESC"
)
public class GTest extends DataEntity<GTest> {
	
	private static final long serialVersionUID = 1L;
	private String grade;		// grade
	
	public GTest() {
		this(null);
	}

	public GTest(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="grade长度不能超过 255 个字符")
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
}