package com.suitfit.portal.base.service.utils;

import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.service.RolePermissionService;
import com.suitfit.portal.base.service.UserRoleService;
import com.suitfit.portal.base.service.UserService;
import com.suitfit.portal.model.entity.Permission;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.User;
import com.suitfit.portal.model.pojo.auth.AuthUser;
import com.suitfit.portal.model.pojo.dto.AuthModel;
import com.suitfit.portal.model.pojo.dto.PermissionModel;
import com.suitfit.portal.model.pojo.dto.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SecurityFactory {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    public AuthModel findByUsername(String username) {
        User user = userService.findByName(username);
        if (user != null) {
            AuthModel auth = new AuthModel();
            BeanUtils.copyProperties(user, auth);
            List<Role> userRoleList = userRoleService.findByUserId(user.getId());
            List<RoleModel> roleModels = userRoleList.stream().map(role -> {
                RoleModel roleModel = new RoleModel();
                roleModel.setName(role.getName());
                List<Permission> permissions = rolePermissionService.findByRoleId(role.getId());
                List<PermissionModel> permissionModels = (List<PermissionModel>) BeanUtils.convert(permissions, PermissionModel.class);
                roleModel.setPermissions(permissionModels);
                return roleModel;
            }).collect(Collectors.toList());
            auth.setRoles(roleModels);
            return auth;
        }
        return null;
    }

    public AuthUser getCurrentUser() {
        AuthUser userDetails = null;
        try {
            userDetails = (AuthUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            // TODO 登陆状态过期
            throw new BaseException();
        }
        return userDetails;
    }

    public String getUsername() {
        AuthUser user = getCurrentUser();
        return user.getUsername();
    }

    public Long getUserId() {
        AuthUser user = getCurrentUser();
        return user.getId();
    }
}
