/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import com.jeesite.modules.test.dao.EquipPlanDao;
import com.jeesite.modules.test.entity.EquipPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.test.entity.RegularMaintainPlan;
import com.jeesite.modules.test.dao.RegularMaintainPlanDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 定修计划维护Service
 * @author dyl
 * @version 2018-08-21
 */
@Service
@Transactional(readOnly=true)
public class RegularMaintainPlanService extends TreeService<RegularMaintainPlanDao, RegularMaintainPlan> {
	@Autowired
	private EquipPlanDao equipPlanDao;
	/**
	 * 获取单条数据
	 * @param regularMaintainPlan
	 * @return
	 */
	@Override
	public RegularMaintainPlan get(RegularMaintainPlan regularMaintainPlan) {
		return super.get(regularMaintainPlan);
	}
	
	/**
	 * 查询列表数据
	 * @param regularMaintainPlan
	 * @return
	 */
	@Override
	public List<RegularMaintainPlan> findList(RegularMaintainPlan regularMaintainPlan) {
		return super.findList(regularMaintainPlan);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param regularMaintainPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(RegularMaintainPlan regularMaintainPlan) {
		super.save(regularMaintainPlan);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(regularMaintainPlan.getId(), "regularMaintainPlan_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(regularMaintainPlan.getId(), "regularMaintainPlan_file");
	}
	
	/**
	 * 更新状态
	 * @param regularMaintainPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(RegularMaintainPlan regularMaintainPlan) {
		super.updateStatus(regularMaintainPlan);
	}
	
	/**
	 * 删除数据
	 * @param regularMaintainPlan
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(RegularMaintainPlan regularMaintainPlan) {
		super.delete(regularMaintainPlan);
	}
	//test code by dang
//	public List<EquipPlan> findEquipPlanList(RegularMaintainPlan regularMaintainPlan) {
//		EquipPlan equipPlan = new EquipPlan();
//		equipPlan.setPlanCode(regularMaintainPlan.getRegularPlanCode());
//		return equipPlanDao.findList(equipPlan);
//	}
	
}