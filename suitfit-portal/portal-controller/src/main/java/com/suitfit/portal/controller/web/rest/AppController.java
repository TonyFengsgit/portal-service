package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.biz.AppBiz;
import com.suitfit.portal.model.pojo.vo.resp.AppNameVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Autowired
    private AppBiz appBiz;

    @GetMapping
    public ResponseMessage apps(){
        List<AppNameVO> voList = appBiz.apps();
        return ResponseMessage.ok(voList);
    }
}
