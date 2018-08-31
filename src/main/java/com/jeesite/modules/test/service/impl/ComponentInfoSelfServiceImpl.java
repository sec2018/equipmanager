package com.jeesite.modules.test.service.impl;

import com.jeesite.modules.test.dao.selfdao.ComponentInfoSelfDao;
import com.jeesite.modules.test.service.selfService.ComponentInfoSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class ComponentInfoSelfServiceImpl implements ComponentInfoSelfService {
    @Autowired
    private ComponentInfoSelfDao componentInfoSelfDao;
    @Override
    public Set<String> findAllComponentCode() {
        return componentInfoSelfDao.findAllComponentCode();
    }
}
