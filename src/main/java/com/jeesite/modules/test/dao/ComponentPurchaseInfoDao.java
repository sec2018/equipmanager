/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.ComponentPurchaseInfo;

/**
 * 备品备件入库DAO接口
 * @author jyf
 * @version 2018-08-29
 */
@MyBatisDao
public interface ComponentPurchaseInfoDao extends CrudDao<ComponentPurchaseInfo> {
	
}