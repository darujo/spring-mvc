package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.darujo.model.Basket;
import ru.darujo.repository.BasketRepository;

import java.util.Optional;

@Service
public class BasketService {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    private BasketRepository basketRepository;

    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    public Optional<Basket> findById(long userId) {
        return basketRepository.findById(userId);
    }

    public Basket addProductToBasket(long userId, long productId) {
        return basketRepository.addProductToBasket(userId, productId);
    }

    public void deleteProduct(long userId, long productId) {
        basketRepository.delProductToBasket(userId, productId);
    }

    public void clearBasket(long userId) {
        basketRepository.clearBasket(userId);
    }
}
