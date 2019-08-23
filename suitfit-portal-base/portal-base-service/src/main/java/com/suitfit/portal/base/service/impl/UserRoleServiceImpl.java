package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.UserRoleMapper;
import com.suitfit.portal.base.service.UserRoleService;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: UserRoleServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:12
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public List<Role> findByUserId(Long userId) {
        return this.baseMapper.findByUserId(userId);
    }


    @Override
    public void removeByUserId(Long id) {
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getUserId, id);
        remove(query);
    }

    @Override
    public List<UserRole> findByRoleId(Long id) {
        QueryWrapper<UserRole> query = new QueryWrapper<>();
        query.lambda().eq(UserRole::getRoleId, id);
        return list(query);
    }
}
