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
@CrossOrigin("*")
public class BasketController {
    private BasketService basketService;

    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("")
    public BasketDto getBasket() {
        UserDto user = new UserDto(1,"111");
        return BasketConvertor.getBasketDto(basketService.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Корзина не найден")));
    }

    @GetMapping("/clear")
    public void clearBasket() {
        UserDto user = new UserDto(1,"111");
        basketService.clearBasket(user.getId());
    }

    @GetMapping("/add")
    public BasketDto ProductSave(@RequestParam long productId) {
        UserDto user = new UserDto(1,"111");
        return BasketConvertor.getBasketDto(basketService.addProductToBasket(user.getId(), productId));
    }

    @GetMapping("/delete")
    public void deleteProduct(@RequestParam long productId) {
        UserDto user = new UserDto(1,"111");
        basketService.deleteProduct(user.getId(), productId);
    }
}
