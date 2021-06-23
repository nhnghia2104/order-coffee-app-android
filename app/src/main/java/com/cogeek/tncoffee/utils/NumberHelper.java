package com.cogeek.tncoffee.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        NumberFormat formatter = new DecimalFormat("###,### ₫");
        String formattedNumber = formatter.format(number);
        return formattedNumber;
    }

    public String floatFormatDefault(float number) {
        NumberFormat formatter = new DecimalFormat("0.#");
        String formattedNumber = formatter.format(number);
        return formattedNumber;
    }

    public String percentFormat(double number) {
        NumberFormat formatter = new DecimalFormat("0.#");
        String formattedNumber = formatter.format(number);
        return "-" + formattedNumber + "%";
    }

    public String dateFormatForDatabase(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(date));
    }

    public String dateFormat(String str) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime datetime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return datetime.format(DateTimeFormatter.ofPattern("HH:mm, dd/MM/yyyy"));
        }
        else {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
                return new SimpleDateFormat("HH:mm, dd/MM/yyyy").format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public String dateFormat(long time) {
        return new SimpleDateFormat("HH:mm, dd/MM/yyyy")
                .format(new Date(time));
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
