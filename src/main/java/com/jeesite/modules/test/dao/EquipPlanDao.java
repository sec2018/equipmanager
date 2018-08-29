/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.EquipPlan;

/**
 * 设备与计划关联信息DAO接口
 * @author dyl
 * @version 2018-08-29
 */
@MyBatisDao
public interface EquipPlanDao extends CrudDao<EquipPlan> {
	
}