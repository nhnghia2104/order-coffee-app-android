package com.cogeek.tncoffee.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedHelper {
    private static final String USER_UID_KEY = "user_uid_key";
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

    public void logout() {
        preferences.edit().clear().apply();
    }
}
