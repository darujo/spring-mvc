package ru.darujo.dto;

import java.io.Serializable;

public class OrderItemDto implements Serializable {
    private final long id;
    private final String name;
    private final double quantity;
    private final double price;

    public OrderItemDto(long id, String name, double quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}