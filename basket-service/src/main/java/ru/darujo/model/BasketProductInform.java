package ru.darujo.model;

import java.math.BigDecimal;

public class BasketProductInform {

    private long productId;
    private BigDecimal quantity;

    public long getProductId() {
        return productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BasketProductInform(long productId, BigDecimal quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
