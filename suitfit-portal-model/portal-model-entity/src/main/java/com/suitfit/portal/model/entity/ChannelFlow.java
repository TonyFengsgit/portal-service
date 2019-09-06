package com.suitfit.portal.model.entity;



import lombok.Data;

@Data
public class ChannelFlow {


    private String channel;//渠道

    private Long  pv;//PV

    private Long  uv;//UV

    private Long userNumT;//注册人数

    private Long userNumF;//作弊注册人数

    private Long userPayNum;//放款人数

    private Long userPayNumT;//放款成功人数

    private Double PayNum;//总额


}
