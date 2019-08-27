package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.RolePermissionMapper;
import com.suitfit.portal.base.service.RolePermissionService;
import com.suitfit.portal.model.entity.Permission;
import com.suitfit.portal.model.entity.RolePermission;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: RolePermissionServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:11
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public void deleteByRoleId(Long id) {
        QueryWrapper<RolePermission> query = new QueryWrapper<>();
        query.lambda().eq(RolePermission::getRoleId, id);
        this.remove(query);
    }

    @Override
    public List<RolePermission> findByPermissionId(Long id) {
        QueryWrapper<RolePermission> query = new QueryWrapper<>();
        query.lambda().eq(RolePermission::getPermissionId, id);
        return this.list(query);
    }

    @Override
    public List<Permission> findByRoleId(Long roleId) {
        return this.baseMapper.findByRoleId(roleId);
    }
}
