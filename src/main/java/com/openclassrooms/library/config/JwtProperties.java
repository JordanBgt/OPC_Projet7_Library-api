package com.openclassrooms.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private Integer expirationMS;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String jwtSecret) {
        this.secret = jwtSecret;
    }

    public Integer getExpirationMS() {
        return expirationMS;
    }

    public void setExpirationMS(Integer jwtExpirationMS) {
        this.expirationMS = jwtExpirationMS;
    }
}

