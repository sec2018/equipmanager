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
import com.jeesite.modules.test.entity.MaintainRecord;
import com.jeesite.modules.test.dao.MaintainRecordDao;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.test.entity.MaintainRecordChild;
import com.jeesite.modules.test.dao.MaintainRecordChildDao;

/**
 * 维修工单Service
 * @author dyl
 * @version 2018-08-23
 */
@Service
@Transactional(readOnly=true)
public class MaintainRecordService extends CrudService<MaintainRecordDao, MaintainRecord> {
	
	@Autowired
	private MaintainRecordChildDao maintainRecordChildDao;
	
	/**
	 * 获取单条数据
	 * @param maintainRecord
	 * @return
	 */
	@Override
	public MaintainRecord get(MaintainRecord maintainRecord) {
		MaintainRecord entity = super.get(maintainRecord);
		if (entity != null){
			MaintainRecordChild maintainRecordChild = new MaintainRecordChild(entity);
			maintainRecordChild.setStatus(MaintainRecordChild.STATUS_NORMAL);
			entity.setMaintainRecordChildList(maintainRecordChildDao.findList(maintainRecordChild));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param maintainRecord
	 * @return
	 */
	@Override
	public Page<MaintainRecord> findPage(Page<MaintainRecord> page, MaintainRecord maintainRecord) {
		return super.findPage(page, maintainRecord);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param maintainRecord
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(MaintainRecord maintainRecord) {
		super.save(maintainRecord);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(maintainRecord.getId(), "maintainRecord_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(maintainRecord.getId(), "maintainRecord_file");
		// 保存 MaintainRecord子表
		for (MaintainRecordChild maintainRecordChild : maintainRecord.getMaintainRecordChildList()){
			if (!MaintainRecordChild.STATUS_DELETE.equals(maintainRecordChild.getStatus())){
				maintainRecordChild.setRecordCode(maintainRecord);
				if (maintainRecordChild.getIsNewRecord()){
					maintainRecordChild.preInsert();
					maintainRecordChildDao.insert(maintainRecordChild);
				}else{
					maintainRecordChild.preUpdate();
					maintainRecordChildDao.update(maintainRecordChild);
				}
			}else{
				maintainRecordChildDao.delete(maintainRecordChild);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param maintainRecord
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(MaintainRecord maintainRecord) {
		super.updateStatus(maintainRecord);
	}
	
	/**
	 * 删除数据
	 * @param maintainRecord
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(MaintainRecord maintainRecord) {
		super.delete(maintainRecord);
		MaintainRecordChild maintainRecordChild = new MaintainRecordChild();
		maintainRecordChild.setRecordCode(maintainRecord);
		maintainRecordChildDao.delete(maintainRecordChild);
	}
	
}