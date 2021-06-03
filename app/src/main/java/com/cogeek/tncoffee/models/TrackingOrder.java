package com.cogeek.tncoffee.models;

public class TrackingOrder {
    private String time;
    private String status_id;
    private String status;
    public TrackingOrder(String time, String status_id, String status) {
        this.time = time;
        this.status_id = status_id;
        this.status = status;
    }
}
