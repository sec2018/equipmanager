/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.PreventMaintianSchedule;

/**
 * 预防性维护计划安排DAO接口
 * @author dyl
 * @version 2018-08-20
 */
@MyBatisDao
public interface PreventMaintianScheduleDao extends CrudDao<PreventMaintianSchedule> {
	
}