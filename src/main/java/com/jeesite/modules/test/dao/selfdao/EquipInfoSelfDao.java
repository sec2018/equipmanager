package com.jeesite.modules.test.dao.selfdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface EquipInfoSelfDao {

    @Select("select DISTINCT  equip_id as a_id from equip_info")
    Set<String> findEquipAllType();
}
