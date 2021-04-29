package com.cogeek.tncoffee.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int total;
    private List<CartDetail> details;

    public Cart() {
        this.total = 0;
        this.details = new ArrayList<>();
    }

    public Cart(int total, List<CartDetail> details) {
        this.total = total;
        this.details = details;
    }

    public List<CartDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CartDetail> details) {
        this.details = details;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void addItem(CartDetail item) {
        this.details.add(item);
        this.total += item.getPrice();
    }
}
