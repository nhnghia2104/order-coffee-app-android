package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("id")
    public String id;

    @SerializedName("items")
    public List<Product> items;

    @SerializedName("name")
    public String name;

    public Category(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public List<Product> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }
}
