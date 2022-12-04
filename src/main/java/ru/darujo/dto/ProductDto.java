package ru.darujo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.darujo.model.Product;

@AllArgsConstructor
@Data
public class ProductDto {
    private long id;
    private String title;
    private double price;

    public static ProductDto createProductDto(Product product){
       return new ProductDto(product.getId(),product.getTitle(), product.getPrice());
    }

}
