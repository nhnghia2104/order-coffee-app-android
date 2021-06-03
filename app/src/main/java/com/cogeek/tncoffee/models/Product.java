package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.MyConfig;
import com.cogeek.tncoffee.utils.NumberHelper;
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

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return MyConfig.self().BASE_URL + image;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return Double.parseDouble(price);
    }

    public Double getFinalPrice() {
        return Double.parseDouble(price) * ( 1 - Double.parseDouble(discount));
    }

    public String getFinalPriceToString() {
        return NumberHelper.getInstance().currencyFormat(Double.parseDouble(price) * ( 1 - Double.parseDouble(discount)));
    }

    public String getPriceToString() {
        return  NumberHelper.getInstance().currencyFormat(Double.parseDouble(price));
    }

    public Double getDiscount() {
        return Double.parseDouble(discount);
    }
}
