package com.suitfit.portal.base.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.entity.ChannelTime;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @program: ChannelFlowMapper
 * @description:
 * @author: Feng
 * @create: 2019-09-03 15:15
 */
@DBSource("all")
@Mapper
public interface ChannelFlowMapper extends BaseMapper<ChannelFlow> {

    List<ChannelFlow> channelFlow(PageVO page);

    List<ChannelFlow> findChannelFlowByChTi(ChannelTime channelTime);


}
