package com.jeesite.modules.test.dao.selfdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface InspectPlanSelfDao {
    @Select("select DISTINCT  inspect_plan_code from inspect_plan")
    Set<String> findAllInspectPlanCode();
}
