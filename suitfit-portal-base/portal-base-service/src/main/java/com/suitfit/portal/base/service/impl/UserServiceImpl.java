package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.portal.base.dao.mapper.UserMapper;
import com.suitfit.portal.base.service.UserService;
import com.suitfit.portal.model.entity.User;
import com.suitfit.portal.model.pojo.criteria.UserQueryCriteria;
import org.springframework.stereotype.Service;

/**
 * @program: UserServiceImpl
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:13
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findByName(String username) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getUserName, username);
        query.last("limit 1");
        return this.getOne(query);
    }

    @Override
    public IPage<User> findByCriteria(UserQueryCriteria criteria, Page initPage) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        if (criteria.getId() != null) {
            query.eq(User::getId, criteria.getId());
        }
        if (!StringUtils.isNullOrEmpty(criteria.getUserName())) {
            query.eq(User::getUserName, criteria.getUserName());
        }
        if (!StringUtils.isNullOrEmpty(criteria.getEmail())) {
            query.eq(User::getEmail, criteria.getEmail());
        }
        if (!StringUtils.isNullOrEmpty(criteria.getPhone())) {
            query.eq(User::getPhone, criteria.getPhone());
        }
        if (criteria.getState() != null) {
            query.eq(User::getState, criteria.getState());
        }
        if (criteria.getDeptIds() != null) {
            query.in(User::getDepartmentId, criteria.getDeptIds());
        }
        return page(initPage, query);
    }

    @Override
    public User findByEmail(String email) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getEmail, email);
        query.last("limit 1");
        return this.getOne(query);
    }

    @Override
    public User findByPhone(String phone) {
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getPhone, phone);
        query.last("limit 1");
        return this.getOne(query);
    }

    @Override
    public void updateEntity(User entity) {
        this.updateById(entity);
    }

    @Override
    public void delete(Long id) {
        this.removeById(id);
    }
}
