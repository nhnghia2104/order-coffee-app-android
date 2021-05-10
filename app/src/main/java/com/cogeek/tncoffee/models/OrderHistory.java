package com.cogeek.tncoffee.models;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private int total;
    private List<OrderHistoryDetail> details;

    public OrderHistory() {
        this.total = 0;
        this.details = new ArrayList<>();
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<OrderHistoryDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderHistoryDetail> details) {
        this.details = details;
    }

    public OrderHistory(int total, List<OrderHistoryDetail> details) {
        this.total = total;
        this.details = details;
    }

    public void addItem(OrderHistoryDetail item) {
        this.details.add(item);
        //this.total += item.getPrice();
    }
}
