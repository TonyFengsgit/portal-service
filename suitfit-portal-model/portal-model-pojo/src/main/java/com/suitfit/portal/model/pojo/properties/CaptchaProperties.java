package com.suitfit.portal.model.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Exrickx
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "portal.captcha")
public class CaptchaProperties {

    private List<String> filters = new ArrayList<>();

}
