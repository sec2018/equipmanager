/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.CompPurcApply;

/**
 * comp_purc_applyDAO接口
 * @author jyf
 * @version 2018-08-02
 */
@MyBatisDao
public interface CompPurcApplyDao extends CrudDao<CompPurcApply> {
	
}