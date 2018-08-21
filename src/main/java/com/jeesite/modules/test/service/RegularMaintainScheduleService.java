/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.RegularMaintainSchedule;
import com.jeesite.modules.test.dao.RegularMaintainScheduleDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 定修安排Service
 * @author dyl
 * @version 2018-08-21
 */
@Service
@Transactional(readOnly=true)
public class RegularMaintainScheduleService extends CrudService<RegularMaintainScheduleDao, RegularMaintainSchedule> {
	
	/**
	 * 获取单条数据
	 * @param regularMaintainSchedule
	 * @return
	 */
	@Override
	public RegularMaintainSchedule get(RegularMaintainSchedule regularMaintainSchedule) {
		return super.get(regularMaintainSchedule);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param regularMaintainSchedule
	 * @return
	 */
	@Override
	public Page<RegularMaintainSchedule> findPage(Page<RegularMaintainSchedule> page, RegularMaintainSchedule regularMaintainSchedule) {
		return super.findPage(page, regularMaintainSchedule);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param regularMaintainSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(RegularMaintainSchedule regularMaintainSchedule) {
		super.save(regularMaintainSchedule);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(regularMaintainSchedule.getId(), "regularMaintainSchedule_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(regularMaintainSchedule.getId(), "regularMaintainSchedule_file");
	}
	
	/**
	 * 更新状态
	 * @param regularMaintainSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(RegularMaintainSchedule regularMaintainSchedule) {
		super.updateStatus(regularMaintainSchedule);
	}
	
	/**
	 * 删除数据
	 * @param regularMaintainSchedule
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(RegularMaintainSchedule regularMaintainSchedule) {
		super.delete(regularMaintainSchedule);
	}
	
}