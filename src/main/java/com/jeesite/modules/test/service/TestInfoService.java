/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.TestInfo;
import com.jeesite.modules.test.dao.TestInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * test_infoService
 * @author jyf
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly=true)
public class TestInfoService extends CrudService<TestInfoDao, TestInfo> {
	
	/**
	 * 获取单条数据
	 * @param testInfo
	 * @return
	 */
	@Override
	public TestInfo get(TestInfo testInfo) {
		return super.get(testInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param testInfo
	 * @return
	 */
	@Override
	public Page<TestInfo> findPage(Page<TestInfo> page, TestInfo testInfo) {
		return super.findPage(page, testInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param testInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TestInfo testInfo) {
		super.save(testInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(testInfo.getId(), "testInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(testInfo.getId(), "testInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param testInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TestInfo testInfo) {
		super.updateStatus(testInfo);
	}
	
	/**
	 * 删除数据
	 * @param testInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TestInfo testInfo) {
		super.delete(testInfo);
	}
	
}