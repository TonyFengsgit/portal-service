package com.suitfit.portal.base.service;

import com.suitfit.framework.service.BaseService;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;

import java.util.List;

public interface allChannelRlowService extends BaseService<ChannelFlow> {

    //默认全部查询渠道流量
    List<ChannelFlow> getallChannelFlow();

    //根据条件：时间、渠道 查询某个渠道流量
    List<ChannelFlow> getallChannelFlow(ChanTimeeq chanTimeeq);

    //拼接集合，注意顺序，不可颠倒
    List<ChannelFlow> mergeFlow(List<ChannelFlow> cpvuv,List<ChannelFlow> userNum,List<ChannelFlow> payUserNum);

}
