package ru.darujo.convertor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.darujo.dto.BasketProductInformDto;
import ru.darujo.dto.ProductDto;
import ru.darujo.integration.ProductServiceIntegration;
import ru.darujo.model.BasketProductInform;

@Component
public class BasketProductConvertor {
    private static ProductServiceIntegration productServiceIntegration;

    @Autowired
    public void setProductServiceIntegration(ProductServiceIntegration productServiceIntegration) {
        this.productServiceIntegration = productServiceIntegration;
    }

    public static BasketProductInformDto getBasketProductInformDto(BasketProductInform basketProductInform) {
        ProductDto product = productServiceIntegration.findById(basketProductInform.getProductId());

        return new BasketProductInformDto(basketProductInform.getProductId(),
                                          product.getTitle(),
                                          basketProductInform.getQuantity(),
                                          product.getPrice(),
                                   product.getPrice() * basketProductInform.getQuantity());
    }

    public static BasketProductInform getBasketProductInform(BasketProductInformDto basketProductInform) {
        return new BasketProductInform(basketProductInform.getProductId(),
                basketProductInform.getQuantity());
    }
}
