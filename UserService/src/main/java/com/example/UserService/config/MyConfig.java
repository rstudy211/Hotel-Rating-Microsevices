package com.example.UserService.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableFeignClients
public class MyConfig {
    @Bean
    @LoadBalanced
    public RestTemplate RestTemplate(){
        return new RestTemplate();
    }
}
