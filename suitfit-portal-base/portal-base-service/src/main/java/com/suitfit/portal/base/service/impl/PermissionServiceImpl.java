package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.portal.base.dao.mapper.PermissionMapper;
import com.suitfit.portal.base.service.PermissionService;
import com.suitfit.portal.model.entity.Permission;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: PermissionServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:09
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> getByPid(long l) {
        QueryWrapper<Permission> query = new QueryWrapper<>();
        query.lambda().eq(Permission::getParentId, l);
        return list(query);
    }

    @Override
    public List<Permission> queryAll(QueryCriteria query) {
        LambdaQueryWrapper<Permission> q = new LambdaQueryWrapper<>();
        if (query.getId() != null)
            q.eq(Permission::getId, query.getId());
        if (!StringUtils.isNullOrEmpty(query.getName())) {
            q.eq(Permission::getName, query.getName());
        }
        return list(q);
    }

    @Override
    public Permission findByName(String name) {
        LambdaQueryWrapper<Permission> query = new LambdaQueryWrapper();
        query.eq(Permission::getName, name);
        query.last("limit 1");
        return getOne(query);
    }

    @Override
    public Permission findById(Long id) {
        return this.getById(id);
    }

    @Override
    public void updateEntity(Permission entity) {
        this.updateById(entity);
    }

    @Override
    public void deleteById(Long id) {
        this.removeById(id);
    }
}
