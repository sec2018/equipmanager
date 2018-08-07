/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.ComponentInfo;
import com.jeesite.modules.test.dao.ComponentInfoDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 备品备件Service
 * @author jyf
 * @version 2018-08-07
 */
@Service
@Transactional(readOnly=true)
public class ComponentInfoService extends CrudService<ComponentInfoDao, ComponentInfo> {
	
	/**
	 * 获取单条数据
	 * @param componentInfo
	 * @return
	 */
	@Override
	public ComponentInfo get(ComponentInfo componentInfo) {
		return super.get(componentInfo);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param componentInfo
	 * @return
	 */
	@Override
	public Page<ComponentInfo> findPage(Page<ComponentInfo> page, ComponentInfo componentInfo) {
		return super.findPage(page, componentInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param componentInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ComponentInfo componentInfo) {
		super.save(componentInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(componentInfo.getId(), "componentInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(componentInfo.getId(), "componentInfo_file");
	}
	
	/**
	 * 更新状态
	 * @param componentInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ComponentInfo componentInfo) {
		super.updateStatus(componentInfo);
	}
	
	/**
	 * 删除数据
	 * @param componentInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ComponentInfo componentInfo) {
		super.delete(componentInfo);
	}
	
}