package ru.darujo.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.darujo.dto.BasketProductInformDto;
import ru.darujo.model.BasketProductInform;
import ru.darujo.model.Product;
import ru.darujo.service.ProductService;

@Component
public class BasketProductConvertor {
    private static ProductService productService;

    @Autowired
    public void setProductService(ProductService productServiceBeen) {
        productService = productServiceBeen;
    }

    public static BasketProductInformDto getBasketProductInformDto(BasketProductInform basketProductInform) {
        Product product = productService.findById(basketProductInform.getId()).orElseThrow(() -> new RuntimeException("Продукт потерян"));

        return new BasketProductInformDto(basketProductInform.getId(),
                                          product.getTitle(),
                                          basketProductInform.getQuantity(),
                                          product.getPrice(),
                                   product.getPrice() * basketProductInform.getQuantity());
    }

    public static BasketProductInform getBasketProductInform(BasketProductInformDto basketProductInform) {
        return new BasketProductInform(basketProductInform.getId(),
                basketProductInform.getQuantity());
    }
}
