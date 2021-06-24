package com.cogeek.tncoffee.models_old;

import com.cogeek.tncoffee.models.Item;

public class CartDetail {
    private Item item;
    private Size size;
    private int quantity;
    private String note;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CartDetail(Item item, Size size, int quantity, String note) {
        this.item = item;
        this.size = size;
        this.quantity = quantity;
        this.note = note;
    }

    public int getPrice() {
        return quantity * item.getPrice();
    }

    public CartDetail() {
    }

    @Override
    public String toString() {
        return "CartDetail{" +
                "item=" + item.getName() +
                ", size='" + size + '\'' +
                ", quantity=" + quantity +
                ", note='" + note + '\'' +
                ", totalPrice='" + getPrice() + '\'' +
                '}';
    }
}
