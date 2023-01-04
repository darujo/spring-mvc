package ru.darujo.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.darujo.dto.ProductDto;
import ru.darujo.exceptions.ResourceNotFoundException;

import java.util.Optional;

@Component
public class ProductServiceIntegration {
    private WebClient productWebClient;

    @Autowired
    public void setProductWebClient(WebClient productWebClient) {
        this.productWebClient = productWebClient;
    }

    public ProductDto findById(Long productId) {
        return productWebClient
                .get()
                .uri("/" + productId)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),clientResponse -> Mono.error(new ResourceNotFoundException("Продукт не найден"))) // TODO обработать ошибки отдельно
                .bodyToMono(ProductDto.class)
                .block();

    }
}
