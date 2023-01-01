package ru.darujo.convertor;

import ru.darujo.dto.BasketDto;
import ru.darujo.dto.BasketProductInformDto;
import ru.darujo.model.Basket;

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
        double price = 0;
        for (BasketProductInformDto productInformDto :basketDto.getProductInformDtos()) {
            price += productInformDto.getPriceAll();
        }
        basketDto.setPrice(price);
        return basketDto;
    }
}
