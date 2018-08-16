/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.CheckResult;
import com.jeesite.modules.test.dao.CheckResultDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 点检结果Service
 * @author jyf
 * @version 2018-08-16
 */
@Service
@Transactional(readOnly=true)
public class CheckResultService extends CrudService<CheckResultDao, CheckResult> {
	
	/**
	 * 获取单条数据
	 * @param checkResult
	 * @return
	 */
	@Override
	public CheckResult get(CheckResult checkResult) {
		return super.get(checkResult);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param checkResult
	 * @return
	 */
	@Override
	public Page<CheckResult> findPage(Page<CheckResult> page, CheckResult checkResult) {
		return super.findPage(page, checkResult);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param checkResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CheckResult checkResult) {
		super.save(checkResult);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(checkResult.getId(), "checkResult_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(checkResult.getId(), "checkResult_file");
	}
	
	/**
	 * 更新状态
	 * @param checkResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CheckResult checkResult) {
		super.updateStatus(checkResult);
	}
	
	/**
	 * 删除数据
	 * @param checkResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CheckResult checkResult) {
		super.delete(checkResult);
	}
	
}