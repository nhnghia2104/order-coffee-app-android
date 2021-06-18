package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.MyConfig;
import com.cogeek.tncoffee.utils.NumberHelper;
import com.google.gson.annotations.SerializedName;

public class OrderDetail {
    @SerializedName("productId")
    private String productId;

    @SerializedName("productName")
    private String productName;

    @SerializedName("Image")
    private String image;

    @SerializedName("amount")
    private String amount;

    @SerializedName("price")
    private String price;

    @SerializedName("discount")
    private String discount;

    @SerializedName("total")
    private String total;

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getImage() {
        return MyConfig.self().BASE_URL + image;
    }

    public String getAmount() {
        return amount;
    }

    public String getPrice() {
        return price;
    }

    public String getDiscount() {
        return discount;
    }

    public String getTotal() {
        return total;
    }

    public String getSalePriceToString() {
        return NumberHelper.getInstance().currencyFormat(Double.parseDouble(price) * ( 1 - Double.parseDouble(discount)));
    }
}
