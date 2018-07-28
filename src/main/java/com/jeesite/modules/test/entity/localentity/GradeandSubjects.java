package com.jeesite.modules.test.entity.localentity;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.modules.test.entity.GTest;
import com.jeesite.modules.test.entity.GradeSubjRelate;
import com.jeesite.modules.test.entity.Subjects;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class GradeandSubjects extends GradeSubjRelate{

    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    private  Subjects subjects;
   @Override
    public String toString() {
        return "GradeandSubjects{" +
                "subjects=" + subjects +
                '}';
    }



}
