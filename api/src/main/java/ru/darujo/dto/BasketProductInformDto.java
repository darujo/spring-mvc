package ru.darujo.dto;


import java.math.BigDecimal;

public class BasketProductInformDto {
    private long productId;
    private String productName;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal priceAll;

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPriceAll(BigDecimal priceAll) {
        this.priceAll = priceAll;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getPriceAll() {
        return priceAll;
    }

    public BasketProductInformDto(long id, String name, BigDecimal quantity, BigDecimal price, BigDecimal priceAll) {
        this.productId = id;
        this.productName = name;
        this.quantity = quantity;
        this.price = price;
        this.priceAll = priceAll;
    }

    public BasketProductInformDto() {
    }
}
