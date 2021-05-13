package com.cogeek.tncoffee.ui.cart;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cogeek.tncoffee.models.Cart;
import com.cogeek.tncoffee.models.CartDetail;

public class CartViewModel extends AndroidViewModel {
    private MutableLiveData<Cart> mCart = new MutableLiveData<>();
    private Cart cart;
    public CartViewModel(@NonNull Application application) {
        super(application);
        cart = new Cart();
        mCart.setValue(cart);
    }
    public LiveData<Cart> getCart() {
        return mCart;
    }

    public void addItemToCart(CartDetail item) {
        cart.addItem(item);
        this.mCart.setValue(cart);
        Log.i("Item Added", item.toString());
    }
}
