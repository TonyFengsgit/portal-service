package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Permission;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @program: PermissionService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:05
 */
@CacheConfig(cacheNames = "permission")
public interface PermissionService extends BaseService<Permission> {

    @Cacheable(key = "'getByPid:'+ #p0")
    List<Permission> getByPid(long l);

    @Cacheable(keyGenerator = "keyGenerator")
    List<Permission> queryAll(QueryCriteria query);

    @Cacheable(key = "'findByName:'+ #p0")
    Permission findByName(String name);

    @Cacheable(key = "'findById:'+ #p0")
    Permission findById(Long id);

    @CacheEvict(allEntries = true)
    void updateEntity(Permission entity);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);
}
