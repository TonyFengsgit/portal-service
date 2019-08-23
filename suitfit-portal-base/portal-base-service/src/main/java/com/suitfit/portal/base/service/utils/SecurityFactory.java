package com.suitfit.portal.base.service.utils;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.portal.model.pojo.auth.AuthUser;
import com.suitfit.portal.model.pojo.dto.AuthModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityFactory {

    public AuthModel findByUsername(String username) {
        return null;
    }

    public AuthUser getCurrentUser() {
        AuthUser userDetails = null;
        try {
            userDetails = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            // TODO 登陆状态过期
            throw new BaseException();
        }
        return userDetails;
    }

    public String getUsername() {
        AuthUser user = getCurrentUser();
        return user.getUsername();
    }

    public Long getUserId() {
        AuthUser user = getCurrentUser();
        return user.getId();
    }
}
