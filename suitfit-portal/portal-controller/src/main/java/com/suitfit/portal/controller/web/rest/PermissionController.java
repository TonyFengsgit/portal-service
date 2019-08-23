package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.biz.PermissionBiz;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.vo.req.PermissionReq;
import com.suitfit.portal.model.pojo.vo.resp.PermissionVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Exrick
 */
@Slf4j
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionBiz permissionBiz;

    @ApiOperation("权限树")
    @GetMapping(value = "tree")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE','PERMISSION_EDIT','ROLES_SELECT','ROLES_ALL')")
    public ResponseMessage getTree() {
        List<Map<String, Object>> tree = permissionBiz.getPermissionTree(null);
        return ResponseMessage.ok(tree);
    }

    @ApiOperation("查询权限")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public ResponseMessage getPermission(PermissionReq req) {
        List<PermissionVO> list = permissionBiz.getPermission(req);
        return ResponseMessage.ok(list);
    }

    @ApiOperation("新增权限")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE')")
    public ResponseMessage create(@Validated @RequestBody PermissionReq req) {
        permissionBiz.create(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改权限")
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_EDIT')")
    public ResponseMessage update(@Validated @RequestBody PermissionReq req) {
        if (ObjectUtils.isEmpty(req.getId())) {
            throw new BaseException(ResponseCode.PARAM_INCOMPLETE);
        }
        permissionBiz.update(req);
        return ResponseMessage.ok();
    }

    @ApiOperation("删除权限")
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_DELETE')")
    public ResponseMessage delete(@PathVariable Long id) {
        permissionBiz.delete(id);
        return ResponseMessage.ok();
    }
}
