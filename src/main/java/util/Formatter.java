package main.java.util;

import java.time.LocalDate;

public class Formatter {

    public static String localDateToString(LocalDate date) {
        String year = String.valueOf(date.getYear());
        String month = String.valueOf(date.getMonth());
        String day = String.valueOf(date.getMonth());
        String delimiter = "-";
        String singleLine;

        return year + delimiter + month + delimiter + day;
    }

    public
}
