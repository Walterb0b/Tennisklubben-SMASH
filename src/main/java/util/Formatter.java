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

    public static LocalDate stringToLocalDate(String s){
        String[] parts = s.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return LocalDate.of(year, month, day);

    }

}
