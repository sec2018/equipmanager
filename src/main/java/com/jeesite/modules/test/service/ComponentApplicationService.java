/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.ComponentApplication;
import com.jeesite.modules.test.dao.ComponentApplicationDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.test.entity.ComponentApplicationChild;
import com.jeesite.modules.test.dao.ComponentApplicationChildDao;

/**
 * component_applicationService
 * @author jyf
 * @version 2018-08-08
 */
@Service
@Transactional(readOnly=true)
public class ComponentApplicationService extends CrudService<ComponentApplicationDao, ComponentApplication> {
	
	@Autowired
	private ComponentApplicationChildDao componentApplicationChildDao;
	
	/**
	 * 获取单条数据
	 * @param componentApplication
	 * @return
	 */
	@Override
	public ComponentApplication get(ComponentApplication componentApplication) {
		ComponentApplication entity = super.get(componentApplication);
		if (entity != null){
			ComponentApplicationChild componentApplicationChild = new ComponentApplicationChild(entity);
			componentApplicationChild.setStatus(ComponentApplicationChild.STATUS_NORMAL);
			entity.setComponentApplicationChildList(componentApplicationChildDao.findList(componentApplicationChild));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param componentApplication
	 * @return
	 */
	@Override
	public Page<ComponentApplication> findPage(Page<ComponentApplication> page, ComponentApplication componentApplication) {
		return super.findPage(page, componentApplication);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param componentApplication
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ComponentApplication componentApplication) {
		super.save(componentApplication);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(componentApplication.getId(), "componentApplication_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(componentApplication.getId(), "componentApplication_file");
		// 保存 ComponentApplication子表
		for (ComponentApplicationChild componentApplicationChild : componentApplication.getComponentApplicationChildList()){
			if (!ComponentApplicationChild.STATUS_DELETE.equals(componentApplicationChild.getStatus())){
				componentApplicationChild.setApplicationCode(componentApplication);
				if (componentApplicationChild.getIsNewRecord()){
					componentApplicationChild.preInsert();
					componentApplicationChildDao.insert(componentApplicationChild);
				}else{
					componentApplicationChild.preUpdate();
					componentApplicationChildDao.update(componentApplicationChild);
				}
			}else{
				componentApplicationChildDao.delete(componentApplicationChild);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param componentApplication
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ComponentApplication componentApplication) {
		super.updateStatus(componentApplication);
	}
	
	/**
	 * 删除数据
	 * @param componentApplication
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ComponentApplication componentApplication) {
		super.delete(componentApplication);
		ComponentApplicationChild componentApplicationChild = new ComponentApplicationChild();
		componentApplicationChild.setApplicationCode(componentApplication);
		componentApplicationChildDao.delete(componentApplicationChild);
	}
	
}