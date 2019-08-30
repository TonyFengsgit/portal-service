package com.suitfit.portal.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.RecommendProduct;
import com.suitfit.portal.model.pojo.criteria.RecommendQueryCriteria;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "recommendProduct")
public interface RecommendProductService extends BaseService<RecommendProduct> {
    @Cacheable(key = "'findByProductCode:'+ #p0")
    RecommendProduct findByProductCode(String productCode);

    @CacheEvict(allEntries = true)
    void updateEntity(RecommendProduct entity);

    @CacheEvict(allEntries = true)
    void deleteByProductCode(String productCode);

    @Cacheable(keyGenerator = "keyGenerator")
    IPage<RecommendProduct> findByPage(RecommendQueryCriteria criteria, Page initPage);

    @CacheEvict(allEntries = true)
    void disable(String productCode, Integer state);

    void updateShowFlag(String productCode, Integer showFlag);
}
