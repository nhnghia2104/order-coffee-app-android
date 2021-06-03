package com.cogeek.tncoffee.utils;

public class MyConfig {
    public final boolean IS_DEBUG = true;
    public final String BASE_URL = "http://10.0.2.2:2104/01/";
    private static MyConfig _instance;
    private MyConfig() {
    }

    public static MyConfig self() {
        if (_instance == null) {
            _instance = new MyConfig();
        }
        return _instance;
    }
}
