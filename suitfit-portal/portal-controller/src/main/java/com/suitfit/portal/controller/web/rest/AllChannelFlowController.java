package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.portal.biz.allChannelFlowBiz;
import com.suitfit.portal.model.entity.ChannelFlow;
import com.suitfit.portal.model.pojo.vo.req.ChanTimeeq;
import com.suitfit.portal.model.pojo.vo.resp.ChannelFlowVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("Channel")
public class AllChannelFlowController {


    @Autowired
    private allChannelFlowBiz allChannelFlow;


    //"流量看板"接口
    //@GetMapping("flow")
    public ResponseMessage getChannelFlow(@RequestBody ChanTimeeq chanTime){

        if ((chanTime.getChannel().isEmpty()) && (chanTime.getTime().isEmpty())){
            return getAllFlow();
        }else
            return getFlowByChTi(chanTime);
    }

    //默认查询全部渠道流量
    @GetMapping("flow")
    public ResponseMessage getAllFlow(){

        System.out.println("测试：进入getChannelFlow方法");
        List<ChannelFlowVO> flow = allChannelFlow.getChannelFlow();
        System.out.println("测试：执行完方法");
        return ResponseMessage.ok(flow);
    }

    //根据条件查询某渠道流量
    public ResponseMessage getFlowByChTi(ChanTimeeq chanTime){

        List<ChannelFlowVO> flowByChTi = allChannelFlow.getFlowByChTi(chanTime);

        return ResponseMessage.ok(flowByChTi);

    }


}
