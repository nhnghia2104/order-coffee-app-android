package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

public class ShipmentDetails {
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

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getFullAddress() {
        return String.format("%s, %s", address, city);
    }

    public String getAddress() {
        return address;
    }
}
