package com.jeesite.modules.test.service.impl;

import com.jeesite.modules.test.dao.selfdao.CheckPlanSelfDao;
import com.jeesite.modules.test.service.selfService.CheckPlanSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class CheckPlanSelfServiceImpl implements CheckPlanSelfService {
    @Autowired
    private  CheckPlanSelfDao checkPlanSelfDao;
    @Override
    public Set<String> findAllCheckPlanCode() {
        return checkPlanSelfDao.findAllCheckPlanCode();
    }
}
