/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.service;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.file.utils.FileUploadUtils;
import com.jeesite.modules.test.dao.ComponentInfoDao;
import com.jeesite.modules.test.dao.ComponentPurchaseInfoChildDao;
import com.jeesite.modules.test.dao.ComponentPurchaseInfoDao;
import com.jeesite.modules.test.entity.ComponentInfo;
import com.jeesite.modules.test.entity.ComponentPurchaseInfo;
import com.jeesite.modules.test.entity.ComponentPurchaseInfoChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 备品备件入库Service
 * @author jyf
 * @version 2018-08-29
 */
@Service
@Transactional(readOnly=true)
public class ComponentPurchaseInfoService extends CrudService<ComponentPurchaseInfoDao, ComponentPurchaseInfo> {
	
	@Autowired
	private ComponentPurchaseInfoChildDao componentPurchaseInfoChildDao;

	@Autowired
	private ComponentInfoDao componentInfoDao;
	
	/**
	 * 获取单条数据
	 * @param componentPurchaseInfo
	 * @return
	 */
	@Override
	public ComponentPurchaseInfo get(ComponentPurchaseInfo componentPurchaseInfo) {
		ComponentPurchaseInfo entity = super.get(componentPurchaseInfo);
		if (entity != null){
			ComponentPurchaseInfoChild componentPurchaseInfoChild = new ComponentPurchaseInfoChild(entity);
			componentPurchaseInfoChild.setStatus(ComponentPurchaseInfoChild.STATUS_NORMAL);
			entity.setComponentPurchaseInfoChildList(componentPurchaseInfoChildDao.findList(componentPurchaseInfoChild));
		}
		return entity;
	}
	
	/**
	 * 查询分页数据
	 * @param page 分页对象
	 * @param componentPurchaseInfo
	 * @return
	 */
	@Override
	public Page<ComponentPurchaseInfo> findPage(Page<ComponentPurchaseInfo> page, ComponentPurchaseInfo componentPurchaseInfo) {
		return super.findPage(page, componentPurchaseInfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param componentPurchaseInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(ComponentPurchaseInfo componentPurchaseInfo) {
		super.save(componentPurchaseInfo);
		// 保存上传图片
		FileUploadUtils.saveFileUpload(componentPurchaseInfo.getId(), "componentPurchaseInfo_image");
		// 保存上传附件
		FileUploadUtils.saveFileUpload(componentPurchaseInfo.getId(), "componentPurchaseInfo_file");
		// 保存 ComponentPurchaseInfo子表
		for (ComponentPurchaseInfoChild componentPurchaseInfoChild : componentPurchaseInfo.getComponentPurchaseInfoChildList()){
			if (!ComponentPurchaseInfoChild.STATUS_DELETE.equals(componentPurchaseInfoChild.getStatus())){
				componentPurchaseInfoChild.setPurchaseInfoCode(componentPurchaseInfo);

				if (componentPurchaseInfoChild.getIsNewRecord()){
					componentPurchaseInfoChild.preInsert();
					componentPurchaseInfoChildDao.insert(componentPurchaseInfoChild);
					//插入时，更新备品备件表的数量
					ComponentInfo componentInfo = new ComponentInfo();
					componentInfo.setComponentCode(componentPurchaseInfoChild.getComponentCode());
				    ComponentInfo	componentInfo2 = componentInfoDao.getByEntity(componentInfo);
					Long count = componentInfo2.getComponentNumber()+componentPurchaseInfoChild.getPurchaseNumber();
				    componentInfo2.setComponentNumber(count);
					componentInfoDao.update(componentInfo2);


				}else{
					ComponentPurchaseInfoChild componentPurchaseInfoChild2 =new ComponentPurchaseInfoChild();
					componentPurchaseInfoChild2.setId(componentPurchaseInfoChild.getId());
					ComponentPurchaseInfoChild componentPurchaseInfoChild3 = componentPurchaseInfoChildDao.getByEntity(componentPurchaseInfoChild2);
					Long precount= componentPurchaseInfoChild3.getPurchaseNumber();
					componentPurchaseInfoChild.preUpdate();
					componentPurchaseInfoChildDao.update(componentPurchaseInfoChild);
					//更新时，更新备品备件表的数量
					ComponentInfo componentInfo = new ComponentInfo();
					componentInfo.setComponentCode(componentPurchaseInfoChild.getComponentCode());
					ComponentInfo	componentInfo2 = componentInfoDao.getByEntity(componentInfo);
					Long count = componentInfo2.getComponentNumber()-precount+componentPurchaseInfoChild.getPurchaseNumber();
					componentInfo2.setComponentNumber(count);
					componentInfoDao.update(componentInfo2);
				}
			}else{

				componentPurchaseInfoChildDao.delete(componentPurchaseInfoChild);
				//删除时，更新备品备件表的数量
				ComponentInfo componentInfo = new ComponentInfo();
				componentInfo.setComponentCode(componentPurchaseInfoChild.getComponentCode());
				ComponentInfo	componentInfo2 = componentInfoDao.getByEntity(componentInfo);
				Long count = componentInfo2.getComponentNumber()-componentPurchaseInfoChild.getPurchaseNumber();
				componentInfo2.setComponentNumber(count);
				componentInfoDao.update(componentInfo2);
			}
		}
	}
	
	/**
	 * 更新状态
	 * @param componentPurchaseInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(ComponentPurchaseInfo componentPurchaseInfo) {
		super.updateStatus(componentPurchaseInfo);
	}
	
	/**
	 * 删除数据
	 * @param componentPurchaseInfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(ComponentPurchaseInfo componentPurchaseInfo) {
		super.delete(componentPurchaseInfo);
		ComponentPurchaseInfoChild componentPurchaseInfoChild = new ComponentPurchaseInfoChild();
		componentPurchaseInfoChild.setPurchaseInfoCode(componentPurchaseInfo);
		componentPurchaseInfoChildDao.delete(componentPurchaseInfoChild);
	}
	
}