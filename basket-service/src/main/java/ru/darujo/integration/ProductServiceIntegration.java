package ru.darujo.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.darujo.dto.ProductDto;

import java.util.Optional;
@Component
public class ProductServiceIntegration {
    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Value("${urlservice.product}")
    private String productUrl;

    public Optional<ProductDto> findById(Long productId) {
        return Optional.ofNullable(restTemplate.getForObject(productUrl + "/" + productId,ProductDto.class));

    }
}
