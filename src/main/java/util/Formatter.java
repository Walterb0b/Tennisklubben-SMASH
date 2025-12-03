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

    public static String booleanStringToString(String boolString, String ifTrue, String ifFalse) {
        String boolStringReturn = "";
        try {
            if (boolString.equalsIgnoreCase("true")) {
                boolStringReturn = ifTrue;
            } else if (boolString.equalsIgnoreCase("false")) {
                boolStringReturn = ifFalse;
            }
        } catch (NullPointerException e) {
            System.out.println("fejl");
        }
        return boolStringReturn;
    }

    public static String csvEncode(Object value) {
        if (value == null) return nullValue;

        String s = value.toString();
        if (s.contains(";") || s.contains("\"") || s.contains("\n")) {
            s = "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }

    public static LocalDate stringToLocalDate(String s){
        String[] parts = s.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        return LocalDate.of(year, month, day);

    }


}
