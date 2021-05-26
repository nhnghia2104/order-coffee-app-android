package com.cogeek.tncoffee.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NumberHelper {
    private static NumberHelper instance;
    public static NumberHelper getInstance() {
        if (instance == null) {
            instance = new NumberHelper();
        }
        return instance;
    }
    private NumberHelper() {};

    public String currencyFormat(double number) {
        NumberFormat formatter = new DecimalFormat("#.###");
        String formattedNumber = formatter.format(number);
        return formattedNumber + "₫";
    }

    public String dateFormatForDatabase(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(date));
    }
}
