package ru.darujo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:url.properties")
public class AppConfigBasket {
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
