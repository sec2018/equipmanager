/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.MaintainRecord;

/**
 * 维修工单DAO接口
 * @author dyl
 * @version 2018-08-23
 */
@MyBatisDao
public interface MaintainRecordDao extends CrudDao<MaintainRecord> {
	
}