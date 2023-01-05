package ru.darujo.convertor;

import ru.darujo.dto.BasketDto;
import ru.darujo.dto.BasketProductInformDto;
import ru.darujo.model.Basket;

import java.math.BigDecimal;

public class BasketConvertor {
    public static Basket getBasket(BasketDto basketDto) {
        Basket basket = new Basket();
        basket.setName(basketDto.getName());
        basketDto.getProductInformDtos().forEach(basketProductInformDto -> basket.getProductInforms().add(BasketProductConvertor.getBasketProductInform(basketProductInformDto)));
        return basket;
    }

    public static BasketDto getBasketDto(Basket basket) {
        BasketDto basketDto = new BasketDto();
        basketDto.setName(basket.getName());
        basket.getProductInforms().forEach(basketProductInformDto -> basketDto.getProductInformDtos().add(BasketProductConvertor.getBasketProductInformDto(basketProductInformDto)));
        BigDecimal price = BigDecimal.ZERO;
        for (BasketProductInformDto productInformDto :basketDto.getProductInformDtos()) {
            price.add(productInformDto.getPriceAll());
        }
        basketDto.setPrice(price);
        return basketDto;
    }
}
