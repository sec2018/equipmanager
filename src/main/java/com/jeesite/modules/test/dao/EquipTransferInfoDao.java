/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.EquipTransferInfo;

/**
 * 设备调拨信息DAO接口
 * @author dang
 * @version 2018-08-06
 */
@MyBatisDao
public interface EquipTransferInfoDao extends CrudDao<EquipTransferInfo> {
	
}