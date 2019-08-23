package com.suitfit.portal.controller.web.rest;

import com.suitfit.portal.biz.DepartmentBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Exrick
 */
@Slf4j
@RestController
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private DepartmentBiz departmentBiz;


}
