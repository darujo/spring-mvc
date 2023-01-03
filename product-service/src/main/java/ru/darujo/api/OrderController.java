package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.model.User;
import ru.darujo.service.OrderService;
import ru.darujo.service.UserService;

import java.security.Principal;

@RestController()
@RequestMapping("/v1/orders")

public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/save")
    public void saveOrder(Principal principal) {
        User user = userService.findByUserName(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        orderService.createOrderToBasket(user);
    }

}
