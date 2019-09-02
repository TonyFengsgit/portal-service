package com.suitfit.portal.model.pojo.criteria;

import lombok.Data;


@Data
public class ChannelQueryCriteria {
    private String channelCode;
    private String appCode;
    private Integer state;
}
