package com.cogeek.tncoffee.models;

public class Notification {
    String title;
    String body;
    String imageUrl;
    int iTime;

    public Notification(String title, String body, String imageUrl, int iTime) {
        this.title = title;
        this.body = body;
        this.imageUrl = imageUrl;
        this.iTime = iTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getiTime() {
        return iTime;
    }

    public void setiTime(int iTime) {
        this.iTime = iTime;
    }
}
