package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.NumberHelper;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("id")
    private String id;

    @SerializedName("createAt")
    private String createAt;

    @SerializedName("total")
    private String total;

    @SerializedName("paymentName")
    private String paymentName;

    @SerializedName("status")
    private String status;

    @SerializedName("details")
    private List<OrderDetail> orderDetailList;

    @SerializedName("tracking")
    private List<TrackingOrder> trackingOrderList;

    @SerializedName("timeline")
    private List<TimelineOrder> timelineOrderList;

    @SerializedName("shipping_address")
    private ShipmentDetails shipmentDetails;

    public String getId() {
        return id;
    }

    public String getCreateAt() {
        return createAt;
    }

    public String getCreatedFormat() {
        return NumberHelper.getInstance().dateFormat(createAt);
    }

    public String getTotal() {
        return NumberHelper.getInstance().currencyFormat(Double.parseDouble(total));
    }

    public String getPaymentName() {
        return paymentName;
    }

    public String getStatus() {
        return status;
    }

    public ShipmentDetails getShipmentDetails() {
        return shipmentDetails;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public List<TrackingOrder> getTrackingOrderList() {
        return trackingOrderList;
    }

    public List<TimelineOrder> getTimelineOrderList() {
        return timelineOrderList;
    }
}

