/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.test.entity.CheckPlans;
import com.jeesite.modules.test.dao.CheckPlansDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 点检计划Service
 * @author jyf
 * @version 2018-08-14
 */
@Service
@Transactional(readOnly=true)
public class CheckPlansService extends TreeService<CheckPlansDao, CheckPlans> {
	
	/**
	 * 获取单条数据
	 * @param checkPlans
	 * @return
	 */
	@Override
	public CheckPlans get(CheckPlans checkPlans) {
		return super.get(checkPlans);
	}
	
	/**
	 * 查询列表数据
	 * @param checkPlans
	 * @return
	 */
	@Override
	public List<CheckPlans> findList(CheckPlans checkPlans) {
		return super.findList(checkPlans);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param checkPlans
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CheckPlans checkPlans) {
		super.save(checkPlans);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(checkPlans.getId(), "checkPlans_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(checkPlans.getId(), "checkPlans_file");
	}
	
	/**
	 * 更新状态
	 * @param checkPlans
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CheckPlans checkPlans) {
		super.updateStatus(checkPlans);
	}
	
	/**
	 * 删除数据
	 * @param checkPlans
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CheckPlans checkPlans) {
		super.delete(checkPlans);
	}
	
}