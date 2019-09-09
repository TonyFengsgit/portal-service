package com.suitfit.portal.base.service.impl;



import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.base.dao.mapper.ChannelFlowMapper;
import com.suitfit.portal.base.service.allChannelRlowService;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.entity.ChannelTime;
import com.suitfit.portal.model.pojo.vo.req.ChannelTimeReq;
import org.springframework.stereotype.Service;


import java.util.*;


@Service
public class allChannelRlowServiceImpl extends BaseServiceImpl<ChannelFlowMapper,ChannelFlow> implements allChannelRlowService {

//BaseServiceImpl<ChannelFlowMapper,ChannelFlow>已经将ChannelFlowMapper集成了，读出了xml中的查询语句，可以直接用


    /**
     * 默认查询全部
     * @return
     */
    @Override
    public List<ChannelFlow> getAllChannelFlow(PageVO page) {
        return this.baseMapper.channelFlow(page);
    }

    @Override
    public List<ChannelFlow> getAllChannelFlow(ChannelTime channelTime) {
        return this.baseMapper.findChannelFlowByChTi(channelTime);
    }



}
