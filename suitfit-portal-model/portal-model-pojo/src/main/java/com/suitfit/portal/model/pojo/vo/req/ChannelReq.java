package com.suitfit.portal.model.pojo.vo.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChannelReq {
    private Long id;
    @NotBlank
    private String channelCode;
    @NotBlank
    private String channelName;
    @NotBlank
    private String channelUrl;
    @NotBlank
    private String appCode;
    private Integer state;
}
