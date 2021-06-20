package com.cogeek.tncoffee.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StringHelper {
    private static StringHelper instance;
    public static StringHelper getInstance() {
        if (instance == null) {
            instance = new StringHelper();
        }
        return instance;
    }
    private StringHelper() {};

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
