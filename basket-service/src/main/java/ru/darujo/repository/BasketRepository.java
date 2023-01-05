package ru.darujo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.darujo.model.Basket;
import ru.darujo.model.BasketProductInform;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class BasketRepository {

    private Basket basket;

    @Autowired
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Optional<Basket> findById(long userId) {
        return Optional.of(basket);
    }

    public Basket addProductToBasket(long userId, long productId) {
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
        return basket;
    }

    public void delProductToBasket(long userId, long productId) {
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
    }

    public void clearBasket(long userId) {
        basket.getProductInforms().clear();
    }
}
