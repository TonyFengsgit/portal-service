package com.suitfit.portal.controller.security.handler;

import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.framework.utils.servlet.ResponseUtils;
import com.suitfit.portal.model.pojo.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 登录成功处理类
 *
 * @author Exrickx
 */
@Slf4j
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private RedisService redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = JwtTokenUtils.generateToken(userDetails.getUsername());
        redisTemplate.set(token, "true", 30, TimeUnit.DAYS);
        remove(userDetails.getUsername());
        ResponseUtils.out(response, token);
    }

    /**
     * 删除登陆失败记录
     *
     * @param username
     */
    private void remove(String username) {
        String key = "loginTimeLimit:" + username;
        String flagKey = "loginFailFlag:" + username;
        redisTemplate.del(key, flagKey);
    }
}