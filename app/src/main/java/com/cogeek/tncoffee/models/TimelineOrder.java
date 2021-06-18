package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

public class TimelineOrder {
    @SerializedName("time")
    private String time;
    @SerializedName("status_id")
    private String status_id;
    @SerializedName("status")
    private String status;
    public TimelineOrder(String time, String status_id, String status) {
        this.time = time;
        this.status_id = status_id;
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public String getStatus_id() {
        return status_id;
    }

    public String getStatus() {
        return status;
    }
}

