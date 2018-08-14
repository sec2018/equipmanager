/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.CheckSchedule;
import com.jeesite.modules.test.dao.CheckScheduleDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 点检安排Service
 * @author jyf
 * @version 2018-08-14
 */
@Service
@Transactional(readOnly=true)
public class CheckScheduleService extends CrudService<CheckScheduleDao, CheckSchedule> {
	
	/**
	 * 获取单条数据
	 * @param checkSchedule
	 * @return
	 */
	@Override
	public CheckSchedule get(CheckSchedule checkSchedule) {
		return super.get(checkSchedule);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param checkSchedule
	 * @return
	 */
	@Override
	public Page<CheckSchedule> findPage(Page<CheckSchedule> page, CheckSchedule checkSchedule) {
		return super.findPage(page, checkSchedule);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param checkSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CheckSchedule checkSchedule) {
		super.save(checkSchedule);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(checkSchedule.getId(), "checkSchedule_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(checkSchedule.getId(), "checkSchedule_file");
	}
	
	/**
	 * 更新状态
	 * @param checkSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CheckSchedule checkSchedule) {
		super.updateStatus(checkSchedule);
	}
	
	/**
	 * 删除数据
	 * @param checkSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CheckSchedule checkSchedule) {
		super.delete(checkSchedule);
	}
	
}