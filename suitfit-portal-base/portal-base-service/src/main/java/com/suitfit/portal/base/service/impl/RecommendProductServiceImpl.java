package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.RecommendProductMapper;
import com.suitfit.portal.base.service.RecommendProductService;
import com.suitfit.portal.model.entity.RecommendProduct;
import com.suitfit.portal.model.pojo.criteria.RecommendQueryCriteria;
import org.springframework.stereotype.Service;

@Service
public class RecommendProductServiceImpl extends BaseServiceImpl<RecommendProductMapper, RecommendProduct> implements RecommendProductService {
    @Override
    public RecommendProduct findByProductCode(String productCode) {
        LambdaQueryWrapper<RecommendProduct> query = new LambdaQueryWrapper<>();
        query.eq(RecommendProduct::getProductCode, productCode);
        query.last("limit 1");
        return getOne(query);
    }

    @Override
    public void updateEntity(RecommendProduct entity) {
        LambdaQueryWrapper<RecommendProduct> query = new LambdaQueryWrapper<>();
        query.eq(RecommendProduct::getProductCode, entity.getProductCode());
        update(entity, query);
    }

    @Override
    public void deleteByProductCode(String productCode) {
        RecommendProduct entity = new RecommendProduct();
        entity.setDisable(true);
        entity.setProductCode(productCode);
        updateEntity(entity);
    }

    @Override
    public IPage<RecommendProduct> findByPage(RecommendQueryCriteria criteria, Page initPage) {
        LambdaQueryWrapper<RecommendProduct> query = new LambdaQueryWrapper<>();
        query.eq(RecommendProduct::getDisable, false);
        if (criteria.getProductName()!=null){
            query.like(RecommendProduct::getProductName, criteria.getProductName());
        }
        if (criteria.getAppCode()!=null){
            query.eq(RecommendProduct::getAppCode, criteria.getAppCode());
        }
        if (criteria.getShowFlag()!=null){
            query.eq(RecommendProduct::getShowFlag, criteria.getShowFlag());
        }
        return page(initPage, query);
    }

    @Override
    public void disable(String productCode, Integer state) {
        RecommendProduct entity = new RecommendProduct();
        entity.setState(state);
        entity.setProductCode(productCode);
        updateEntity(entity);
    }

    @Override
    public void updateShowFlag(String productCode, Integer showFlag) {
        RecommendProduct entity = new RecommendProduct();
        entity.setProductCode(productCode);
        entity.setShowFlag(showFlag);
        updateEntity(entity);
    }
}
