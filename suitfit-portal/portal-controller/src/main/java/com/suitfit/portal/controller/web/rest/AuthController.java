package com.suitfit.portal.controller.web.rest;


import com.suitfit.framework.mvc.ResponseMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("auth")
public class AuthController {

    @RequestMapping(value = "needLogin", method = RequestMethod.GET)
    public ResponseMessage needLogin() {
        return ResponseMessage.error("401", "您还未登录");
    }

}
