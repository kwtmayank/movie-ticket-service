package com.mtb.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
public class ConfigProperties {


    @Bean
    @ConfigurationProperties(prefix = "app.genres")
    public Map<String, String> genreList() {
        return new HashMap<>();
    }

}
