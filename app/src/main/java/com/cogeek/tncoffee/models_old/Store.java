package com.cogeek.tncoffee.models_old;

public class Store {
    String name;
    String address;
    Double distance;
    String imageUrl;

    public Store(String name, String address, Double distance, String imageUrl) {
        this.name = name;
        this.address = address;
        this.distance = distance;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
