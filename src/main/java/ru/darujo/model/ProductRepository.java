package ru.darujo.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {
    private final Map<Long,Product> products;

    public ProductRepository() {
        products = new HashMap<>() ;
    }

    public Map<Long,Product> getProducts() {
        return products;
    }

    public Product getProductForId(long id) {
        return products.get(id);

    }

    public void addProduct(Product product) {
        products.put(product.getId(),product);
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + products;
    }

    public void deleteProductForId(long id) {
        products.remove(id);

    }
}
