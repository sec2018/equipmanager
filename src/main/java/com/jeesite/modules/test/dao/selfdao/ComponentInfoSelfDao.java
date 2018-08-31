package com.jeesite.modules.test.dao.selfdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface ComponentInfoSelfDao {
    @Select("select DISTINCT component_code from component_info")
    Set<String> findAllComponentCode();
}
