/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.EquipInfo;
import com.jeesite.modules.test.dao.EquipInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * equip_infoService
 * @author jyf
 * @version 2018-07-27
 */
@Service
@Transactional(readOnly=true)
public class EquipInfoService extends CrudService<EquipInfoDao, EquipInfo> {
	
	/**
	 * 获取单条数据
	 * @param equipInfo
	 * @return
	 */
	@Override
	public EquipInfo get(EquipInfo equipInfo) {
		return super.get(equipInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param equipInfo
	 * @return
	 */
	@Override
	public Page<EquipInfo> findPage(Page<EquipInfo> page, EquipInfo equipInfo) {

		//角色级别数据权限设置
		//equipInfo.getSqlMap().getDataScope().addFilter("dsf", "Role", "a.role_code","a.create_by");
		//公司 部门级别数据权限设置
		//equipInfo.getSqlMap().getDataScope().addFilter("dsf","Office", "u11.office_code","a.create_by");
		return super.findPage(page, equipInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param equipInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(EquipInfo equipInfo) {
		super.save(equipInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(equipInfo.getId(), "equipInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(equipInfo.getId(), "equipInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param equipInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(EquipInfo equipInfo) {
		super.updateStatus(equipInfo);
	}
	
	/**
	 * 删除数据
	 * @param equipInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(EquipInfo equipInfo) {
		super.delete(equipInfo);
	}
	
}