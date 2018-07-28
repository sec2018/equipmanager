/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.STest;
import com.jeesite.modules.test.dao.STestDao;

/**
 * 学生表Service
 * @author jyf
 * @version 2018-07-21
 */
@Service
@Transactional(readOnly=true)
public class STestService extends CrudService<STestDao, STest> {
	
	/**
	 * 获取单条数据
	 * @param sTest
	 * @return
	 */
	@Override
	public STest get(STest sTest) {
		return super.get(sTest);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param sTest
	 * @return
	 */
	@Override
	public Page<STest> findPage(Page<STest> page, STest sTest) {
		return super.findPage(page, sTest);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param sTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(STest sTest) {
		super.save(sTest);
	}
	
	/**
	 * 更新状态
	 * @param sTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(STest sTest) {
		super.updateStatus(sTest);
	}
	
	/**
	 * 删除数据
	 * @param sTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(STest sTest) {
		super.delete(sTest);
	}
	
}