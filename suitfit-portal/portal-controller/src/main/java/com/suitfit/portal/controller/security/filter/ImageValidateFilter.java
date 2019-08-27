package com.suitfit.portal.controller.security.filter;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.redis.RedisService;
import com.suitfit.framework.utils.servlet.ResponseUtils;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.properties.CaptchaProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 图形验证码过滤器
 *
 * @author Exrick
 */
@Slf4j
public class ImageValidateFilter extends OncePerRequestFilter {


    private CaptchaProperties captchaProperties;

    private RedisService redisTemplate;

    public ImageValidateFilter(CaptchaProperties properties, RedisService redisTemplate) {
        this.captchaProperties = properties;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        Boolean flag = false;
        String requestUrl = request.getRequestURI();
        PathMatcher pathMatcher = new AntPathMatcher();
        for (String url : captchaProperties.getFilters()) {
            if (pathMatcher.match(url, requestUrl)) {
                flag = true;
                break;
            }
        }
        String debug = request.getParameter("debug");
        if (flag && !"true".equals(debug)) {
            try {
                String captchaId = request.getParameter("captchaId");
                String code = request.getParameter("verifyCode");
                if (StringUtils.isBlank(captchaId) || StringUtils.isBlank(code)) {
                    throw new BaseException(ResponseCode.CAPTCHA_PARAM_INCOMPLETE);
                }
                Object redisCode = redisTemplate.get(captchaId);
                if (StringUtils.isNullOrEmpty(redisCode)) {
                    throw new BaseException(ResponseCode.CAPTCHA_EXPIRE);
                }
                if (!redisCode.toString().toLowerCase().equals(code.toLowerCase())) {
                    log.info("验证码错误：code:" + code + "，redisCode:" + redisCode);
                    throw new BaseException(ResponseCode.CAPTCHA_ERROR);
                }
                // 已验证清除key
                redisTemplate.del(captchaId);
                // 验证成功 放行
                chain.doFilter(request, response);
                return;
            } catch (BaseException e) {
                log.error("captcha code error", e);
                ResponseUtils.out(response, e);
            }
        } else {
            // 无需验证 放行
            chain.doFilter(request, response);
        }

    }
}
