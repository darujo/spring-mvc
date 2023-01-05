package ru.darujo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.darujo.exceptions.ResourceNotFoundException;
import ru.darujo.service.ProductService;
import ru.darujo.soap.product.*;

@Endpoint
@Configuration
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.darujo.ru/soap/product";
    private ProductService productService;

    @Autowired
    public void setProductService (ProductService productService) {
        this.productService = productService;
    }



    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getProductByIdResponse(@RequestPayload GetProductByIdRequest
                                                   request) {

        GetProductByIdResponse response = new GetProductByIdResponse();
        ru.darujo.model.Product product = productService.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден"));
        Product productResp = new Product();
        productResp.setId(product.getId());
        productResp.setTitle(product.getTitle());
        productResp.setPrice(product.getPrice());
        response.setProduct(productResp);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductRequest")
    @ResponsePayload
    public GetAllProductResponse getAllProductResponse(@RequestPayload GetAllProductRequest
                                                      request) {

        GetAllProductResponse response = new GetAllProductResponse();
        productService.findAllProducts().forEach(product -> {
            Product productResp = new Product();
            productResp.setId(product.getId());
            productResp.setTitle(product.getTitle());
            productResp.setPrice(product.getPrice());
            response.getProducts().add(productResp);
        });
        return response;
    }
}
