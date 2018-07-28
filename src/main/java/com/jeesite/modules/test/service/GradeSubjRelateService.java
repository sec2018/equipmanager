/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.GradeSubjRelate;
import com.jeesite.modules.test.dao.GradeSubjRelateDao;

/**
 * grade_subj_relateService
 * @author jyf
 * @version 2018-07-23
 */
@Service
@Transactional(readOnly=true)
public class GradeSubjRelateService extends CrudService<GradeSubjRelateDao, GradeSubjRelate> {
	
	/**
	 * 获取单条数据
	 * @param gradeSubjRelate
	 * @return
	 */
	@Override
	public GradeSubjRelate get(GradeSubjRelate gradeSubjRelate) {
		return super.get(gradeSubjRelate);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param gradeSubjRelate
	 * @return
	 */
	@Override
	public Page<GradeSubjRelate> findPage(Page<GradeSubjRelate> page, GradeSubjRelate gradeSubjRelate) {
		return super.findPage(page, gradeSubjRelate);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param gradeSubjRelate
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(GradeSubjRelate gradeSubjRelate) {
		super.save(gradeSubjRelate);
	}
	
	/**
	 * 更新状态
	 * @param gradeSubjRelate
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(GradeSubjRelate gradeSubjRelate) {
		super.updateStatus(gradeSubjRelate);
	}
	
	/**
	 * 删除数据
	 * @param gradeSubjRelate
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(GradeSubjRelate gradeSubjRelate) {
		super.delete(gradeSubjRelate);
	}
	
}