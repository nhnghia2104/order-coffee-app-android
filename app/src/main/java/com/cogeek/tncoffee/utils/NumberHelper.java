package com.cogeek.tncoffee.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

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
        NumberFormat formatter = new DecimalFormat("#,###");
        String formattedNumber = formatter.format(number);
        return formattedNumber + "₫";
    }
}
