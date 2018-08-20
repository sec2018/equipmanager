/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.TreeDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.PreventMaintainPlan;

/**
 * 预防性维护计划DAO接口
 * @author dyl
 * @version 2018-08-20
 */
@MyBatisDao
public interface PreventMaintainPlanDao extends TreeDao<PreventMaintainPlan> {
	
}