package com.cogeek.tncoffee.models;

public class User {
    String email;
    String name;
    String avatarUrl;
    String phone;
    int point;
    int birthday;
    boolean gender; // true: male, false : female

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String name, String avatarUrl, String phone, int point, int birthday, boolean gender) {
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.phone = phone;
        this.point = point;
        this.birthday = birthday;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }
<<<<<<< Updated upstream
=======



>>>>>>> Stashed changes
}
