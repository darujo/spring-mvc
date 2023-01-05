package ru.darujo.dto;


public class BasketProductInformDto {
    private long productId;
    private String productName;
    private double quantity;
    private double price;
    private double priceAll;

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriceAll(double priceAll) {
        this.priceAll = priceAll;
    }

    public long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getPriceAll() {
        return priceAll;
    }

    public BasketProductInformDto(long id, String name, double quantity, double price, double priceAll) {
        this.productId = id;
        this.productName = name;
        this.quantity = quantity;
        this.price = price;
        this.priceAll = priceAll;
    }

    public BasketProductInformDto() {
    }
}
