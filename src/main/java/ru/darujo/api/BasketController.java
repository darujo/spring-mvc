package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.convertor.BasketConvertor;
import ru.darujo.dto.BasketDto;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.model.User;
import ru.darujo.service.BasketService;
import ru.darujo.service.UserService;

import java.security.Principal;

@RestController()
@RequestMapping("/v1/baskets")

public class BasketController {
    private BasketService basketService;

    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public BasketDto getBasket(Principal principal) {
        User user = userService.findByUserName(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return BasketConvertor.getBasketDto(basketService.findById(user.getId()).orElseThrow(() -> new ResourceNotFoundException("Корзина не найден")));
    }

    @GetMapping("/clear")
    public void clearBasket(Principal principal) {
        User user = userService.findByUserName(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        basketService.clearBasket(user.getId());
    }

    @GetMapping("/add")
    public BasketDto ProductSave(Principal principal, @RequestParam long productId) {
        User user = userService.findByUserName(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return BasketConvertor.getBasketDto(basketService.addProductToBasket(user.getId(), productId));
    }

    @GetMapping("/delete")
    public void deleteProduct(Principal principal, @RequestParam long productId) {
        User user = userService.findByUserName(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        basketService.deleteProduct(user.getId(), productId);
    }
}
