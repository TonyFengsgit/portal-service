package com.suitfit.portal.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.portal.base.dao.mapper.AppChannelMapper;
import com.suitfit.portal.base.service.AppChannelService;
import com.suitfit.portal.model.entity.AppChannel;
import com.suitfit.portal.model.pojo.criteria.ChannelQueryCriteria;
import org.springframework.stereotype.Service;

@Service
public class AppChannelServiceImpl extends BaseServiceImpl<AppChannelMapper, AppChannel> implements AppChannelService {
    @Override
    public AppChannel findById(Long id) {

        return getById(id);
    }

    @Override
    public void updateEntity(AppChannel entity) {
        updateById(entity);
    }

    @Override
    public IPage<AppChannel> findByPage(ChannelQueryCriteria criteria, Page initPage) {
        LambdaQueryWrapper<AppChannel> query = new LambdaQueryWrapper<>();
        if (criteria.getAppCode()!=null){
            query.eq(AppChannel::getAppCode, criteria.getAppCode());
        }
        if (criteria.getChannelCode()!=null){
            query.eq(AppChannel::getChannelCode, criteria.getChannelCode());
        }
        if (criteria.getState()!=null){
            query.eq(AppChannel::getState, criteria.getState());
        }
        return page(initPage, query);
    }

    @Override
    public void deleteById(Long id) {
        removeById(id);
    }
}
