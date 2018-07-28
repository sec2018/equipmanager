/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.test.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.GradeSubjRelate;

/**
 * grade_subj_relateDAO接口
 * @author jyf
 * @version 2018-07-23
 */
@MyBatisDao
public interface GradeSubjRelateDao extends CrudDao<GradeSubjRelate> {
	
}