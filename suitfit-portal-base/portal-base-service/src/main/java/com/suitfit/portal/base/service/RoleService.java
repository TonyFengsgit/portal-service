package com.suitfit.portal.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Role;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @program: RoleService
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-16 10:06
 */
@CacheConfig(cacheNames = "role")
public interface RoleService extends BaseService<Role> {

    @Cacheable(key = "'defaultRole'")
    Role getDefaultRole();

    @Cacheable(keyGenerator = "keyGenerator")
    IPage<Role> findByCriteria(QueryCriteria criteria, Page initPage);

    @Cacheable(key = "'get:'+#p0")
    Role get(Long id);

    @Cacheable(keyGenerator = "keyGenerator")
    IPage<Role> selectByPage(Page initPage);

    @CacheEvict(allEntries = true)
    void updateEntity(Role entity);

    @Cacheable(key = "'findByName:'+#p0")
    Role findByName(String name);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);

    List<Role> findByRoleIds(Collection<Long> roles);
}
