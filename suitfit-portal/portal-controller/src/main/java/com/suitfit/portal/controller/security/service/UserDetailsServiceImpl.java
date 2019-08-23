package com.suitfit.portal.controller.security.service;

import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.model.pojo.auth.AuthUser;
import com.suitfit.portal.model.pojo.dto.AuthModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @author Exrickx
 */
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SecurityFactory securityFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthModel user = securityFactory.findByUsername(username);
        if (user != null) {
            return new AuthUser(user);
        }
        throw new UsernameNotFoundException("");
    }
}
