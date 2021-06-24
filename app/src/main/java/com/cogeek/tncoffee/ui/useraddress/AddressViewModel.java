package com.cogeek.tncoffee.ui.useraddress;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cogeek.tncoffee.models.Cart;

public class AddressViewModel extends AndroidViewModel {
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();

    public AddressViewModel(@NonNull Application application) {
        super(application);
        mIndex.setValue(-1);
    }

    public LiveData<Integer> getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex.setValue(index);
    }
}
