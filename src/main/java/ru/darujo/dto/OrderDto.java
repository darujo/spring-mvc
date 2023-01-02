package ru.darujo.dto;

import lombok.Data;
import ru.darujo.model.Order;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Order} entity
 */
@Data
public class OrderDto implements Serializable {
    private final long id;
    private final List<OrderItemDto> orderItems;
}