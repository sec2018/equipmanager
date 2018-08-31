package com.jeesite.modules.test.dao.selfdao;

import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

@Mapper
public interface EquipInfoSelfDao {
//    User user = UserUtils.getUser();
//    String managerCode = user.getUserCode();
    @Select("select DISTINCT  equip_id from equip_info")
    //@Select("select DISTINCT  equip_id from equip_info where equip_manager_code=managerCode")
    Set<String> findAllEquipId();
}
