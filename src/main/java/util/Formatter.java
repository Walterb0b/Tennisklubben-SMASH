package main.java.util;

import main.java.logic.MemberManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Formatter {
    private static final String nullValue = "";


    public static String localDateToString(LocalDate date) {
        String dateString = "";
        if (date != null) {
            dateString = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        }
        return dateString;
    }

    public static String booleanToString(boolean bool, String ifTrue, String ifFalse) {
        String boolString = nullValue;
        try {
            if (bool) {
                boolString = ifTrue;
            } else if (!bool) {
                boolString = ifFalse;
            }
        } catch (NullPointerException e) {
            System.out.println("fejl");
        }
        return boolString;
    }

    public static LocalDate stringToLocalDate(String s){
        if (s.isBlank()) {
            return null;
        } else {
            String[] parts = s.split("/");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            return LocalDate.of(year, month, day);
        }
    }

    public static String displayPaymentString(String name, int memberID, String seasonQuarter, double amount){
        return String.format("%-10s | %-30s | %4.0f kr. | %-8s ",
                memberID,
                name,
                amount,
                seasonQuarter) +
        "\n" + "----------------------------------------------------------------------------------";

    }
}
