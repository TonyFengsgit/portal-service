package com.suitfit.portal.base.service.impl;



import com.suitfit.framework.service.impl.BaseServiceImpl;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.bean.BeanUtils;
import com.suitfit.portal.base.dao.mapper.ChannelFlowMapper;
import com.suitfit.portal.base.service.allChannelRlowService;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class allChannelRlowServiceImpl extends BaseServiceImpl<ChannelFlowMapper,ChannelFlow> implements allChannelRlowService {

//BaseServiceImpl<ChannelFlowMapper,ChannelFlow>已经将ChannelFlowMapper集成了，读出了xml中的查询语句，可以直接用

    @Override
    public List<ChannelFlow> getallChannelFlow() {
        //直接用baseMapper的方法，获取渠道、PV、UV,因为ChannelFlowMapper实现了baseMapper
        //多态调用
        System.out.println("进入allChannelRlowServiceImpl层");
        List<ChannelFlow>  cpvuv=this.baseMapper.allChannelCpvuv();
        List<ChannelFlow> userNum = this.baseMapper.allChannelUserNum();
        List<ChannelFlow> payUserNum = this.baseMapper.payUserNum();


        //返回完整版的"流量看板"集合
        return mergeFlow(cpvuv,userNum,payUserNum);
    }

    @Override
    public List<ChannelFlow> getallChannelFlow(ChanTimeeq chanTimeeq) {

        System.out.println("进入allChannelRlowServiceImpl层");
        List<ChannelFlow> CpvuvbyChTi = this.baseMapper.findCpvuvbyChTi(chanTimeeq);
        List<ChannelFlow> payNumbyChTi = this.baseMapper.findpayNumbyChTi(chanTimeeq);
        List<ChannelFlow> UserNumbyChTi = this.baseMapper.findUserNumbyChTi(chanTimeeq);

        return mergeFlow(CpvuvbyChTi,payNumbyChTi,UserNumbyChTi);
    }

    //使用此方法必须注意集合顺序，将集合拼接在一起
    @Override
    public List<ChannelFlow> mergeFlow(List<ChannelFlow> cpvuv, List<ChannelFlow> userNum, List<ChannelFlow> payUserNum) {
        //遍历，取出渠道
        for (ChannelFlow channelFlow : cpvuv) {
            String channal = channelFlow.getChannal();
            //遍历第二个集合
            for (ChannelFlow flow : userNum) {
                //比对渠道,如果相同的渠道
                if (StringUtils.equals(channal,flow.getChannal())) {
                    //取出对应的注册人数值
                    Long userNumT = flow.getUserNumT();
                    //存入真注册人数数
                    channelFlow.setUserNumT(userNumT);
                    //存入改变后注册人数
                    channelFlow.setUserNumF(userNumT * 12);
                }

            }

        }
        for (ChannelFlow channelFlow : cpvuv) {
            String channal = channelFlow.getChannal();
            for (ChannelFlow paynum : payUserNum) {
                if (StringUtils.equals(channal,paynum.getChannal())){
                    //设置放款人数
                    channelFlow.setUserPayNum(paynum.getUserPayNum());
                    //设置放款成功人数
                    channelFlow.setUserPayNumT(paynum.getUserPayNumT());
                    //设置总金额
                    channelFlow.setPayNum(paynum.getPayNum());
                }
            }

        }

        List<ChannelFlow> cpvuvList = (List<ChannelFlow>) BeanUtils.convert(cpvuv, ChannelFlow.class);
        //打印测试
        for (ChannelFlow channelFlow : cpvuvList) {
            System.out.println("测试：查询渠道"+channelFlow.getChannal());
            System.out.println("测试：查询总额"+channelFlow.getPayNum());
        }
        return cpvuvList;
    }




}
