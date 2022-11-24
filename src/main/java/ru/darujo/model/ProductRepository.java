package ru.darujo.model;

import org.springframework.stereotype.Component;
import ru.darujo.model.publicmodel.ProductPublic;

import java.util.HashMap;
import java.util.Map;

@Component
public class ProductRepository {
    private final Map<Long,ProductPublic> products;

    public ProductRepository() {
        products = new HashMap<>() ;
    }

    public Map<Long,ProductPublic> getProducts() {
        return products;
    }

    public ProductPublic getProductForId(long id) {
        return products.get(id);

    }

    public void addProduct(ProductPublic product) {
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
