package ru.darujo.convertor;

import ru.darujo.dto.ProductDto;
import ru.darujo.model.Product;

public class ProductConvertor {
    public static ProductDto getProductDto(Product product){
        return ProductBuilder
                .createProduct()
                .setId(product.getId())
                .setTitle(product.getTitle())
                .setTitle(product.getTitle())
                .getProductDto();
    }
    public static Product getProduct(ProductDto productDto){
        return ProductBuilder
                .createProduct()
                .setId(productDto.getId())
                .setPrice(productDto.getPrice())
                .setTitle(productDto.getTitle())
                .getProduct();
    }
}
