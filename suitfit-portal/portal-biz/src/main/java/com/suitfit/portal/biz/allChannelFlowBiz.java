package com.suitfit.portal.biz;


import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.model.pojo.vo.req.ChannelTimeReq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelFlowVO;

import java.util.List;

public interface allChannelFlowBiz {

    List<ChannelFlowVO> getAllChannelFlow(PageVO page);

    List<ChannelFlowVO> getChannelFlowByChTi(ChannelTimeReq chanTime,PageVO page);


}
