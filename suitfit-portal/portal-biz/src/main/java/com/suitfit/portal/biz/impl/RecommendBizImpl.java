package com.suitfit.portal.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.dao.utils.PageUtils;
import com.suitfit.portal.base.service.RecommendProductService;
import com.suitfit.portal.biz.RecommendBiz;
import com.suitfit.portal.model.entity.RecommendProduct;
import com.suitfit.portal.model.pojo.criteria.RecommendQueryCriteria;
import com.suitfit.portal.model.pojo.vo.common.PageVO;
import com.suitfit.portal.model.pojo.vo.req.RecommendReq;
import com.suitfit.portal.model.pojo.vo.resp.RecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendBizImpl implements RecommendBiz {

    @Autowired
    private RecommendProductService service;

    @Override
    public void create(RecommendReq req) {
        if (service.findByProductCode(req.getProductCode())!=null){
            throw new BaseException("");
        }

        RecommendProduct entity = new RecommendProduct();
        BeanUtils.copyProperties(req, entity);
        service.save(entity);
    }

    @Override
    public void update(RecommendReq req) {
        if (service.findByProductCode(req.getProductCode())!=null){
            throw new BaseException("");
        }
        RecommendProduct entity = new RecommendProduct();
        BeanUtils.copyProperties(req, entity);
        service.updateEntity(entity);
    }

    @Override
    public void delete(String productCode) {
        service.deleteByProductCode(productCode);
    }

    @Override
    public PageVO<RecommendVO> products(RecommendReq req, Page initPage) {
        RecommendQueryCriteria criteria = new RecommendQueryCriteria();
        BeanUtils.copyProperties(req, criteria);
        IPage<RecommendProduct> iPage = service.findByPage(criteria, initPage);
        PageVO<RecommendVO> pageVO = PageUtils.fromIpage(iPage, RecommendVO.class);
        return pageVO;
    }

    @Override
    public void disable(String productCode) {
        service.disable(productCode, 0);
    }

    @Override
    public void undisable(String productCode) {
        service.disable(productCode, 1);
    }

    @Override
    public void updateShowFlag(String productCode, Integer showFlag) {
        service.updateShowFlag(productCode, showFlag);
    }
}
