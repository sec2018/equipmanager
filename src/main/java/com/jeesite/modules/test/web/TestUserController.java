package com.jeesite.modules.test.web;


import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.modules.test.entity.TestData;
import com.jeesite.modules.test.entity.TestUser;
import com.jeesite.modules.test.service.TestUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.jeesite.common.service.BaseService.text;
import static com.jeesite.common.web.http.ServletUtils.renderResult;

@Controller
@RequestMapping(value = "${adminPath}/test/testUser")
public class TestUserController {

    @Autowired
    private TestUserService testUserService;




    @RequiresPermissions("test:testUser:view")
    @RequestMapping(value = "form")
    public String form(TestUser testUser, Model model) {
        model.addAttribute("testUser", testUser);
        return "modules/test/testUserForm";
    }

    @RequiresPermissions("test:testUser:edit")
    @PostMapping(value = "save")
    @ResponseBody
    public String saveUser(@Validated TestUser testUser){
        testUserService.insert(testUser);
        return renderResult(Global.TRUE, text("保存数据成功！"));

    }

    /**
     * 查询列表
     */
    @RequiresPermissions("test:testUser:view")
    @RequestMapping(value = {"list", ""})
    public String list(TestUser testUser, Model model) {
        model.addAttribute("testUser", testUser);
        return "modules/test/testUserList";
    }
    /**
     * 查询列表数据
     */
    @RequiresPermissions("test:testUser:view")
    @RequestMapping(value = "listData")
    @ResponseBody
    public Page<TestUser> listData(TestUser testUser, HttpServletRequest request, HttpServletResponse response) {
        Page<TestUser> page = testUserService.findPage(new Page<TestUser>(request, response), testUser);
        return page;
    }

    /**
     * 删除数据
     */
    @RequiresPermissions("test:testUser:edit")
    @RequestMapping(value = "delete")
    @ResponseBody
    public String delete(TestUser testUser) {
        testUserService.delete(testUser);
        return renderResult(Global.TRUE, text("删除数据成功！"));
    }












}
