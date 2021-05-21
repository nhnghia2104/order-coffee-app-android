package com.cogeek.tncoffee.ui.menu;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.cogeek.tncoffee.models_old.Cart;
import com.cogeek.tncoffee.models_old.CartDetail;

public class MenuViewModel extends AndroidViewModel {
    private MutableLiveData<Cart> mCart = new MutableLiveData<>();
    private Cart cart;
    public MenuViewModel(@NonNull Application application) {
        super(application);
        cart = new Cart();
        mCart.setValue(cart);
    }

    public MutableLiveData<Cart> getCart() {
        return mCart;
    }

    public void addItemToCart(CartDetail item) {
        cart.addItem(item);
        this.mCart.setValue(cart);
        Log.i("Item Added", item.toString());
    }
}
