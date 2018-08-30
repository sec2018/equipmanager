package com.jeesite.modules.test.service.impl;

import com.jeesite.modules.test.dao.selfdao.EquipInfoSelfDao;
//import com.jeesite.modules.test.entity.EquipInfo;
//import com.jeesite.modules.test.service.EquipInfoSelfService;
import com.jeesite.modules.test.service.selfService.EquipInfoSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EquipInfoSelfServiceImpl implements EquipInfoSelfService {

    @Autowired
    private EquipInfoSelfDao equipInfoSelfDao;

    @Override
    public Set<String> findEquipAllType() {

        return equipInfoSelfDao.findEquipAllType();
    }
}
