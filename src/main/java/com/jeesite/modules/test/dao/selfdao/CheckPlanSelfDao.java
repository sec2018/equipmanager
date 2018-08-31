package com.jeesite.modules.test.dao.selfdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface CheckPlanSelfDao {
    @Select("select DISTINCT  chack_plan_code from check_plans")
    Set<String> findAllCheckPlanCode();
}
