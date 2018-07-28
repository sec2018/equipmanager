package com.jeesite.modules.test.service;

import com.jeesite.common.service.CrudService;
import com.jeesite.modules.test.dao.TestUserDao;
import com.jeesite.modules.test.entity.TestData;
import com.jeesite.modules.test.entity.TestDataChild;
import com.jeesite.modules.test.entity.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TestUserService extends CrudService<TestUserDao,TestUser> {

    @Autowired
    private   TestUserDao testUserDao;

    @Override
    public TestUser get(TestUser testUser) {
        TestUser entity = super.get(testUser);
        return entity;
    }

    @Override
    public void insert(TestUser testUser){
        super.insert(testUser);

    }



}

