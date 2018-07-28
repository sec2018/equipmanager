package com.jeesite.modules.test.service.localService;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.dao.GradeSubjRelateDao;
import com.jeesite.modules.test.dao.localDao.GradeandSubjectsDao;
import com.jeesite.modules.test.entity.GradeSubjRelate;
import com.jeesite.modules.test.entity.STest;
import com.jeesite.modules.test.entity.localentity.GradeandSubjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Transactional(readOnly=true)
public class GradeandSubjectsService extends CrudService<GradeandSubjectsDao, GradeandSubjects>   {

    @Autowired
    private  GradeandSubjectsDao gradeandSubjectsDao;


   public Page<GradeandSubjects> findSubsByGradeId(Page<GradeandSubjects> page, GradeandSubjects gradeandSubjects){

       Page<GradeandSubjects> subjectsList = gradeandSubjectsDao.findAllSubjectsBygradeid(page,gradeandSubjects);
       return subjectsList;
    }


}
