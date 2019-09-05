package com.suitfit.portal.model.entity;



import lombok.Data;

@Data
public class ChannelFlow {


    private String channal;

    private Long  pv;

    private Long  uv;

    private Long userNumT;

    private Long userNumF;

    private Long userPayNum;

    private Long userPayNumT;//放款成功人数

    private Double PayNum;//总额


}
