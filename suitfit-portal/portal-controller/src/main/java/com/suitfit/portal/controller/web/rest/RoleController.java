package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.biz.RoleBiz;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RoleReq;
import com.suitfit.portal.model.pojo.vo.resp.RoleVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Exrickx
 */
@Slf4j
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleBiz roleBiz;

    @ApiOperation("获取单个role")
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_SELECT')")
    public ResponseMessage getRoles(@PathVariable Long id) {
        RoleVO vo = roleBiz.getRoles(id);
        return ResponseMessage.ok(vo);
    }

    @ApiOperation("返回全部的角色，新增用户时下拉选择")
    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','USER_ALL','USER_CREATE','USER_EDIT')")
    public ResponseMessage getAll(@ModelAttribute PageVO page) {
        PageVO<RoleVO> rolePage = roleBiz.getAll(PageUtils.initPage(page));
        return ResponseMessage.ok(rolePage);
    }

    @ApiOperation("查询角色")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_SELECT')")
    public ResponseMessage getRoles(RoleReq req, PageVO page) {
        PageVO<RoleVO> rolePage = roleBiz.getRoles(req, PageUtils.initPage(page));
        return ResponseMessage.ok(rolePage);
    }

    @ApiOperation("查询用户角色级别")
    @GetMapping("level")
    public ResponseMessage getLevel() {
        List<Integer> levels = roleBiz.getLevel();
        return ResponseMessage.ok(levels);
    }

    @ApiOperation("新增角色")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_CREATE')")
    public ResponseMessage create(@Validated @RequestBody RoleReq req) {
        roleBiz.create(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_EDIT')")
    public ResponseMessage update(@Validated @RequestBody RoleReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        roleBiz.update(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改角色菜单")
    @PutMapping(value = "menu")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_EDIT')")
    public ResponseMessage updateMenu(@RequestBody RoleReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        roleBiz.updateMenu(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改用户权限")
    @PutMapping(value = "permission")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_EDIT')")
    public ResponseMessage updatePermission(@RequestBody RoleReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        roleBiz.updatePermission(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("删除用户权限")
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLES_ALL','ROLES_DELETE')")
    public ResponseMessage delete(@PathVariable Long id) {
        roleBiz.delete(id);
        return ResponseMessage.ok();
    }
}
