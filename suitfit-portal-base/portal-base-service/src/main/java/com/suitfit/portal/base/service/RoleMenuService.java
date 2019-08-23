package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.entity.RoleMenu;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "roleMenu")
public interface RoleMenuService extends BaseService<RoleMenu> {

    @CacheEvict(allEntries = true)
    void deleteByRoleId(Long id);

    @Cacheable(keyGenerator = "keyGenerator")
    List<Menu> findByRoleIds(List<Long> roleIds);

    @CacheEvict(allEntries = true)
    void removeByMenuId(Long id);
}
