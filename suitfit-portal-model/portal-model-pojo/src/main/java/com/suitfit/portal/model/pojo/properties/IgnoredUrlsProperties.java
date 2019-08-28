package com.suitfit.portal.model.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Exrickx
 */
@Data
@ConfigurationProperties(prefix = "portal.ignored")
public class IgnoredUrlsProperties {

    private List<String> urls = new ArrayList<>();

    private List<String> path = new ArrayList<>();
}
