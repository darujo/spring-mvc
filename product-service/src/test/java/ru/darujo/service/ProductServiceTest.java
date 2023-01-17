package ru.darujo.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.darujo.dto.ProductDto;
import ru.darujo.model.Product;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProductServiceTest {
    @Autowired
    ProductService productService;
    @Autowired
    WebTestClient webTestClient;
    @Test
    void findById() {
        Product product = new Product();
        product.setTitle("TestProduct");
        product.setPrice(BigDecimal.valueOf(1000));
        product = productService.saveProduct(product);

        ProductDto productByHttp = webTestClient.get()
                .uri("/v1/products/" + product.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        Assertions.assertEquals(product.getId(), productByHttp.getId());
        Assertions.assertEquals(product.getTitle(), productByHttp.getTitle());
        Assertions.assertEquals(product.getPrice().compareTo( productByHttp.getPrice()),0);
    }

    @Test
    void saveProduct() {
    }

    @Test
    void deleteProduct() {
    }
    @Test
    void testFindByIdNotFound() {
        Product product = new Product();
        product.setTitle("TestProduct");
        product.setPrice(BigDecimal.valueOf(1000));
        product = productService.saveProduct(product);
        Long productId = product.getId();
        productService.deleteProduct(productId);

        webTestClient.get()
                .uri("v1/products/" + productId)
                .exchange()
                .expectStatus().isNotFound();
    }
}