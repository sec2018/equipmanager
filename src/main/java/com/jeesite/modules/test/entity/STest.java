/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.entity;

import com.jeesite.common.mybatis.annotation.JoinTable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
/**
 * 学生表Entity
 * @author jyf
 * @version 2018-07-21
 */
@Table(name="s_test", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="name", attrName="name", label="name", queryType=QueryType.LIKE),
		@Column(name="age", attrName="age", label="age"),
	},joinTable={
		@JoinTable(type= JoinTable.Type.RIGHT_JOIN, entity=GTest.class, attrName="testGTest", alias="u10",
				on="u10.id = a.id", columns={
				@Column(name="grade", label="年级", queryType =  QueryType.LIKE)
		})

} ,orderBy="a.id DESC"
)


public class STest extends DataEntity<STest> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private Long age;		// age
	@Getter
	@Setter
	private GTest testGTest;		// 年级
	
	public STest() {
		this(null);
	}

	public STest(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="name长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

//	public GTest getTestGTest() {
//		return testGTest;
//	}
//
//	public void setTestGTest(GTest gTest) {
//		this.testGTest = gTest;
//	}
	
}