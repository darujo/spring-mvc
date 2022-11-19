package ru.darujo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.darujo.model.Product;
import ru.darujo.model.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Random  random = new Random();
    public static long idSequence = 0L;
    public void createNewProduct() {
        Product product = new Product(++idSequence,"Product #" + idSequence, Math.abs(random.nextInt()) /1000f);
        productRepository.addProduct(product);

    }
    public void saveProduct(Product product){
        if (product.getId()== 0L){
            product.setId(++idSequence);
        }
        productRepository.addProduct(product);
    }
    @PostConstruct
    public void init(){
        for (int i = 0; i < 5; i++) {
            createNewProduct();
        }
    }


    public Map<Long,Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProductForId(long id) {
        return productRepository.getProductForId(id);
    }
    public void delProductForId(long id){
        productRepository.deleteProductForId(id);
    }
}
