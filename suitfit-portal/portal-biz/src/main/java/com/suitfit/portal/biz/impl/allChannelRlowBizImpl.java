package com.suitfit.portal.biz.impl;

import com.suitfit.framework.utils.bean.BeanUtils;


import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.base.service.allChannelRlowService;
import com.suitfit.portal.biz.allChannelFlowBiz;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.entity.ChannelTime;
import com.suitfit.portal.model.pojo.vo.req.ChannelTimeReq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class allChannelRlowBizImpl implements allChannelFlowBiz {

    @Autowired
    private allChannelRlowService flowService ;

    @Override
    public List<ChannelFlowVO> getAllChannelFlow(PageVO page) {

        List<ChannelFlow> channelflow = flowService.getAllChannelFlow(page);
        List<ChannelFlowVO> channelflowVO= (List<ChannelFlowVO>) BeanUtils.convert(channelflow, ChannelFlowVO.class);
        return channelflowVO;
    }

    @Override
    public List<ChannelFlowVO> getChannelFlowByChTi(ChannelTimeReq chanTime, PageVO page) {


        ChannelTime channelTime = new ChannelTime();
        channelTime.setChannel(chanTime.getChannel());
        channelTime.setTime(chanTime.getTime());
        channelTime.setCurrent(page.getCurrent());
        channelTime.setSize(page.getSize());
        List<ChannelFlow> channelflow = flowService.getAllChannelFlow(channelTime);
        List<ChannelFlowVO> channelflowVO= (List<ChannelFlowVO>) BeanUtils.convert(channelflow, ChannelFlowVO.class);
        return channelflowVO;
    }



}
