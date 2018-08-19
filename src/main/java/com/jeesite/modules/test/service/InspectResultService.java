/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.InspectResult;
import com.jeesite.modules.test.dao.InspectResultDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 巡检结果Service
 * @author dyl
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly=true)
public class InspectResultService extends CrudService<InspectResultDao, InspectResult> {
	
	/**
	 * 获取单条数据
	 * @param inspectResult
	 * @return
	 */
	@Override
	public InspectResult get(InspectResult inspectResult) {
		return super.get(inspectResult);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param inspectResult
	 * @return
	 */
	@Override
	public Page<InspectResult> findPage(Page<InspectResult> page, InspectResult inspectResult) {
		return super.findPage(page, inspectResult);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param inspectResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(InspectResult inspectResult) {
		super.save(inspectResult);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(inspectResult.getId(), "inspectResult_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(inspectResult.getId(), "inspectResult_file");
	}
	
	/**
	 * 更新状态
	 * @param inspectResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(InspectResult inspectResult) {
		super.updateStatus(inspectResult);
	}
	
	/**
	 * 删除数据
	 * @param inspectResult
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(InspectResult inspectResult) {
		super.delete(inspectResult);
	}
	
}