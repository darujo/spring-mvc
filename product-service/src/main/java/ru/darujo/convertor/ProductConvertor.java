package ru.darujo.convertor;

import ru.darujo.dto.ProductDto;
import ru.darujo.model.Product;

public class ProductConvertor {
    public static ProductDto getProductDto(Product product){
        return new ProductDto(product.getId(),product.getTitle(), product.getPrice());
    }
    public static Product getProduct(ProductDto productDto){
        return new Product(productDto.getId(),productDto.getTitle(), productDto.getPrice(),null);
    }
}
