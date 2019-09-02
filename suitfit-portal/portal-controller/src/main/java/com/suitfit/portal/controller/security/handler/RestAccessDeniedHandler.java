package com.suitfit.portal.controller.security.handler;


import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.servlet.ResponseUtils;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 403无权限
 * 因为在framework中已经添加了统一权限处理，这里的处理不会生效!!!!
 *
 * @author Exrickx
 */
@Slf4j
@Deprecated
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        ResponseUtils.out(response, new BaseException(ResponseCode.PERMISSION_ERROR));
    }

}
