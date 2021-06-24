package com.cogeek.tncoffee.models_old;

import com.cogeek.tncoffee.models.Item;

import java.util.List;

public class Category {
    String name;
    List<Item> items;

    public Category() {
    }

    public Category(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
