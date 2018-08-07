/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.ComponentInfo;

/**
 * 备品备件DAO接口
 * @author jyf
 * @version 2018-08-07
 */
@MyBatisDao
public interface ComponentInfoDao extends CrudDao<ComponentInfo> {
	
}