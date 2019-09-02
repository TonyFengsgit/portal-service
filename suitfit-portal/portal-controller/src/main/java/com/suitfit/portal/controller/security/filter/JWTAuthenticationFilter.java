package com.suitfit.portal.controller.security.filter;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.framework.utils.servlet.ResponseUtils;
import com.suitfit.portal.base.service.utils.SecurityFactory;
import com.suitfit.portal.controller.security.utils.JwtTokenUtils;
import com.suitfit.portal.model.pojo.auth.AuthUser;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.consts.SecurityConstant;
import com.suitfit.portal.model.pojo.dto.AuthModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
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

    private RedisService redisTemplate;

    private SecurityFactory securityFactory;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,AuthenticationEntryPoint authenticationEntryPoint , RedisService redisTemplate, SecurityFactory securityFactory) {
        super(authenticationManager, authenticationEntryPoint);
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
            if (StringUtils.isNullOrEmpty(token)){
                throw new AuthenticationCredentialsNotFoundException(ResponseCode.TOKEN_EXPIRE.getMessage());
            }
            UsernamePasswordAuthenticationToken authentication = getAuthentication(token);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            log.error("jwt token error", e);
            this.getAuthenticationEntryPoint().commence(request, response, e);
        } catch (Exception e) {
            log.error("jwt token error", e);
            ResponseUtils.out(response, new BaseException(ResponseCode.SYSTEM_ERROR));
        }
        chain.doFilter(request, response);
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
            throw new CredentialsExpiredException(ResponseCode.TOKEN_EXPIRE.getMessage());
        }
        String username = JwtTokenUtils.getUsernameFromToken(token);
        if (StringUtils.isNotBlank(username)) {
            AuthModel auth = this.securityFactory.findByUsername(username);
            if (auth == null) {
                throw new UsernameNotFoundException(ResponseCode.USER_DONT_EXISTS.getMessage());
            }
            AuthUser authUser = new AuthUser(auth);
            return new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());
        } else {
            throw new CredentialsExpiredException(ResponseCode.TOKEN_EXPIRE.getMessage());
        }
    }
}

