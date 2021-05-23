package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<ItemCart> itemList;
    private SharedHelper sharedHelper;

    public Cart(SharedHelper sharedHelper) {
        itemList = new ArrayList<>();
        this.sharedHelper = sharedHelper;
    }

    public void saveCart() {

    }

    public void loadCart() {

    }

    public void clearCart() {
        itemList.clear();
        saveCart();
    }

    public void addItem(ItemCart itemCart) {
        for (ItemCart item : itemList) {
            if (item.getId() == itemCart.getId()) {
                item.incQty();
                saveCart();
                return;
            }
        }
        itemList.add(itemCart);
        saveCart();
    }

    public void removeItem(String id) {
        for (ItemCart item : itemList) {
            if (item.getId() == id) {
                if (item.decQty() == 0) {
                    itemList.remove(item);
                }
                break;
            }
        }
        saveCart();
    }

    public Double totalPrice() {
        Double total = 0.0;
        for (ItemCart item : itemList) {
            total += item.getFinalPrice();
        }
        return total;
    }

    public List<ItemCart> getItemList() {
        return itemList;
    }
}
