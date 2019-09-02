package com.suitfit.portal.biz.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.suitfit.framework.exception.BaseException;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.framework.utils.page.PageUtils;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.base.service.AppChannelService;
import com.suitfit.portal.base.service.UserService;
import com.suitfit.portal.biz.ChannelBiz;
import com.suitfit.portal.model.entity.AppChannel;
import com.suitfit.portal.model.entity.User;
import com.suitfit.portal.model.pojo.code.ResponseCode;
import com.suitfit.portal.model.pojo.criteria.ChannelQueryCriteria;
import com.suitfit.portal.model.pojo.vo.req.ChannelReq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChannelBizImpl implements ChannelBiz {

    @Autowired
    private AppChannelService appChannelService;

    @Autowired
    private UserService userService;

    @Override
    public void create(ChannelReq req) {
        User user = userService.findByName(req.getChannelCode());
        if (user==null){
            throw new BaseException(ResponseCode.CHANNEL_USER_DONT_EXISTS);
        }
        AppChannel entity = new AppChannel();
        BeanUtils.copyProperties(req, entity);
        appChannelService.save(entity);
    }

    @Override
    public void update(ChannelReq req) {
        AppChannel channl1 = appChannelService.findById(req.getId());
        if (!StringUtils.equalsIgnoreCase(req.getChannelCode(), channl1.getChannelCode())){
            throw new BaseException("渠道code不可更改");
        }
        AppChannel entity = new AppChannel();
        BeanUtils.copyProperties(req, entity);
        entity.setId(channl1.getId());
        appChannelService.updateEntity(entity);
    }

    @Override
    public PageVO<ChannelVO> channels(ChannelReq req, Page initPage) {
        ChannelQueryCriteria query = new ChannelQueryCriteria();
        BeanUtils.copyProperties(req, query);
        IPage<AppChannel> pageEntity = appChannelService.findByPage(query, initPage);
        PageVO<ChannelVO> pageVO = PageUtils.fromIpage(pageEntity, ChannelVO.class);
        return pageVO;
    }

    @Override
    public void delete(Long id) {
        appChannelService.deleteById(id);
    }
}
