package com.cogeek.tncoffee.models;

public class ItemCart {
    private String id;
    private String name;
    private String image;
    private Double price;
    private Double discount;
    private Double finalPrice;
    private int quantity;

    public ItemCart(String id, String name, String image, Double price, Double discount, int quantity) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.finalPrice = price * discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /* Increase quantity item */
    public void incQty() {
        this.quantity++;
    }

    /* Decrease quantity item */
    public int decQty() {
       return --this.quantity;
    }
}
