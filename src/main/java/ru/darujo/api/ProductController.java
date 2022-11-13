package ru.darujo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.darujo.model.Product;
import ru.darujo.service.ProductService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products")
    public Collection<Product> index() {
        return productService.getProducts().values();
    }

    @GetMapping("/editProduct")
    public Product ProductEdit(long id) {
        return productService.getProductForId(id);
    }

    @PostMapping ("/saveProduct")
    public void ProductSave(Product product){
        productService.saveProduct(product);
    }
    @GetMapping("/deleteProduct")
    public void deleteProduct(long id) {
        productService.delProductForId(id);
    }


}