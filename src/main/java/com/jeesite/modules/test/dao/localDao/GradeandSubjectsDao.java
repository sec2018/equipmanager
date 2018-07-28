package com.jeesite.modules.test.dao.localDao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.entity.Page;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.test.entity.localentity.GradeandSubjects;


@MyBatisDao
public interface GradeandSubjectsDao extends CrudDao<GradeandSubjects> {

    public Page<GradeandSubjects> findAllSubjectsBygradeid(Page<GradeandSubjects> page, GradeandSubjects gradeandSubjects);
}
