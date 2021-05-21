package com.cogeek.tncoffee.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkProvider {
    private static volatile NetworkProvider mInstance = null;

    public Retrofit retrofit;

    private NetworkProvider() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:2104/01/index.php/mobile/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static NetworkProvider self() {
        if (mInstance == null)
            mInstance = new NetworkProvider();
        return mInstance;
    }
}
