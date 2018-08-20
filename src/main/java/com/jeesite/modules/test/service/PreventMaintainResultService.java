/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.PreventMaintainResult;
import com.jeesite.modules.test.dao.PreventMaintainResultDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 预防性维护结果Service
 * @author dyl
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly=true)
public class PreventMaintainResultService extends CrudService<PreventMaintainResultDao, PreventMaintainResult> {
	
	/**
	 * 获取单条数据
	 * @param preventMaintainResult
	 * @return
	 */
	@Override
	public PreventMaintainResult get(PreventMaintainResult preventMaintainResult) {
		return super.get(preventMaintainResult);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param preventMaintainResult
	 * @return
	 */
	@Override
	public Page<PreventMaintainResult> findPage(Page<PreventMaintainResult> page, PreventMaintainResult preventMaintainResult) {
		return super.findPage(page, preventMaintainResult);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param preventMaintainResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PreventMaintainResult preventMaintainResult) {
		super.save(preventMaintainResult);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(preventMaintainResult.getId(), "preventMaintainResult_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(preventMaintainResult.getId(), "preventMaintainResult_file");
	}
	
	/**
	 * 更新状态
	 * @param preventMaintainResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PreventMaintainResult preventMaintainResult) {
		super.updateStatus(preventMaintainResult);
	}
	
	/**
	 * 删除数据
	 * @param preventMaintainResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PreventMaintainResult preventMaintainResult) {
		super.delete(preventMaintainResult);
	}
	
}