/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.STest;

/**
 * 学生表DAO接口
 * @author jyf
 * @version 2018-07-21
 */
@MyBatisDao
public interface STestDao extends CrudDao<STest> {
	
}