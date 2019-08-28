package com.suitfit.portal.model.pojo.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Exrickx
 */
@Data
@ConfigurationProperties(prefix = "portal.captcha")
public class CaptchaProperties {

    private List<String> filters = new ArrayList<>();

}
