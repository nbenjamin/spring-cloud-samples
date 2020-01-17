package com.nbenja.springcloud.springcloudvaultdemo.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("vaultprops")
public class ApplicationProperties {
    private String username;
    private String password;
}
