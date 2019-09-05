package com.suitfit.portal.base.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suitfit.framework.dynamic.annotation.DBSource;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;
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
//ChannelFlowMapper继承了BaseMapper，所以项目中都是用BaseMapper多态调用子类方法

    //1.查询封装各个渠道：渠道、PV、UV -->
    List<ChannelFlow> allChannelCpvuv();

    //2.查询各个渠道的注册人数
    List<ChannelFlow> allChannelUserNum();

    //3.查询各个渠道支付人数、支付成功人数、总金额
    List<ChannelFlow> payUserNum();

    //4.根据条件查询:渠道、时间
    List<ChannelFlow> findCpvuvbyChTi(ChanTimeeq chanTimeeq);

    //5.根据条件渠道、时间查询：渠道的注册人数
    List<ChannelFlow> findUserNumbyChTi(ChanTimeeq chanTimeeq);

    //6.根据条件渠道、时间查询：渠道支付人数、支付成功人数、总金额
    List<ChannelFlow> findpayNumbyChTi(ChanTimeeq chanTimeeq);

}
