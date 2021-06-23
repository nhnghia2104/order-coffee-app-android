package com.cogeek.tncoffee.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.cogeek.tncoffee.models.Cart;
import com.cogeek.tncoffee.models.User;
import com.google.gson.Gson;

public class SharedHelper {
    private static final String USER_UID_KEY = "user_uid_key";
    private static final String USER_PROFILE_KEY = "user_profile_key";
    private static final String CART_KEY = "cart_key";
    private static SharedHelper instance;
    private final SharedPreferences preferences;

    public static synchronized SharedHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SharedHelper(context);
        }
        return instance;
    }
    private SharedHelper(Context context) {
        this.preferences = context.getSharedPreferences("myApp", Context.MODE_PRIVATE);
    }

    public void setUserUidKey(String uidKey) {
        preferences.edit().putString(USER_UID_KEY, uidKey).commit();
    }

    public String getUserUidKey() {
        return preferences.getString(USER_UID_KEY,"");
    }

    public void setUserProfile(User user) {
        Gson gson = new Gson();
        preferences.edit().putString(USER_PROFILE_KEY, gson.toJson(user)).commit();
    }

    public User getUserProfile() {
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString(USER_PROFILE_KEY, ""), User.class);
    }

    public void setCart(Cart cart) {
        Gson gson = new Gson();
        preferences.edit().putString(CART_KEY, gson.toJson(cart)).commit();
    }

    public Cart getCart() {
        Gson gson = new Gson();
        Log.e("cart", preferences.getString(CART_KEY,""));
        return gson.fromJson(preferences.getString(CART_KEY, ""), Cart.class);
    }

    public void logout() {
        preferences.edit().clear().apply();
    }
}
