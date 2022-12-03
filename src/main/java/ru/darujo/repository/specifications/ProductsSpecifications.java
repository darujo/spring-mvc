package ru.darujo.repository.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.darujo.model.Product;

import java.math.BigDecimal;

public class ProductsSpecifications {
    public static Specification<Product> priceGE(BigDecimal price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price));
    }
    public static Specification<Product> priceLE(double price){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.le(root.get("price"),price));
    }
    public static Specification<Product> titleLike(String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"),String.format("%%%s%%",title)));
    }
}
