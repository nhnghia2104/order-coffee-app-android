package com.cogeek.tncoffee;

import android.content.Context;

import com.cogeek.tncoffee.utils.SharedHelper;

import java.util.HashMap;
import java.util.Map;

public class CognateClient {
//    private  static final String TAG = App.TAG + "MobileClient";
    private final Context mContext;
    private SharedHelper sharedHelper;
    private final Map<String, String> headers = new HashMap<>();
    private CognateClient(Context context) {
        this.mContext = context;
    }
}
