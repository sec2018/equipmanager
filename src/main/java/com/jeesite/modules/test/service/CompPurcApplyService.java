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
import com.jeesite.modules.test.entity.CompPurcApply;
import com.jeesite.modules.test.dao.CompPurcApplyDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.test.entity.CompPurcApplyChild;
import com.jeesite.modules.test.dao.CompPurcApplyChildDao;

/**
 * comp_purc_applyService
 * @author jyf
 * @version 2018-08-02
 */
@Service
@Transactional(readOnly=true)
public class CompPurcApplyService extends CrudService<CompPurcApplyDao, CompPurcApply> {
	
	@Autowired
	private CompPurcApplyChildDao compPurcApplyChildDao;
	
	/**
	 * 获取单条数据
	 * @param compPurcApply
	 * @return
	 */
	@Override
	public CompPurcApply get(CompPurcApply compPurcApply) {
		CompPurcApply entity = super.get(compPurcApply);
		if (entity != null){
			CompPurcApplyChild compPurcApplyChild = new CompPurcApplyChild(entity);
			compPurcApplyChild.setStatus(CompPurcApplyChild.STATUS_NORMAL);
			entity.setCompPurcApplyChildList(compPurcApplyChildDao.findList(compPurcApplyChild));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param compPurcApply
	 * @return
	 */
	@Override
	public Page<CompPurcApply> findPage(Page<CompPurcApply> page, CompPurcApply compPurcApply) {
		return super.findPage(page, compPurcApply);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param compPurcApply
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(CompPurcApply compPurcApply) {
		super.save(compPurcApply);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(compPurcApply.getId(), "compPurcApply_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(compPurcApply.getId(), "compPurcApply_file");
		// 保存 CompPurcApply子表
		for (CompPurcApplyChild compPurcApplyChild : compPurcApply.getCompPurcApplyChildList()){
			if (!CompPurcApplyChild.STATUS_DELETE.equals(compPurcApplyChild.getStatus())){
				compPurcApplyChild.setPurchaseApplyCode(compPurcApply);
				if (compPurcApplyChild.getIsNewRecord()){
					compPurcApplyChild.preInsert();
					compPurcApplyChildDao.insert(compPurcApplyChild);
				}else{
					compPurcApplyChild.preUpdate();
					compPurcApplyChildDao.update(compPurcApplyChild);
				}
			}else{
				compPurcApplyChildDao.delete(compPurcApplyChild);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param compPurcApply
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(CompPurcApply compPurcApply) {
		super.updateStatus(compPurcApply);
	}
	
	/**
	 * 删除数据
	 * @param compPurcApply
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(CompPurcApply compPurcApply) {
		super.delete(compPurcApply);
		CompPurcApplyChild compPurcApplyChild = new CompPurcApplyChild();
		compPurcApplyChild.setPurchaseApplyCode(compPurcApply);
		compPurcApplyChildDao.delete(compPurcApplyChild);
	}
	
}