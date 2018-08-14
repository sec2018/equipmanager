/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.CheckPlans;

/**
 * 点检计划DAO接口
 * @author jyf
 * @version 2018-08-14
 */
@MyBatisDao
public interface CheckPlansDao extends TreeDao<CheckPlans> {
	
}