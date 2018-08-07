/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.test.dao.EquipMaintainApplyDao;
import com.jeesite.modules.test.entity.EquipMaintainApply;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备报修申请Service
 * @author dang
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly=true)
public class EquipMaintainApplyService extends CrudService<EquipMaintainApplyDao, EquipMaintainApply> {
	
	/**
	 * 获取单条数据
	 * @param equipMaintainApply
	 * @return
	 */
	@Override
	public EquipMaintainApply get(EquipMaintainApply equipMaintainApply) {
		return super.get(equipMaintainApply);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param equipMaintainApply
	 * @return
	 */
	@Override
	public Page<EquipMaintainApply> findPage(Page<EquipMaintainApply> page, EquipMaintainApply equipMaintainApply) {
		return super.findPage(page, equipMaintainApply);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param equipMaintainApply
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EquipMaintainApply equipMaintainApply) {
		super.save(equipMaintainApply);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(equipMaintainApply.getId(), "equipMaintainApply_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(equipMaintainApply.getId(), "equipMaintainApply_file");
	}
	
	/**
	 * 更新状态
	 * @param equipMaintainApply
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EquipMaintainApply equipMaintainApply) {
		super.updateStatus(equipMaintainApply);
	}
	
	/**
	 * 删除数据
	 * @param equipMaintainApply
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EquipMaintainApply equipMaintainApply) {
		super.delete(equipMaintainApply);
	}
	
}