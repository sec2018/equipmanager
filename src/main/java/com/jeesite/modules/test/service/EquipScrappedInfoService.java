/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.EquipScrappedInfo;
import com.jeesite.modules.test.dao.EquipScrappedInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * equip_scrapped_infoService
 * @author jyf
 * @version 2018-07-27
 */
@Service
@Transactional(readOnly=true)
public class EquipScrappedInfoService extends CrudService<EquipScrappedInfoDao, EquipScrappedInfo> {
	
	/**
	 * 获取单条数据
	 * @param equipScrappedInfo
	 * @return
	 */
	@Override
	public EquipScrappedInfo get(EquipScrappedInfo equipScrappedInfo) {
		return super.get(equipScrappedInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param equipScrappedInfo
	 * @return
	 */
	@Override
	public Page<EquipScrappedInfo> findPage(Page<EquipScrappedInfo> page, EquipScrappedInfo equipScrappedInfo) {

		return super.findPage(page, equipScrappedInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param equipScrappedInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EquipScrappedInfo equipScrappedInfo) {
		super.save(equipScrappedInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(equipScrappedInfo.getId(), "equipScrappedInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(equipScrappedInfo.getId(), "equipScrappedInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param equipScrappedInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EquipScrappedInfo equipScrappedInfo) {
		super.updateStatus(equipScrappedInfo);
	}
	
	/**
	 * 删除数据
	 * @param equipScrappedInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EquipScrappedInfo equipScrappedInfo) {
		super.delete(equipScrappedInfo);
	}
	
}