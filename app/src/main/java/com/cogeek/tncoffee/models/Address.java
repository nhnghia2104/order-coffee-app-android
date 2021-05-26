package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("email")
    private String email;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("city")
    private String city;

    @SerializedName("address")
    private String address;

    @SerializedName("phone")
    private String phone;

}
