package ru.darujo.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.darujo.dto.BasketProductInformDto;
import ru.darujo.model.BasketProductInform;
import ru.darujo.service.ProductService;

@Component
public class BasketProductConvertor {
    private static ProductService productService;

    @Autowired
    public void setProductService(ProductService productServiceBeen) {
        productService = productServiceBeen;
    }

    public static BasketProductInformDto getBasketProductInformDto(BasketProductInform basketProductInform) {
        return new BasketProductInformDto(basketProductInform.getId(),
                productService.findById(basketProductInform.getId()).orElseThrow(() -> new RuntimeException("Продукт потерян")).getTitle(),
                basketProductInform.getQuantity());
    }

    public static BasketProductInform getBasketProductInform(BasketProductInformDto basketProductInform) {
        return new BasketProductInform(basketProductInform.getId(),
                basketProductInform.getQuantity());
    }
}
