package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.NumberHelper;
import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ItemCart> itemList;

    public Cart() {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
    }

    public List<ItemCart> getItemList() {
        return itemList;
    }

    public Double totalCartPrice() {
        Double totalCart = 0.0;
        for (ItemCart item : itemList) {
            totalCart += item.getPrice() * item.getQuantity() * ( 1 - item.getDiscount());
        }
        return totalCart;
    }

    public String totalCartPriceToString() {
        return NumberHelper.getInstance().currencyFormat(totalCartPrice());
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
