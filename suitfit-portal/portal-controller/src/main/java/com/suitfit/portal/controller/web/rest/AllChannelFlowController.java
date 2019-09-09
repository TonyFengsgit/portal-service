package com.suitfit.portal.controller.web.rest;

import com.suitfit.framework.mvc.ResponseMessage;
import com.suitfit.framework.utils.StringUtils;
import com.suitfit.framework.utils.page.PageVO;
import com.suitfit.portal.biz.allChannelFlowBiz;
import com.suitfit.portal.model.pojo.vo.req.ChannelTimeReq;
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
    public ResponseMessage getFlow(@RequestBody Param param){

        ChannelTimeReq channelTime = param.channelTime;
        PageVO page = param.page;
        System.out.println("传入参数："+channelTime.getChannel()+"---"+channelTime.getTime());
        if ((StringUtils.isEmpty(channelTime.getChannel())) && (StringUtils.isEmpty(channelTime.getTime()))){
            System.out.println("进入默认查询方法");
            return getChannelFlow(page);
        }else{
            System.out.println("进入条件搜索方法");
            return getChannelFlowByChTi(channelTime,page);
        }
    }

    public ResponseMessage getChannelFlow(PageVO page){
        List<ChannelFlowVO> allChannelFlow = this.allChannelFlow.getAllChannelFlow(page);
        return ResponseMessage.ok(allChannelFlow);
    }


    public ResponseMessage getChannelFlowByChTi(ChannelTimeReq chanTime,PageVO page){
        List<ChannelFlowVO> allChannelFlow = this.allChannelFlow.getChannelFlowByChTi(chanTime,page);
        return ResponseMessage.ok(allChannelFlow);
    }


}
class Param{
    public ChannelTimeReq channelTime;
    public PageVO page;
}