package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.darujo.dto.ProductDto;
import ru.darujo.model.Product;
import ru.darujo.repository.ProductRepository;
import ru.darujo.repository.specifications.ProductsSpecifications;

import java.math.BigDecimal;
import java.util.*;

@Service
@Primary
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public ProductDto saveProduct(Product product) {
        return ProductDto.createProductDto(productRepository.save(product));
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


    public Page<ProductDto> findProducts(BigDecimal min, Double max, int page, int size){
        Specification<Product> specification = Specification.where(null);

        if (min !=null){
            specification = specification.and(ProductsSpecifications.priceGE(min));
        }
        if (max !=null){
            specification = specification.and(ProductsSpecifications.priceLE(max));
        }
       return  productRepository.findAll(specification,PageRequest.of(page - 1,size)).map(ProductDto::createProductDto);
    }
}
