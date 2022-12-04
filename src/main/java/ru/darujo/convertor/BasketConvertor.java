package ru.darujo.convertor;

import ru.darujo.dto.BasketDto;
import ru.darujo.model.Basket;

public class BasketConvertor {
    public static Basket getBasket(BasketDto basketDTO) {
        Basket basket = new Basket();
        basket.setName(basketDTO.getName());
        basketDTO.getProductInformDtos().forEach(basketProductInformDto -> basket.getProductInforms().add(BasketProductConvertor.getBasketProductInform(basketProductInformDto)));
        return basket;
    }

    public static BasketDto getBasketDto(Basket basket) {
        BasketDto basketDto = new BasketDto();
        basketDto.setName(basket.getName());
        basket.getProductInforms().forEach(basketProductInformDto -> basketDto.getProductInformDtos().add(BasketProductConvertor.getBasketProductInformDto(basketProductInformDto)));
        return basketDto;
    }
}
