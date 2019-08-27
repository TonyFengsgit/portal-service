package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.portal.base.dao.mapper.RoleMapper;
import com.suitfit.portal.base.service.RoleService;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @program: RoleServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:12
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements RoleService {


    @Override
    public Role getDefaultRole() {
        LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<>();
        query.eq(Role::getDefaultRole, true);
        query.last("limit 1");
        return this.getOne(query);
    }

    @Override
    public IPage<Role> findByCriteria(QueryCriteria criteria, Page initPage) {
        LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<>();
        if (criteria.getId() != null) {
            query.eq(Role::getId, criteria.getId());
        }
        if (!StringUtils.isNullOrEmpty(criteria.getName())) {
            query.eq(Role::getName, criteria.getName());
        }
        return page(initPage, query);
    }


    @Override
    public Role get(Long id) {
        return this.getById(id);
    }

    @Override
    public IPage<Role> selectByPage(Page initPage) {
        return page(initPage);
    }

    @Override
    public void updateEntity(Role entity) {
        this.updateById(entity);
    }

    @Override
    public Role findByName(String name) {
        LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<>();
        query.eq(Role::getName, name);
        query.last("limit 1");
        return this.getOne(query);
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }

    @Override
    public List<Role> findByRoleIds(Collection<Long> roles) {
        LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<>();
        query.in(Role::getId, roles);
        return list(query);
    }
}
