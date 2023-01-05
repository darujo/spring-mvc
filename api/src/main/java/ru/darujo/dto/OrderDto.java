package ru.darujo.dto;

import java.io.Serializable;
import java.util.List;

public class OrderDto implements Serializable {
    private final long id;
    private final List<OrderItemDto> orderItems;

    public OrderDto(long id, List<OrderItemDto> orderItems) {
        this.id = id;
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

}