package com.cogeek.tncoffee.ui.menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cogeek.tncoffee.models.Product;

public class ItemViewModel extends ViewModel {
    private final MutableLiveData<Product> selectedItem = new MutableLiveData<>();
    public void selectItem(Product item) {
        selectedItem.setValue(item);
    }
    public LiveData<Product> getSelectedItem() {
        return selectedItem;
    }
}
