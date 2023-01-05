package ru.darujo.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.darujo.dto.BasketDto;

import java.util.Optional;

@Component
public class BasketServiceIntegration {
    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${urlservice.basket}")
    private String basketUrl;

    public Optional<BasketDto> getBasket() {
        return Optional.ofNullable(restTemplate.getForObject(basketUrl,BasketDto.class));
    }

    public void clearBasket() {
        restTemplate.getForObject(basketUrl + "/clear", Void.class );
    }
}
