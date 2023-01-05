package ru.darujo.dto;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BasketDto {
    private String name;
    private List<BasketProductInformDto> productInformDtos = new ArrayList<>();
    private BigDecimal price;

    public void setName(String name) {
        this.name = name;
    }

    public void setProductInformDtos(List<BasketProductInformDto> productInformDtos) {
        this.productInformDtos = productInformDtos;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public List<BasketProductInformDto> getProductInformDtos() {
        return productInformDtos;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BasketDto() {
    }
}
