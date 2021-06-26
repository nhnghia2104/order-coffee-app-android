package com.cogeek.tncoffee.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserAddress {
    @SerializedName("array")
    private List<Address> addressList;

    @SerializedName("default")
    private int addressDefaultIndex;

    public UserAddress() {
        this.addressList = new ArrayList<>();
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public Address getUserAddressDefault() {
        return addressList.get(addressDefaultIndex);
    }

    public int getAddressDefaultIndex() {
        return addressDefaultIndex;
    }

    public void setDefaultIndex(int idx) {
        this.addressDefaultIndex = idx;
    }

    public void updateAddress(Address address, int index) {
        addressList.get(index).setAddress(address.getAddress());
        addressList.get(index).setCity(address.getCity());
        addressList.get(index).setFirstName(address.getFirstName());
        addressList.get(index).setLastName(address.getLastName());
        addressList.get(index).setEmail(address.getEmail());
        addressList.get(index).setPhone(address.getPhone());
    }

}

