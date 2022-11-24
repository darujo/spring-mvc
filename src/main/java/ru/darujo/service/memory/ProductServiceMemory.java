package ru.darujo.service.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.darujo.model.ProductRepository;
import ru.darujo.model.publicmodel.ProductPublic;
import ru.darujo.service.ProductDao;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Random;

@Service
public class ProductServiceMemory implements ProductDao {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private final Random  random = new Random();
    public static long idSequence = 0L;
    public void createNewProduct() {
        ProductPublic product = new ProductPublic(++idSequence,"Product #" + idSequence, Math.abs(random.nextInt()) /1000f);
        productRepository.addProduct(product);

    }
    @Override
    public void saveOrUpdate(ProductPublic product){
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

    @Override
    public Collection<ProductPublic> findAll() {
        return productRepository.getProducts().values();
    }

    @Override
    public ProductPublic findById(long id) {
        return productRepository.getProductForId(id);
    }
    @Override
    public void deleteById(long id){
        productRepository.deleteProductForId(id);
    }
}
