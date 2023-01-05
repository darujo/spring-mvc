package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.service.OrderService;

@RestController()
@RequestMapping("/v1/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/save")
    public void saveOrder(@RequestHeader String username) {
        orderService.createOrderToBasket(username);
    }

}
