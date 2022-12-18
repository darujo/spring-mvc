package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.convertor.BasketConvertor;
import ru.darujo.dto.BasketDto;
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

    @GetMapping("/{id}")
    public BasketDto getBasket(@PathVariable long id) {
        return BasketConvertor.getBasketDto(basketService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Корзина не найден")));
    }

    @GetMapping("/add")
    public BasketDto ProductSave(@RequestParam long basketId, @RequestParam long productId) {
        return BasketConvertor.getBasketDto(basketService.addProductToBasket(basketId, productId));
    }

    @GetMapping("/delete/{basketId}")
    public void deleteProduct(@PathVariable long basketId, @RequestParam long productId) {
        basketService.deleteProduct(basketId, productId);
    }
}
