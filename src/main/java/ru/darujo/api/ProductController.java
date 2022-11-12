package ru.darujo.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.darujo.model.Product;
import ru.darujo.model.ProductRepository;
import ru.darujo.service.ProductService;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;
    @GetMapping("")
    public String index(Model model){
        model.addAttribute("products", productRepository.getProducts().values());
        return "index";
    }

    @GetMapping("/productEdit")
    public String ProductEdit(long id, Model model){
        Product product = productRepository.getProductForId(id);
        model.addAttribute("product",product);
        return "product";
    }

    @GetMapping("/productAdd")
    public String ProductAdd(Model model){
        model.addAttribute("product",productService.createEmptyProduct());
        return "product";
    }
    @PostMapping ("/")
    public String ProductSave(Product product, Model model){
        productService.saveProduct(product);
        return  index(model);
    }
}
