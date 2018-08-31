package com.jeesite.modules.test.service.impl;

import com.jeesite.modules.test.dao.selfdao.CheckPlanSelfDao;
import com.jeesite.modules.test.dao.selfdao.InspectPlanSelfDao;
import com.jeesite.modules.test.service.selfService.CheckPlanSelfService;
import com.jeesite.modules.test.service.selfService.InspectPlanSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class InspectPlanSelfServiceImpl implements InspectPlanSelfService {
    @Autowired
    private InspectPlanSelfDao inspectPlanSelfDao;
    @Override
    public Set<String> findAllInspectPlanCode() {
        return inspectPlanSelfDao. findAllInspectPlanCode();
    }
}
