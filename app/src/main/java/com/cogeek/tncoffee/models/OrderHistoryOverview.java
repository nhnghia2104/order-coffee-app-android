package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.MyConfig;
import com.cogeek.tncoffee.utils.NumberHelper;
import com.google.gson.annotations.SerializedName;

public class OrderHistoryOverview {
    @SerializedName("id")
    private String id;

    @SerializedName("createAt")
    private String createAt;

    @SerializedName("status")
    private String status;

    @SerializedName("total")
    private String total;

    @SerializedName("tracking")
    private TrackingOrder tracking;

    @SerializedName("statusColor")
    private String statusColor;

    @SerializedName("statusName")
    private String statusName;

    @SerializedName("productInCart")
    private String productInCart;

    @SerializedName("productImage")
    private String productImage;

    @SerializedName("productName")
    private String productName;

    public String getId() {
        return id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getStatus() {
        return status;
    }

    public String getTotal() {
        return NumberHelper.getInstance().currencyFormat(Double.parseDouble(total));
    }

    public TrackingOrder getTracking() {
        return tracking;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public String getStatusName() {
        return statusName;
    }

    public int getProductInCart() {
        return Integer.parseInt(productInCart);
    }

    public String getProductImage() {
        return MyConfig.self().BASE_URL + productImage;
    }

    public String getProductName() {
        return productName;
    }
}
