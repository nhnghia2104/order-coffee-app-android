package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public final class Product {
    @SerializedName("ID")
    private String id;

    @SerializedName("CategoryId")
    private String categoryId;

    @SerializedName("Category")
    private String category;

    @SerializedName("Description")
    private String description;

    @SerializedName("Image")
    private String image;

    @SerializedName("Name")
    private String name;

    @SerializedName("Price")
    private  String price;

    @SerializedName("admin")
    private  String admin;

    @SerializedName("dateCreated")
    private  String dateCreated;

    @SerializedName("discount")
    private  String discount;

    @SerializedName("inventory")
    private  String inventory;

    @SerializedName("status")
    private  String status;

    @SerializedName("view")
    private  String view;

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return "http://10.0.2.2:2104/01/" + image;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return Double.parseDouble(price);
    }

    public Double getFinalPrice() {
        return Double.parseDouble(price) * Double.parseDouble(discount);
    }

    public Double getDiscount() {
        return Double.parseDouble(discount);
    }
}
