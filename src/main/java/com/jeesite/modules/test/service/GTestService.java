/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.GTest;
import com.jeesite.modules.test.dao.GTestDao;

/**
 * 班级表Service
 * @author jyf
 * @version 2018-07-21
 */
@Service
@Transactional(readOnly=true)
public class GTestService extends CrudService<GTestDao, GTest> {
	
	/**
	 * 获取单条数据
	 * @param gTest
	 * @return
	 */
	@Override
	public GTest get(GTest gTest) {
		return super.get(gTest);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param gTest
	 * @return
	 */
	@Override
	public Page<GTest> findPage(Page<GTest> page, GTest gTest) {
		return super.findPage(page, gTest);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param gTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(GTest gTest) {
		super.save(gTest);
	}
	
	/**
	 * 更新状态
	 * @param gTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(GTest gTest) {
		super.updateStatus(gTest);
	}
	
	/**
	 * 删除数据
	 * @param gTest
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(GTest gTest) {
		super.delete(gTest);
	}
	
}