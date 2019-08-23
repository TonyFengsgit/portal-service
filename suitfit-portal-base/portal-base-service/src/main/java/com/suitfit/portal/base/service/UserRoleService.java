package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.entity.UserRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @program: UserRoleService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:07
 */
@CacheConfig(cacheNames = "userRole")
public interface UserRoleService extends BaseService<UserRole> {

    @Cacheable(key = "'findByUserId:' + #p0")
    List<Role> findByUserId(Long id);

    @CacheEvict(allEntries = true)
    void removeByUserId(Long id);

    @Cacheable(key = "'findByRoleId:' + #p0")
    List<UserRole> findByRoleId(Long id);
}
