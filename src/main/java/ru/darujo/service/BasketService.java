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
    public void setProductService(ProductService productService){
        this.productService =productService;
    }

    private BasketRepository basketRepository;
    @Autowired
    public void setBasketRepository(BasketRepository basketRepository){
        this.basketRepository =basketRepository;
    }
    public Optional<Basket> findById(long basketId) {
        return basketRepository.findById(basketId);
    }
    public Basket addProductToBasket(long basketId,long productId){
        return basketRepository.addProductToBasket(basketId,productId);
    }

    public void deleteProduct(long basketId, long productId) {
        basketRepository.delProductToBasket(basketId,productId);
    }

    public void clearBasket(long id) {
        basketRepository.clearBasket( id);
    }
}
