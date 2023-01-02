package ru.darujo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketProductInform {

    private long productId;
    private double quantity;

}
