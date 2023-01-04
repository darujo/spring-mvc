package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.darujo.dto.BasketDto;
import ru.darujo.dto.BasketProductInformDto;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.integration.BasketServiceIntegration;
import ru.darujo.model.*;
import ru.darujo.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private BasketServiceIntegration basketServiceIntegration;
    @Autowired
    public void setBasketServiceIntegration(BasketServiceIntegration basketServiceIntegration){
        this.basketServiceIntegration = basketServiceIntegration;
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
        BasketDto basket = basketServiceIntegration.getBasket();
        Order order = new Order();
        order.setUser(user);
        List<OrderItem> orderItems = new ArrayList<>();

        for (BasketProductInformDto basketProductInformDto: basket.getProductInformDtos()) {
            Product product = productService.findById(basketProductInformDto.getProductId()).orElseThrow(() -> new RuntimeException("Продукт потерян"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(product.getId());
            orderItem.setQuantity(basketProductInformDto.getQuantity());
            orderItem.setPrice(product.getPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);
        basketServiceIntegration.clearBasket();

    }

}
