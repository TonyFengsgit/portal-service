package com.suitfit.portal.controller.security.filter;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.framework.utils.servlet.ResponseUtils;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.model.pojo.auth.AuthUser;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.consts.SecurityConstant;
import com.suitfit.portal.model.pojo.dto.AuthModel;
import com.suitfit.portal.model.pojo.properties.TokenProperties;
import com.suitfit.portal.model.pojo.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Exrickx
 */
@Slf4j
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private TokenProperties tokenProperties;

    private RedisService redisTemplate;

    private SecurityFactory securityFactory;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, TokenProperties tokenProperties, RedisService redisTemplate, SecurityFactory securityFactory) {
        super(authenticationManager);
        this.tokenProperties = tokenProperties;
        this.redisTemplate = redisTemplate;
        this.securityFactory = securityFactory;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(SecurityConstant.HEADER);
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(SecurityConstant.HEADER);
        }
        try {
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        } catch (BaseException e) {
            log.error("jwt token error", e);
            ResponseUtils.out(response, e);
        } catch (Exception e) {
            log.error("jwt token error", e);
            ResponseUtils.out(response, new BaseException(ResponseCode.SYSTEM_ERROR));
        }

    }

    /**
     * 从token找出数据库用户
     *
     * @param token
     * @return
     * @throws Exception
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String token) throws Exception {
        Object v = redisTemplate.get(token);
        if (StringUtils.isNullOrEmpty(v) || JwtTokenUtils.isTokenExpired(token)) {
            throw new BaseException(ResponseCode.TOKEN_EXPIRE);
        }
        String username = JwtTokenUtils.getUsernameFromToken(token);
        if (StringUtils.isNotBlank(username)) {
            AuthModel auth = this.securityFactory.findByUsername(username);
            if (auth == null) {
                throw new BaseException(ResponseCode.USER_DONT_EXISTS);
            }
            AuthUser authUser = new AuthUser(auth);
            return new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        } else {
            throw new BaseException(ResponseCode.SYSTEM_ERROR);
        }
    }
}

