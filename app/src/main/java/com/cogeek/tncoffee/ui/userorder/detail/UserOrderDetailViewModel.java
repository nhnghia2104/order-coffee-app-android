package com.cogeek.tncoffee.ui.userorder.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cogeek.tncoffee.models.Order;
import com.cogeek.tncoffee.models.Product;

public class UserOrderDetailViewModel extends ViewModel {
    private final MutableLiveData<Order> selectedItem = new MutableLiveData<>();
    public void selectItem(Order item) {
        selectedItem.setValue(item);
    }
    public LiveData<Order> getSelectedItem() {
        return selectedItem;
    }
}