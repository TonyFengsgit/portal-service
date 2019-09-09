package com.suitfit.portal.model.entity;



import lombok.Data;

@Data
public class ChannelTime {

    //条件查询实体类
    //渠道
    private String channel;

    //时间
    private String  time;

    //当前页
    private Long current;

    //总页数
    private Long size;

}
