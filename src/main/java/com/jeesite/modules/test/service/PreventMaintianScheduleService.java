/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.PreventMaintianSchedule;
import com.jeesite.modules.test.dao.PreventMaintianScheduleDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 预防性维护计划安排Service
 * @author dyl
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly=true)
public class PreventMaintianScheduleService extends CrudService<PreventMaintianScheduleDao, PreventMaintianSchedule> {
	
	/**
	 * 获取单条数据
	 * @param preventMaintianSchedule
	 * @return
	 */
	@Override
	public PreventMaintianSchedule get(PreventMaintianSchedule preventMaintianSchedule) {
		return super.get(preventMaintianSchedule);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param preventMaintianSchedule
	 * @return
	 */
	@Override
	public Page<PreventMaintianSchedule> findPage(Page<PreventMaintianSchedule> page, PreventMaintianSchedule preventMaintianSchedule) {
		return super.findPage(page, preventMaintianSchedule);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param preventMaintianSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PreventMaintianSchedule preventMaintianSchedule) {
		super.save(preventMaintianSchedule);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(preventMaintianSchedule.getId(), "preventMaintianSchedule_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(preventMaintianSchedule.getId(), "preventMaintianSchedule_file");
	}
	
	/**
	 * 更新状态
	 * @param preventMaintianSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PreventMaintianSchedule preventMaintianSchedule) {
		super.updateStatus(preventMaintianSchedule);
	}
	
	/**
	 * 删除数据
	 * @param preventMaintianSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PreventMaintianSchedule preventMaintianSchedule) {
		super.delete(preventMaintianSchedule);
	}
	
}