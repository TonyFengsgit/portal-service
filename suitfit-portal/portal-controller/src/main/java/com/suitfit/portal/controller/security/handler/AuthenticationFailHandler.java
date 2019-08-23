package com.suitfit.portal.controller.security.handler;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.framework.utils.servlet.ResponseUtils;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.exception.LoginFailLimitException;
import com.suitfit.portal.model.pojo.properties.TokenProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 认证失败
 *
 * @author Exrickx
 */
@Slf4j
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private TokenProperties tokenProperties;

    @Autowired
    private RedisService redisTemplate;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        try {
            if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
                String username = request.getParameter("username");
                recordLoginTime(username);
                String key = "loginTimeLimit:" + username;
                Object value = redisTemplate.get(key);
                if (StringUtils.isNullOrEmpty(value)) {
                    value = "0";
                }
                //获取已登录错误次数
                int loginFailTime = Integer.parseInt(value.toString());
                int restLoginTime = tokenProperties.getLoginTimeLimit() - loginFailTime;
                log.info("用户" + username + "登录失败，还有" + restLoginTime + "次机会");
                if (restLoginTime <= 3 && restLoginTime > 0) {
                    throw new BaseException("用户名或密码错误，还有" + restLoginTime + "次尝试机会");
                } else if (restLoginTime <= 0) {
                    throw new BaseException("登录错误次数超过限制，请" + tokenProperties.getLoginAfterTime() + "分钟后再试");
                } else {
                    throw new BaseException(ResponseCode.USER_PASSWORD_ERROR);
                }
            } else if (e instanceof DisabledException) {
                throw new BaseException(ResponseCode.ACCOUNT_DISABLED);
            } else if (e instanceof LoginFailLimitException) {
                throw new BaseException(((LoginFailLimitException) e).getMsg());
            } else {
                throw new BaseException(ResponseCode.SYSTEM_ERROR);
            }
        } catch (BaseException ex) {
            log.error("login failure error", ex);
            ResponseUtils.out(response, ex);
        }
    }

    /**
     * 判断用户登陆错误次数
     */
    private boolean recordLoginTime(String username) {
        String key = "loginTimeLimit:" + username;
        String flagKey = "loginFailFlag:" + username;
        Object value = redisTemplate.get(key);
        if (StringUtils.isNullOrEmpty(value)) {
            value = "0";
        }
        //获取已登录错误次数
        int loginFailTime = Integer.parseInt(value.toString()) + 1;
        redisTemplate.set(key, String.valueOf(loginFailTime), tokenProperties.getLoginAfterTime(), TimeUnit.MINUTES);
        if (loginFailTime >= tokenProperties.getLoginTimeLimit()) {
            redisTemplate.set(flagKey, "fail", tokenProperties.getLoginAfterTime(), TimeUnit.MINUTES);
            return false;
        }
        return true;
    }
}
