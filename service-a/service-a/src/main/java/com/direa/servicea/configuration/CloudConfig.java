package com.direa.servicea.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="test3")
public class CloudConfig {

    private String message;

    public String message() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
