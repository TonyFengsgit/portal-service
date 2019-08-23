package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.biz.UserBiz;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.UserPassReq;
import com.suitfit.portal.model.pojo.vo.req.UserReq;
import com.suitfit.portal.model.pojo.vo.resp.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author Exrickx
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserBiz userBiz;


    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT')")
    public ResponseMessage users(@ModelAttribute UserReq query, PageVO page) {
        PageVO<UserVO> pageUser = userBiz.users(query, PageUtils.initPage(page));
        return ResponseMessage.ok(pageUser);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_CREATE')")
    public ResponseMessage create(@Validated @RequestBody UserReq req) {
        userBiz.create(req);
        return ResponseMessage.ok();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_EDIT')")
    public ResponseMessage update(@Validated @RequestBody UserReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        userBiz.update(req);
        return ResponseMessage.ok();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_DELETE')")
    public ResponseMessage delete(@PathVariable Long id) {
        userBiz.delete(id);
        return ResponseMessage.ok();
    }

    @PostMapping("updatePass")
    public ResponseMessage updatePass(@RequestBody UserPassReq req) {
        userBiz.updatePass(req);
        return ResponseMessage.ok();
    }
}
