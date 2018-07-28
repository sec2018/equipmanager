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
 * grade_subj_relateEntity
 * @author jyf
 * @version 2018-07-23
 */
@Table(name="grade_subj_relate", alias="a", columns={
		@Column(name="id", attrName="id", label="id", isPK=true),
		@Column(name="grade_id", attrName="gradeId", label="grade_id"),
		@Column(name="num_sub", attrName="numSub", label="num_sub"),
	},joinTable={
		@JoinTable(type= JoinTable.Type.RIGHT_JOIN, entity=Subjects.class, attrName="subjects", alias="u10",
				on="u10.num_sub = a.num_sub", columns={
				@Column(name="id", label="序号", isPK=true),
				@Column(name="num_sub", label="科目编号", queryType =  QueryType.EQ),
				@Column(name="subname", label="科目", queryType =  QueryType.LIKE),
		})

}, orderBy="a.id DESC"
)
public class GradeSubjRelate extends DataEntity<GradeSubjRelate> {
	
	private static final long serialVersionUID = 1L;
	private String gradeId;		// grade_id
	private String numSub;		// num_sub

	@Getter
	@Setter
	private Subjects subjects;		// 科目
	
	public GradeSubjRelate() {
		this(null);
	}

	public GradeSubjRelate(String id){
		super(id);
	}
	
	@Length(min=0, max=255, message="grade_id长度不能超过 255 个字符")
	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	@Length(min=0, max=255, message="num_sub长度不能超过 255 个字符")
	public String getNumSub() {
		return numSub;
	}

	public void setNumSub(String numSub) {
		this.numSub = numSub;
	}
	
}