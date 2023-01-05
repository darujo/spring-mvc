package ru.darujo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySources({@PropertySource("secret.properties"),@PropertySource("url.properties")})
public class AppConfig {
    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }
}
