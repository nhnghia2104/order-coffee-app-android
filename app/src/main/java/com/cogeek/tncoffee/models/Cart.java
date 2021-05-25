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

    public Double totalPrice() {
        Double total = 0.0;
        for (ItemCart item : itemList) {
            total += item.getFinalPrice();
        }
        return total;
    }

    public String totalPriceToString() {
        return NumberHelper.getInstance().currencyFormat(totalPrice());
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
