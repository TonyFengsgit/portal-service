package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.biz.MenuBiz;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.dto.MenuDTO;
import com.suitfit.portal.model.pojo.vo.req.MenuReq;
import com.suitfit.portal.model.pojo.vo.resp.MenuVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private MenuBiz menuBiz;

    @ApiOperation("用户菜单树")
    @GetMapping("init")
    public ResponseMessage init() {
        List<MenuVO> list = menuBiz.init();
        return ResponseMessage.ok(list);
    }

    @ApiOperation("回全部的菜单")
    @GetMapping(value = "tree")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE','MENU_EDIT','ROLES_SELECT','ROLES_ALL')")
    public ResponseMessage getMenuTree() {
        List<Map<String, Object>> menuTree = menuBiz.getMenuTree(null);
        return ResponseMessage.ok(menuTree);
    }

    @ApiOperation("查询菜单")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
    public ResponseMessage getMenus(MenuReq req) {
        List<MenuDTO> list = menuBiz.getMenus(req);
        return ResponseMessage.ok(list);
    }

    @ApiOperation("新增菜单")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE')")
    public ResponseMessage create(@Validated @RequestBody MenuReq req) {
        menuBiz.create(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改菜单")
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_EDIT')")
    public ResponseMessage update(@Validated @RequestBody MenuReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        menuBiz.update(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("删除菜单")
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_DELETE')")
    public ResponseMessage delete(@PathVariable Long id) {
        menuBiz.delete(id);
        return ResponseMessage.ok();
    }
}
