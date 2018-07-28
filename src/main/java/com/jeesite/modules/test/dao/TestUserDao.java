/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.TestUser;

/**
 * test_userDAO接口
 * @author jyf
 * @version 2018-07-12
 */
@MyBatisDao
public interface TestUserDao extends CrudDao<TestUser> {
	
}