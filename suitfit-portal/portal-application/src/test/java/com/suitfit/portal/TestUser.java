package com.suitfit.portal;

import com.suitfit.portal.base.service.RoleService;
import com.suitfit.portal.biz.UserBiz;
import com.suitfit.portal.model.pojo.vo.req.UserReq;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class TestUser extends BaseTest {

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private RoleService roleService;

    @Test
    public void testCreate() {
        UserReq userReq = new UserReq();
        userReq.setUserName("test");
        userReq.setPhone("12345678900");
        userReq.setEmail("admin@admin.com");
        userReq.setDepartmentId(0L);
        Set<Long> roles = new HashSet<>();
        roles.add(1L);
        userReq.setRoles(roles);
        userBiz.create(userReq);
    }
}
