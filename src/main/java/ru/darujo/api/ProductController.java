package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darujo.model.Product;
import ru.darujo.service.ProductDao;

import java.util.Collection;

@RestController
public class ProductController {
    private  ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public Collection<Product> index() {
        return productDao.findAll();
    }

    @GetMapping("/editProduct")
    public Product ProductEdit(long id) {
        return productDao.findById(id);
    }

    @PostMapping ("/saveProduct")
    public void ProductSave(Product product){
        productDao.saveOrUpdate(product);
    }
    @GetMapping("/deleteProduct")
    public void deleteProduct(long id) {
        productDao.deleteById(id);
    }


}