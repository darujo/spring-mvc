package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.convertor.BasketConvertor;
import ru.darujo.dto.BasketDto;
import ru.darujo.dto.UserDto;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.service.BasketService;
@RestController()
@RequestMapping("/v1/baskets")
public class BasketController {
    private BasketService basketService;

    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("")
    public BasketDto getBasket(@RequestHeader String username) {
        return BasketConvertor.getBasketDto(basketService.findById(username).orElseThrow(() -> new ResourceNotFoundException("Корзина не найден")));
    }

    @GetMapping("/clear")
    public void clearBasket(@RequestHeader String username) {
        basketService.clearBasket(username);
    }

    @GetMapping("/add")
    public BasketDto ProductSave(@RequestHeader String username,@RequestParam long productId) {
        return BasketConvertor.getBasketDto(basketService.addProductToBasket(username, productId));
    }

    @GetMapping("/delete")
    public void deleteProduct(@RequestHeader String username,@RequestParam long productId) {
        basketService.deleteProduct(username, productId);
    }
}
