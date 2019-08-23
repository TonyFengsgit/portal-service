package com.suitfit.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: PortalApplication
 * @description:
 * @author: Chonzi.xiao
 * @create: 2019-08-12 16:43
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.suitfit.*")
public class PortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
