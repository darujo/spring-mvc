package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.model.*;
import ru.darujo.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private BasketService basketService;
    @Autowired
    public void setBasketService(BasketService basketService){
        this.basketService = basketService;
    }
    private OrderRepository orderRepository;
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    private static ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        OrderService.productService = productService;
    }
    @Transactional
    public void createOrderToBasket(User user){
        Basket basket = basketService.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("Корзина не найдена"));
        Order order = new Order();
        order.setUser(user);
        List<OrderItem> orderItems = new ArrayList<>();

        for (BasketProductInform basketProductInform: basket.getProductInforms()) {
            Product product = productService.findById(basketProductInform.getProductId()).orElseThrow(() -> new RuntimeException("Продукт потерян"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(basketProductInform.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        basketService.clearBasket(user.getId());

    }

}
