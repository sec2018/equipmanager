/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.test.entity.PreventMaintainPlan;
import com.jeesite.modules.test.dao.PreventMaintainPlanDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 预防性维护计划Service
 * @author dyl
 * @version 2018-08-20
 */
@Service
@Transactional(readOnly=true)
public class PreventMaintainPlanService extends TreeService<PreventMaintainPlanDao, PreventMaintainPlan> {
	
	/**
	 * 获取单条数据
	 * @param preventMaintainPlan
	 * @return
	 */
	@Override
	public PreventMaintainPlan get(PreventMaintainPlan preventMaintainPlan) {
		return super.get(preventMaintainPlan);
	}
	
	/**
	 * 查询列表数据
	 * @param preventMaintainPlan
	 * @return
	 */
	@Override
	public List<PreventMaintainPlan> findList(PreventMaintainPlan preventMaintainPlan) {
		return super.findList(preventMaintainPlan);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param preventMaintainPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(PreventMaintainPlan preventMaintainPlan) {
		super.save(preventMaintainPlan);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(preventMaintainPlan.getId(), "preventMaintainPlan_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(preventMaintainPlan.getId(), "preventMaintainPlan_file");
	}
	
	/**
	 * 更新状态
	 * @param preventMaintainPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(PreventMaintainPlan preventMaintainPlan) {
		super.updateStatus(preventMaintainPlan);
	}
	
	/**
	 * 删除数据
	 * @param preventMaintainPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(PreventMaintainPlan preventMaintainPlan) {
		super.delete(preventMaintainPlan);
	}
	
}