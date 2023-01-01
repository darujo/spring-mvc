package ru.darujo.dto;

import lombok.Data;
import ru.darujo.model.OrderItem;

import java.io.Serializable;

/**
 * A DTO for the {@link OrderItem} entity
 */
@Data
public class OrderItemDto implements Serializable {
    private final long id;
    private final String name;
    private final double quantity;
    private final double price;
}