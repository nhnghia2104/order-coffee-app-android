package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ItemCart> itemList;

    public Cart() {
    }

    public List<ItemCart> getItemList() {
        return itemList;
    }

    public Double totalPrice() {
        Double total = 0.0;
        for (ItemCart item : itemList) {
            total += item.getFinalPrice();
        }
        return total;
    }

    public void setItemList(List<ItemCart> itemList) {
        this.itemList = itemList;
    }

    public void removeItem(ItemCart item) {
        this.itemList.remove(item);
    }

    public void addItem(ItemCart item) {
        this.itemList.add(item);
    }

    public void clear() {
        this.itemList.clear();
    }
}
