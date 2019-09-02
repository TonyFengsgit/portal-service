package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("app_channel")
public class AppChannel extends BaseEntity {
    private String channelCode;
    private String channelName;
    private String channelUrl;
    private Integer state;
    private String appCode;
}
