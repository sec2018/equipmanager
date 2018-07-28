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
 * test_infoEntity
 * @author jyf
 * @version 2018-07-13
 */
@Table(name="test_info", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="info", attrName="info", label="info"),
		@Column(name="name", attrName="name", label="name", queryType=QueryType.LIKE),
	}, orderBy="a.id DESC"
)
public class TestInfo extends DataEntity<TestInfo> {
	
	private static final long serialVersionUID = 1L;
	private String info;		// info
	private String name;		// name
	
	public TestInfo() {
		this(null);
	}

	public TestInfo(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="info长度不能超过 255 个字符")
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	@Length(min=0, max=255, message="name长度不能超过 255 个字符")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}