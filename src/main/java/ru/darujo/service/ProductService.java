package ru.darujo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.darujo.model.Product;
import ru.darujo.model.ProductRepository;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Random  random = new Random();
    public static long idSequence = 0L;
    public void createNewProduct() {
        Product product = new Product(++idSequence,"Product #" + idSequence, Math.abs(random.nextInt()) /1000f);
        productRepository.addProduct(product);
        System.out.println(product.getPrice());

    }
    public Product createEmptyProduct() {
        return new Product();
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


}
