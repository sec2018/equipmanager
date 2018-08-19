/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.test.entity.InspectPlan;
import com.jeesite.modules.test.dao.InspectPlanDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 巡检计划Service
 * @author dyl
 * @version 2018-08-17
 */
@Service
@Transactional(readOnly=true)
public class InspectPlanService extends TreeService<InspectPlanDao, InspectPlan> {
	
	/**
	 * 获取单条数据
	 * @param inspectPlan
	 * @return
	 */
	@Override
	public InspectPlan get(InspectPlan inspectPlan) {
		return super.get(inspectPlan);
	}
	
	/**
	 * 查询列表数据
	 * @param inspectPlan
	 * @return
	 */
	@Override
	public List<InspectPlan> findList(InspectPlan inspectPlan) {
		return super.findList(inspectPlan);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param inspectPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(InspectPlan inspectPlan) {
		super.save(inspectPlan);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(inspectPlan.getId(), "inspectPlan_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(inspectPlan.getId(), "inspectPlan_file");
	}
	
	/**
	 * 更新状态
	 * @param inspectPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(InspectPlan inspectPlan) {
		super.updateStatus(inspectPlan);
	}
	
	/**
	 * 删除数据
	 * @param inspectPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(InspectPlan inspectPlan) {
		super.delete(inspectPlan);
	}
	
}