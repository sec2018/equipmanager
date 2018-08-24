/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.TreeService;
import com.jeesite.modules.test.entity.MaintainRecordTree;
import com.jeesite.modules.test.dao.MaintainRecordTreeDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 维修工单库Service
 * @author dyl
 * @version 2018-08-24
 */
@Service
@Transactional(readOnly=true)
public class MaintainRecordTreeService extends TreeService<MaintainRecordTreeDao, MaintainRecordTree> {
	
	/**
	 * 获取单条数据
	 * @param maintainRecordTree
	 * @return
	 */
	@Override
	public MaintainRecordTree get(MaintainRecordTree maintainRecordTree) {
		return super.get(maintainRecordTree);
	}
	
	/**
	 * 查询列表数据
	 * @param maintainRecordTree
	 * @return
	 */
	@Override
	public List<MaintainRecordTree> findList(MaintainRecordTree maintainRecordTree) {
		return super.findList(maintainRecordTree);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param maintainRecordTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(MaintainRecordTree maintainRecordTree) {
		super.save(maintainRecordTree);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(maintainRecordTree.getId(), "maintainRecordTree_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(maintainRecordTree.getId(), "maintainRecordTree_file");
	}
	
	/**
	 * 更新状态
	 * @param maintainRecordTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(MaintainRecordTree maintainRecordTree) {
		super.updateStatus(maintainRecordTree);
	}
	
	/**
	 * 删除数据
	 * @param maintainRecordTree
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(MaintainRecordTree maintainRecordTree) {
		super.delete(maintainRecordTree);
	}
	
}