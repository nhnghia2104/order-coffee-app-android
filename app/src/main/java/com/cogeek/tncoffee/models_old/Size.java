package com.cogeek.tncoffee.models_old;

public enum Size {
    SMALL("Nhỏ"),
    MEDIUM("Vừa"),
    LARGE("Lớn");

    private String displayName;

    Size(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
