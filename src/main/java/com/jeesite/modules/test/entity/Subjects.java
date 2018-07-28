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
 * subjectsEntity
 * @author jyf
 * @version 2018-07-23
 */
@Table(name="subjects", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="num_sub", attrName="numSub", label="num_sub"),
		@Column(name="subname", attrName="subname", label="subname"),
	}, orderBy="a.id DESC"
)
public class Subjects extends DataEntity<Subjects> {
	
	private static final long serialVersionUID = 1L;
	private String numSub;		// num_sub
	private String subname;		// subname
	
	public Subjects() {
		this(null);
	}

	public Subjects(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="num_sub长度不能超过 255 个字符")
	public String getNumSub() {
		return numSub;
	}

	public void setNumSub(String numSub) {
		this.numSub = numSub;
	}
	
	@Length(min=0, max=255, message="subname长度不能超过 255 个字符")
	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}
	
}