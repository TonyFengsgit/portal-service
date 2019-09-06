package com.suitfit.portal.model.pojo.vo.resp;


import lombok.Data;

@Data
public class ChannelFlowVO {

    private String channel;
    private Long  pv;
    private Long  uv;

    private Long userNumT;
    private Long userNumF;

    private Long userPayNum;
    private Long userPayNumT;

    private Double PayNum;
}
