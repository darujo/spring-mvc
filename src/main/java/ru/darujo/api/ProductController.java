package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.model.Product;
import ru.darujo.service.ProductService;

import java.util.List;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<Product> index() {
        return productService.findPage(0,10000);
    }

    @GetMapping("/editProduct")
    public Product ProductEdit(@RequestParam long id) {
        return productService.findById(id).orElseThrow(()->new ResourceNotFoundException("Продукт не найден"));
    }

    @PostMapping ("/saveProduct")
    public void ProductSave(Product product){
        productService.saveProduct(product);
    }
    @DeleteMapping("/product")
    public void deleteProduct(long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/findPage")
    public List<Product> findPage(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10")int size ){
        return productService.findPage(page,size);
    }
    @GetMapping("/productsMinMax")
    public List<Product> productsMinMax(@RequestParam(defaultValue = "0")double min,@RequestParam(defaultValue = "0")double max) {
        return productService.productsByPriceBetween(min,max);
    }

}