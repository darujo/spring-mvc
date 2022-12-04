package ru.darujo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketProductInformDto {
    private long id;
    private String name;
    private double quantity;
}
