/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.InspectSchedule;
import com.jeesite.modules.test.dao.InspectScheduleDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 巡检安排Service
 * @author dyl
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly=true)
public class InspectScheduleService extends CrudService<InspectScheduleDao, InspectSchedule> {
	
	/**
	 * 获取单条数据
	 * @param inspectSchedule
	 * @return
	 */
	@Override
	public InspectSchedule get(InspectSchedule inspectSchedule) {
		return super.get(inspectSchedule);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param inspectSchedule
	 * @return
	 */
	@Override
	public Page<InspectSchedule> findPage(Page<InspectSchedule> page, InspectSchedule inspectSchedule) {
		return super.findPage(page, inspectSchedule);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param inspectSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(InspectSchedule inspectSchedule) {
		super.save(inspectSchedule);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(inspectSchedule.getId(), "inspectSchedule_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(inspectSchedule.getId(), "inspectSchedule_file");
	}
	
	/**
	 * 更新状态
	 * @param inspectSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(InspectSchedule inspectSchedule) {
		super.updateStatus(inspectSchedule);
	}
	
	/**
	 * 删除数据
	 * @param inspectSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(InspectSchedule inspectSchedule) {
		super.delete(inspectSchedule);
	}
	
}