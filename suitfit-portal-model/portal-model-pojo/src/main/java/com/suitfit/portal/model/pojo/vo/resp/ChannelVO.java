package com.suitfit.portal.model.pojo.vo.resp;

import lombok.Data;

@Data
public class ChannelVO {
    private Long id;
    private String ChannelCode;
    private String channelName;
    private String channelUrl;
    private Integer state;
    private String appCode;
}
