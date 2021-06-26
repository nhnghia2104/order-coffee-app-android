package com.cogeek.tncoffee.utils;

public class MyConfig {
    public final boolean IS_DEBUG = false;
    private final String DEBUG_URL = "http://10.0.2.2:2104/01/";
    private final String DEPLOY_URL = "http://20.58.163.19:2104/01/";
    private static MyConfig _instance;
    private MyConfig() {
    }

    public static MyConfig self() {
        if (_instance == null) {
            _instance = new MyConfig();
        }
        return _instance;
    }

    public String getBASE_URL() {
        return IS_DEBUG ? DEBUG_URL : DEPLOY_URL;
    }
}
