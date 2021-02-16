package com.example.servicecompany.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.cors.CorsConfiguration;


@ConfigurationProperties(
        prefix = "project",
        ignoreUnknownFields = false
)
public class ProjectProperties {

    private final CorsConfiguration cors = new CorsConfiguration();

    public CorsConfiguration getCors() {
        return this.cors;
    }

}
