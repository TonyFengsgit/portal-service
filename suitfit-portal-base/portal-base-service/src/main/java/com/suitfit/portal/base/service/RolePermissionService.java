package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.RolePermission;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @program: RolePermissionService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:07
 */
@CacheConfig(cacheNames = "rolePermission")
public interface RolePermissionService extends BaseService<RolePermission> {
    @CacheEvict(allEntries = true)
    void deleteByRoleId(Long id);

    @Cacheable(key = "'findByPermissionId:'+ #p0")
    List<RolePermission> findByPermissionId(Long id);
}
