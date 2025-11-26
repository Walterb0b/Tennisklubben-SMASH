package main.java.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    final static Pattern IsIntegerPattern = Pattern.compile("^\\d+$");
    final static Pattern ContainsIntegerPattern = Pattern.compile("\\d+");

    public static boolean isInteger(String str) {
        return IsIntegerPattern.matcher(str).matches();
    }

    public static boolean containsInteger(String str) {
        return ContainsIntegerPattern.matcher(str).find();
    }

    public static int lossyConvertStringToInt(String query) throws SmashException {
        Matcher m = ContainsIntegerPattern.matcher(query);
        if (m.find()) {
            int number = Integer.parseInt(m.group());
            return number;
        } else {
            throw new SmashException("Fejl i konvertering fra String til Integer");
        }
    }

    public Validator() {
        //Do nothing
    }

    public static LocalDate dateValidatorWithScanner(ScannerHelper sh, String question) {
        boolean inputCorrect = false;
        LocalDate date = null;
        while (!inputCorrect) {
            String answer = sh.askQuestion(question);
            try {
                date = dateValidator(answer);

                inputCorrect = true;

            } catch (SmashException e){
                System.out.println("Dato-fejl: " + e.getMessage());
            }
        }
        return date;
    }

    public static LocalDate birthdayValidatorWithScanner(ScannerHelper sh, String question) {
        boolean inputCorrect = false;
        LocalDate date = null;
        while (!inputCorrect) {
            String answer = sh.askQuestion(question);
            try {
                date = birthdayValidator(dateValidator(answer));
                inputCorrect = true;
            } catch (SmashException e) {
                System.out.println("FødselsdagsDato-fejl: " + e.getMessage());
            }
                /*
            } try {return number;
                date = birthdayValidator(date);
                inputCorrect = true;
            } catch (SmashException e) {
                System.out.println("FødselsdagsDato-fejl: " + e);
            }

                 */
        }
        return date;
    }

    static LocalDate birthdayValidator(LocalDate birthday) throws SmashException {
        int minAge = 3;
        int maxAge = 120;
        LocalDate now = LocalDate.now();

        if (Period.between(birthday, now).getYears() < minAge && birthday.isBefore(now)) {
            throw new SmashException("Medlemmer kan ikke være yngre end " + minAge + " år");
        } else if (Period.between(birthday, now).getYears() > maxAge && birthday.isBefore(now)) {
            throw new SmashException("Medlemmer kan ikke være ældre end " + maxAge + " år");
        } else if (birthday.isAfter(now)) {
            throw new SmashException("Medlemmer kan ikke have en fødselsdag i fremtiden");
        }
        return birthday;
    }

    static LocalDate dateValidator(String userDateString) throws SmashException {

        boolean inputCorrect = false;
        boolean yearCorrect = false;
        boolean monthCorrect = false;
        boolean dayCorrect = false;
        //LocalDate today = LocalDate.now();
        int count;
        int year = 0;
        int month = 0;
        int day = 0;
        int yearMin = 1900;
        LocalDate userDate = null;

        /*while (!inputCorrect) {
            dayCorrect = false;
            monthCorrect = false;
            yearCorrect = false;
            //userDateString = sh.askQuestion("Indtast " + dateType + " i formatet DD/MM/YYYY");

         */

            //hvis første char i brugerindtastningen er et bogstav, så er dato ikke korrekt
            if (Character.isLetter(userDateString.charAt(0))) {
                throw new SmashException("En dato kan ikke starte med et bogstav. Prøv igen");
            } else {
                //finder ud af hvor mange / der er i min string
                count = userDateString.length() - userDateString.replace("/", "").length();
                if (count == 0 ||count == 1) {
                    throw new SmashException("Ikke en gyldig dato i formatet DD/MM/YYYY");

                } else if ( count == 2) {
                    String[] values = userDateString.trim().split("/");

                    try {
                        day = Integer.parseInt(values[0]);
                        /*
                        if (day > 31) {
                            System.out.println("FEJL DAG - dag større end 31");
                        } else {
                            dayCorrect = true;
                        }

                         */
                        month = Integer.parseInt(values[1]);
                        /*
                        if (month > 12 && dayCorrect) {
                            System.out.println("FEJL MÅNED - måned større end 12");
                        } else {
                            monthCorrect = true;
                        }
                         */
                        year = Integer.parseInt(values[2]);
                        if (year < yearMin) {
                            throw new SmashException("Årstal kan ikke være lavere end " + yearMin);
                        }


                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        throw new SmashException("Dag, måned eller år forkert indtastet. Prøv igen");
                    }
                try {
                    userDate = LocalDate.of(year, month, day);

                } catch (NumberFormatException | DateTimeException e) {
                    throw new SmashException("Fejl i oprettelse af dato. Prøv igen");
                }
            }
        }
        return userDate;
    }

}

