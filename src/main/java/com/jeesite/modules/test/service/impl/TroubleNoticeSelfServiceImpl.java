package com.jeesite.modules.test.service.impl;

import com.jeesite.modules.test.dao.selfdao.TroubleNoticeSelfDao;
import com.jeesite.modules.test.service.selfService.TroubleNoticeSelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TroubleNoticeSelfServiceImpl implements TroubleNoticeSelfService {
    @Autowired
    private TroubleNoticeSelfDao troubleNoticeSelfDao;
    @Override
    public Set<String> findAllTroubleNoticeCode() {
        return troubleNoticeSelfDao.findAllTroubleNoticeCode();
    }
}
