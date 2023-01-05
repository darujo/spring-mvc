package ru.darujo.dto;

public class ProductDto {
    private Long id;
    private String title;
    private double price;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public ProductDto() {
    }

    public ProductDto(Long id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
