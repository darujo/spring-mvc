package ru.darujo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class BasketDto {
    private String name;
    private List<BasketProductInformDto> productInformDtos = new ArrayList<>();
}
