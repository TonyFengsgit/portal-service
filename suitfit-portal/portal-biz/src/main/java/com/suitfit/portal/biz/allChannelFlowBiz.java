package com.suitfit.portal.biz;




import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelFlowVO;

import java.util.List;

public interface allChannelFlowBiz {

    //得到并且封装流量看板
    List<ChannelFlowVO> getChannelFlow();

    //根据条件查询渠道流量
    List<ChannelFlowVO> getFlowByChTi(ChanTimeeq chanTime);

}
