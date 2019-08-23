package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.Menu;
import com.suitfit.portal.model.pojo.criteria.QueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "menu")
public interface MenuService extends BaseService<Menu> {

    @Cacheable(key = "'findByPid:'+#p0")
    List<Menu> findByPid(long l);

    @Cacheable(keyGenerator = "keyGenerator")
    List<Menu> findByCriteria(QueryCriteria query);

    @Cacheable(key = "'findByName:' + #p0")
    Menu findByName(String name);

    @Cacheable(key = "'findById:' + #p0")
    Menu findById(Long id);

    @CacheEvict(allEntries = true)
    void updateEntity(Menu entity);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);
}
