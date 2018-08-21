/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.RegularMaintainResult;
import com.jeesite.modules.test.dao.RegularMaintainResultDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 定修结果维护Service
 * @author dyl
 * @version 2018-08-21
 */
@Service
@Transactional(readOnly=true)
public class RegularMaintainResultService extends CrudService<RegularMaintainResultDao, RegularMaintainResult> {
	
	/**
	 * 获取单条数据
	 * @param regularMaintainResult
	 * @return
	 */
	@Override
	public RegularMaintainResult get(RegularMaintainResult regularMaintainResult) {
		return super.get(regularMaintainResult);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param regularMaintainResult
	 * @return
	 */
	@Override
	public Page<RegularMaintainResult> findPage(Page<RegularMaintainResult> page, RegularMaintainResult regularMaintainResult) {
		return super.findPage(page, regularMaintainResult);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param regularMaintainResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(RegularMaintainResult regularMaintainResult) {
		super.save(regularMaintainResult);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(regularMaintainResult.getId(), "regularMaintainResult_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(regularMaintainResult.getId(), "regularMaintainResult_file");
	}
	
	/**
	 * 更新状态
	 * @param regularMaintainResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(RegularMaintainResult regularMaintainResult) {
		super.updateStatus(regularMaintainResult);
	}
	
	/**
	 * 删除数据
	 * @param regularMaintainResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(RegularMaintainResult regularMaintainResult) {
		super.delete(regularMaintainResult);
	}
	
}