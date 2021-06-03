package com.cogeek.tncoffee.models;

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
    private String tracking;

    @SerializedName("statusColor")
    private String statusColor;

    @SerializedName("statusName")
    private String statusName;

    @SerializedName("productInCart")
    private String productInCart;

    @SerializedName("productImage")
    private String productImage;

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
        return total;
    }

    public String getTracking() {
        return tracking;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getProductInCart() {
        return productInCart;
    }

    public String getProductImage() {
        return productImage;
    }
}
