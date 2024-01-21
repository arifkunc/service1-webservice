package com.oracle.assignment.webservice.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class RestConfig {
    @Bean
    public RestTemplateBuilder restTemplateBuilder(){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5));

        return restTemplateBuilder;
    }
}
