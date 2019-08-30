package com.suitfit.portal.controller.utils;

import com.alibaba.fastjson.JSON;
import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.mvc.ResponseMessage;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: ResponseUtils
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-19 09:55
 */
@Slf4j
public class ResponseUtils {

    public static void out(HttpServletResponse response, ResponseMessage message) {
        ServletOutputStream servletOutputStream = null;
        try {
            response.setContentType("application/json;charset=UTF-8");
            servletOutputStream = response.getOutputStream();
            servletOutputStream.write(JSON.toJSONString(message).getBytes("utf-8"));
        } catch (Exception ex) {
        } finally {
//            if (servletOutputStream != null) {
//                try {
//                    servletOutputStream.flush();
//                    servletOutputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
        }
    }

    public static void out(HttpServletResponse response, Object obj) {
        out(response, ResponseMessage.ok(obj));
    }

    public static void out(HttpServletResponse response, BaseException e) {
        out(response, ResponseMessage.error(e.getErrorCode(), e.getMessage()));
    }


}
