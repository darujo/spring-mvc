package ru.darujo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductDto {
    private Long id;
    private String title;
    private double price;
}
