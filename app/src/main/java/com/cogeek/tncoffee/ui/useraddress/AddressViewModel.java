package com.cogeek.tncoffee.ui.useraddress;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cogeek.tncoffee.models.Cart;
import com.cogeek.tncoffee.models.UserAddress;
import com.cogeek.tncoffee.utils.SharedHelper;

public class AddressViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private MutableLiveData<Integer> cartSelected = new MutableLiveData<>();

    public AddressViewModel(@NonNull Application application) {
        super(application);
        mIndex.setValue(-1);
        UserAddress userAddress = SharedHelper.getInstance(application).getUserProfile().getUserAddress();
        if (userAddress != null) {
            cartSelected.setValue(userAddress.getAddressDefaultIndex());
        }
        else {
            cartSelected.setValue(-1);
        }
    }

    public LiveData<Integer> getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex.setValue(index);
    }

    public LiveData<Integer> getCartSelected() {
        return cartSelected;
    }

    public void setCartSelected(int index) {
        this.cartSelected.setValue(index);
    }
}
