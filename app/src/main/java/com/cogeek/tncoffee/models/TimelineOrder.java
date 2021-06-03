package com.cogeek.tncoffee.models;

public class TimelineOrder {
    private String time;
    private String status_id;
    private String status;
    public TimelineOrder(String time, String status_id, String status) {
        this.time = time;
        this.status_id = status_id;
        this.status = status;
    }
}

