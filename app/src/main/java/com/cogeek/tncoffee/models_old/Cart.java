package com.cogeek.tncoffee.models_old;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Double total;
    private List<CartDetail> items;

    public Cart() {
        this.total = 0.0;
        this.items = new ArrayList<>();
    }

    public Cart(Double total, List<CartDetail> items) {
        this.total = total;
        this.items = items;
    }

    public List<CartDetail> getDetails() {
        return items;
    }

    public void setDetails(List<CartDetail> items) {
        this.items = items;
    }

    public Double getTotal() {
        return total;
    }

    public void addItem(CartDetail item) {
        this.items.add(item);
        this.total += item.getPrice();
    }

    public void removeItem(int index) {
        this.items.remove(index);
    }
}
