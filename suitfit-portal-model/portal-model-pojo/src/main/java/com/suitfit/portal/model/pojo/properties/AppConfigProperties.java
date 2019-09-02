package com.suitfit.portal.model.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "portal.app")
public class AppConfigProperties {
    private Map<String,String> channelUrls = new HashMap<>();
}
