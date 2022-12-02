package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.darujo.model.Product;
import ru.darujo.repository.ProductRepository;

import java.util.*;

@Service
@Primary
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
//    public List<Product> findAll() {
//        return productRepository.find();
//    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
    public List<Product> productsByPriceBetween(double min, double max ){
        return productRepository.findByPriceBetween( min, max == 0 ? Double.MAX_VALUE : max  );
    }

    public List<Product> findPage(int page, int size){
        List<Product> products = new ArrayList<>();
        productRepository.findPage(page,size).forEach(products::add);
        return  products;
    }
}
