package com.cogeek.tncoffee.models;

import com.cogeek.tncoffee.utils.MyConfig;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private String uid;

    @SerializedName("gender")
    private String gender;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("phone")
    private String phone;

    @SerializedName("city")
    private String city;

    @SerializedName("address")
    private String address;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("shipping_address")
    private UserAddress userAddress;

    public String getUid() {
        return uid;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() { return firstName + " " + lastName;}

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getAvatar() {
        return MyConfig.self().getBASE_URL() + avatar;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddress userAddress) {
        this.userAddress = userAddress;
    }
}
