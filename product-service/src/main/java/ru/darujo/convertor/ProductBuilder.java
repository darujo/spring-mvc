package ru.darujo.convertor;

import ru.darujo.dto.ProductDto;
import ru.darujo.model.Product;

import java.math.BigDecimal;

public class ProductBuilder {
    private Long id;
    private String title;
    private BigDecimal price;
    public ProductBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public ProductBuilder setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public static ProductBuilder createProduct () {
        return new ProductBuilder();
    }
    public ProductDto getProductDto(){
        return new ProductDto(id,title,price);
    }
    public Product getProduct(){
        return new Product(id,title, price,null);
    }
}
