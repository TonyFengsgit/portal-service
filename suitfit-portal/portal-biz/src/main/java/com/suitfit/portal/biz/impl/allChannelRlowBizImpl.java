package com.suitfit.portal.biz.impl;

import com.suitfit.framework.utils.bean.BeanUtils;


import com.suitfit.portal.base.service.allChannelRlowService;
import com.suitfit.portal.biz.allChannelFlowBiz;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class allChannelRlowBizImpl implements allChannelFlowBiz {

    @Autowired
    private allChannelRlowService rlowService ;

    //得到并且封装流量看板数据
    @Override
    public List<ChannelFlowVO> getChannelFlow() {

        System.out.println("测试：进入allChannelRlowBizImpl层");
        List<ChannelFlow> flows = rlowService.getallChannelFlow();
        List<ChannelFlowVO> flowVOlist = (List<ChannelFlowVO>) BeanUtils.convert(flows, ChannelFlowVO.class);
        return flowVOlist;
    }



    //条件查询
    @Override
    public List<ChannelFlowVO> getFlowByChTi(ChanTimeeq chanTime){

        List<ChannelFlow> flows = rlowService.getallChannelFlow(chanTime);
        List<ChannelFlowVO> flowVOSlist=(List<ChannelFlowVO>) BeanUtils.convert(flows,ChannelFlowVO.class);
        return  flowVOSlist;


    }
}
