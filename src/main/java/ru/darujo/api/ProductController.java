package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darujo.model.publicmodel.BuyerPublic;
import ru.darujo.model.publicmodel.ProductPublic;
import ru.darujo.service.ProductDao;

import java.util.Collection;
import java.util.Set;

@RestController
public class ProductController {
    private  ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public Collection<ProductPublic> index() {
        return productDao.findAll();
    }

    @GetMapping("/editProduct")
    public ProductPublic ProductEdit(long id) {
        return productDao.findById(id);
    }

    @PostMapping ("/saveProduct")
    public void ProductSave(ProductPublic product){
        productDao.saveOrUpdate(product);
    }
    @GetMapping("/deleteProduct")
    public void deleteProduct(long id) {
        productDao.deleteById(id);
    }

    @GetMapping("/findBuyerByProductId")
    public Set<BuyerPublic> findBuyerByProductId(long id) {
        return productDao.findBuyerByProductId(id);
    }


}