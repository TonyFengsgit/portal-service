package com.suitfit.portal.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.AppChannel;
import com.suitfit.portal.model.pojo.criteria.ChannelQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "appChannel")
public interface AppChannelService extends BaseService<AppChannel> {

    @Cacheable(key = "'findById:' + #p0")
    AppChannel findById(Long id);

    @CacheEvict(allEntries = true)
    void updateEntity(AppChannel entity);

    @Cacheable(keyGenerator = "keyGenerator")
    IPage<AppChannel> findByPage(ChannelQueryCriteria query, Page initPage);

    @CacheEvict(allEntries = true)
    void deleteById(Long id);
}
