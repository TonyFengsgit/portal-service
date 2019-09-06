package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.portal.biz.allChannelFlowBiz;
import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "Channel",method = RequestMethod.POST)
public class AllChannelFlowController {


    @Autowired
    private allChannelFlowBiz allChannelFlow;


    //"流量看板"接口

    @PostMapping("flow")
    public ResponseMessage getChannelFlow(@RequestBody ChanTimeeq chanTime){

        System.out.println("传入参数："+chanTime.getChannel()+"---"+chanTime.getTime());
        if ((chanTime.getChannel()==null) && (chanTime.getTime()==null)){
            System.out.println("进入默认查询方法");
            return getAllFlow();
        }else{
            System.out.println("进入条件搜索方法");
            return getFlowByChTi(chanTime);
        }
    }

    //默认查询全部渠道流量
    //@GetMapping("flow")
    public ResponseMessage getAllFlow(){

        List<ChannelFlowVO> flow = allChannelFlow.getChannelFlow();
        for (ChannelFlowVO channelFlowVO : flow) {
            System.out.println(channelFlowVO.getChannel());
            System.out.println(channelFlowVO.getUserNumT());
            System.out.println(channelFlowVO.getUserNumF());
            System.out.println(channelFlowVO.getPayNum());
            System.out.println(channelFlowVO.getPv());
            System.out.println(channelFlowVO.getUv());
            System.out.println(channelFlowVO.getUserPayNumT());
        }
        return ResponseMessage.ok(flow);
    }

    //根据条件查询某渠道流量
    public ResponseMessage getFlowByChTi(ChanTimeeq chanTime){

        List<ChannelFlowVO> flowByChTi = allChannelFlow.getFlowByChTi(chanTime);

        return ResponseMessage.ok(flowByChTi);

    }


}
