package com.cogeek.tncoffee.models;

public class Notification {
    String title;
    String content;
    String image;
    long time;

    public Notification() {
    }

    public Notification(String title, String content, String image, int time) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
