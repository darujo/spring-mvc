package ru.darujo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.darujo.convertor.ProductConvertor;
import ru.darujo.dto.ProductDto;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.service.ProductService;

import java.math.BigDecimal;

@RestController()
@RequestMapping("/v1/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/{id}")
    public ProductDto ProductEdit(@PathVariable long id) {
        return ProductConvertor.getProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден")));
    }

    @PostMapping("")
    public ProductDto ProductSave(@RequestBody ProductDto productDto) {
        return ProductConvertor.getProductDto(productService.saveProduct(ProductConvertor.getProduct(productDto)));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("")
    public Page<ProductDto> productsMinMax(@RequestParam(required = false) BigDecimal min,
                                           @RequestParam(required = false) BigDecimal max,
                                           @RequestParam(defaultValue = "1") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return productService.findProducts(min, max, page, size).map(ProductConvertor::getProductDto);
    }

}