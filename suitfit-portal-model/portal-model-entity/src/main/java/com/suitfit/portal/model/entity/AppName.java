package com.suitfit.portal.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.suitfit.framework.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("app_name")
public class AppName extends BaseEntity {
    private String appCode;
    private String appName;
}
