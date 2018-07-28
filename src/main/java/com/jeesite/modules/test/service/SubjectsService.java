/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.Subjects;
import com.jeesite.modules.test.dao.SubjectsDao;

/**
 * subjectsService
 * @author jyf
 * @version 2018-07-23
 */
@Service
@Transactional(readOnly=true)
public class SubjectsService extends CrudService<SubjectsDao, Subjects> {
	
	/**
	 * 获取单条数据
	 * @param subjects
	 * @return
	 */
	@Override
	public Subjects get(Subjects subjects) {
		return super.get(subjects);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param subjects
	 * @return
	 */
	@Override
	public Page<Subjects> findPage(Page<Subjects> page, Subjects subjects) {
		return super.findPage(page, subjects);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param subjects
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Subjects subjects) {
		super.save(subjects);
	}
	
	/**
	 * 更新状态
	 * @param subjects
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Subjects subjects) {
		super.updateStatus(subjects);
	}
	
	/**
	 * 删除数据
	 * @param subjects
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Subjects subjects) {
		super.delete(subjects);
	}
	
}