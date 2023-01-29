package ru.darujo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.darujo.model.Basket;
import ru.darujo.model.BasketProductInform;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BasketService {
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
    @Value("&{redis.basket}")
    private String  basketPrefix;

    public Optional<Basket> findById(String username) {
        return Optional.of ((Basket) redisTemplate.opsForValue().get(basketPrefix + username ));
    }
    public void saveBasket(String username,Basket basket) {
        redisTemplate.opsForValue().set(basketPrefix + username , basket  );
    }

    public Basket addProductToBasket(String username, long productId) {
        Basket basket = findById(username).orElse(new Basket());
        BasketProductInform basketProductInform = null;
        for (int i = 0; i < basket.getProductInforms().size(); i++) {
            if (basket.getProductInforms().get(i).getProductId() == productId) {
                basketProductInform = basket.getProductInforms().get(i);
                break;
            }
        }
        if (basketProductInform == null) {
            basketProductInform = new BasketProductInform(productId, BigDecimal.ONE);
            basket.getProductInforms().add(basketProductInform);
        } else {
            basketProductInform.setQuantity(basketProductInform.getQuantity().add(BigDecimal.ONE));
        }
        saveBasket(username,basket);
        return basket;
    }

    public void deleteProduct(String username, long productId) {
        Basket basket = findById(username).orElseThrow(()->new RuntimeException("Нет корзины"));
        List<BasketProductInform> productInforms = basket.getProductInforms();
        for (int i = 0; i < productInforms.size(); i++) {
            BasketProductInform productInform = productInforms.get(i);
            if (productInform.getProductId() == productId) {
                productInform.setQuantity(productInform.getQuantity().subtract(BigDecimal.ONE ));
                if (productInform.getQuantity().equals(BigDecimal.ZERO) ) {
                    productInforms.remove(productInform);
                }
                break;
            }
        }
        saveBasket(username,basket);

    }

    public void clearBasket(String username) {
        saveBasket(username,new Basket());
    }
}
