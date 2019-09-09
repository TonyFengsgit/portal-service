package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.entity.ChannelTime;


import java.util.List;

public interface allChannelRlowService extends BaseService<ChannelFlow> {

    List<ChannelFlow> getAllChannelFlow(PageVO page);

    List<ChannelFlow> getAllChannelFlow(ChannelTime channelTime);


}
