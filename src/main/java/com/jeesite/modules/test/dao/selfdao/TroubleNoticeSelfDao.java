package com.jeesite.modules.test.dao.selfdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface TroubleNoticeSelfDao {
    @Select("select DISTINCT notice_code from trouble_notice")
    Set<String> findAllTroubleNoticeCode();
}
