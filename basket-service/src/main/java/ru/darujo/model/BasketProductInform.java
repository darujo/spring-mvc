package ru.darujo.model;

public class BasketProductInform {

    private long productId;
    private double quantity;

    public long getProductId() {
        return productId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BasketProductInform(long productId, double quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
