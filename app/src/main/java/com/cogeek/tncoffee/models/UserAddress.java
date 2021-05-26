package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserAddress {
    @SerializedName("array")
    private List<Address> addressList;

    @SerializedName("default")
    private int addressDefaultIndex;

    public List<Address> getAddressList() {
        return addressList;
    }

    public Address getUserAddressDefault() {
        return addressList.get(addressDefaultIndex);
    }

    public int getAddressDefaultIndex() {
        return addressDefaultIndex;
    }
}

