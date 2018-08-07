/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.EquipMaintainApply;

/**
 * 设备报修申请DAO接口
 * @author dang
 * @version 2018-08-02
 */
@MyBatisDao
public interface EquipMaintainApplyDao extends CrudDao<EquipMaintainApply> {
	
}