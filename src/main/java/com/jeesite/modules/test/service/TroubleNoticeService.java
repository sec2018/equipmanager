/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.entity.TroubleNotice;
import com.jeesite.modules.test.dao.TroubleNoticeDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 故障通知单Service
 * @author dyl
 * @version 2018-08-22
 */
@Service
@Transactional(readOnly=true)
public class TroubleNoticeService extends CrudService<TroubleNoticeDao, TroubleNotice> {
	
	/**
	 * 获取单条数据
	 * @param troubleNotice
	 * @return
	 */
	@Override
	public TroubleNotice get(TroubleNotice troubleNotice) {
		return super.get(troubleNotice);
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param troubleNotice
	 * @return
	 */
	@Override
	public Page<TroubleNotice> findPage(Page<TroubleNotice> page, TroubleNotice troubleNotice) {
		return super.findPage(page, troubleNotice);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param troubleNotice
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(TroubleNotice troubleNotice) {
		super.save(troubleNotice);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(troubleNotice.getId(), "troubleNotice_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(troubleNotice.getId(), "troubleNotice_file");
	}
	
	/**
	 * 更新状态
	 * @param troubleNotice
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(TroubleNotice troubleNotice) {
		super.updateStatus(troubleNotice);
	}
	
	/**
	 * 删除数据
	 * @param troubleNotice
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(TroubleNotice troubleNotice) {
		super.delete(troubleNotice);
	}
	
}