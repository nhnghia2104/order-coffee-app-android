package com.cogeek.tncoffee.ui.cart;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cogeek.tncoffee.models.Cart;
import com.cogeek.tncoffee.models.ItemCart;
import com.cogeek.tncoffee.models_old.Item;
import com.cogeek.tncoffee.utils.SharedHelper;

public class CartViewModel extends AndroidViewModel {
    private MutableLiveData<Cart> mCart = new MutableLiveData<>();
    private Cart cart;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cart = new Cart();
        loadCart();
    }

    public LiveData<Cart> getCart() {
        return mCart;
    }

    public void addItem(ItemCart itemCart) {
        if (cart == null) {
            loadCart();
        }
        for (ItemCart item : cart.getItemList()) {
            if (itemCart.getId().equals(item.getId())) {
                item.increaseQuantity();
                saveCart();
                return;
            }
        }
        cart.addItem(itemCart);
        saveCart();
    }

    public void removeItem(String id) {
        for (ItemCart item : cart.getItemList()) {
            if (item.getId() == id) {
                if (item.decreaseQuantity() == 0) {
                    cart.removeItem(item);
                }
                break;
            }
        }
        saveCart();
    }

    public void deleteItemCart(ItemCart itemCart) {
        cart.removeItem(itemCart);
        saveCart();
    }

    private void saveCart() {
        SharedHelper.getInstance(getApplication()).setCart(cart);
        mCart.setValue(cart);
    }

    private void loadCart() {
        Cart temp = SharedHelper.getInstance(getApplication()).getCart();
        if (temp != null) {
            mCart.setValue(temp);
        }
    }

    public void clearCart() {
        cart.clear();
        saveCart();
    }
}
