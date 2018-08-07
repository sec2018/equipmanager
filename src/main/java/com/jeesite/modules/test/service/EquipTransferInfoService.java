/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.test.dao.EquipTransferInfoDao;
import com.jeesite.modules.test.entity.EquipTransferInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 设备调拨信息Service
 * @author dang
 * @version 2018-08-06
 */
@Service
@Transactional(readOnly=true)
public class EquipTransferInfoService extends CrudService<EquipTransferInfoDao, EquipTransferInfo> {
	
	/**
	 * 获取单条数据
	 * @param equipTransferInfo
	 * @return
	 */
	@Override
	public EquipTransferInfo get(EquipTransferInfo equipTransferInfo) {
		return super.get(equipTransferInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param equipTransferInfo
	 * @return
	 */
	@Override
	public Page<EquipTransferInfo> findPage(Page<EquipTransferInfo> page, EquipTransferInfo equipTransferInfo) {
		return super.findPage(page, equipTransferInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param equipTransferInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EquipTransferInfo equipTransferInfo) {
		super.save(equipTransferInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(equipTransferInfo.getId(), "equipTransferInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(equipTransferInfo.getId(), "equipTransferInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param equipTransferInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EquipTransferInfo equipTransferInfo) {
		super.updateStatus(equipTransferInfo);
	}
	
	/**
	 * 删除数据
	 * @param equipTransferInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EquipTransferInfo equipTransferInfo) {
		super.delete(equipTransferInfo);
	}
	
}