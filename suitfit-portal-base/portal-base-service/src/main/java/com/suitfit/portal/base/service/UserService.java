package com.suitfit.portal.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.User;
import com.suitfit.portal.model.pojo.criteria.UserQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * @program: UserService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:07
 */
@CacheConfig(cacheNames = "user")
public interface UserService extends BaseService<User> {

    @Cacheable(key = "'findByName:'+#p0")
    User findByName(String username);

    @Cacheable(keyGenerator = "keyGenerator")
    IPage<User> findByCriteria(UserQueryCriteria query, Page initPage);

    @Cacheable(key = "'findByEmail:'+#p0")
    User findByEmail(String email);

    @Cacheable(key = "'findByPhone:'+#p0")
    User findByPhone(String phone);

    @CacheEvict(allEntries = true)
    void updateEntity(User entity);

    @CacheEvict(allEntries = true)
    void delete(Long id);
}
