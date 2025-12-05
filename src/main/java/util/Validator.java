package main.java.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validering af brugerinput gennem ScannerHelper.
 */
public class Validator {
    final static Pattern IsIntegerPattern = Pattern.compile("^\\d+$");
    final static Pattern ContainsIntegerPattern = Pattern.compile("\\d+");

    public static boolean isInteger(String str) {
        return IsIntegerPattern.matcher(str).matches();
    }

    public static boolean containsInteger(String str) {
        return ContainsIntegerPattern.matcher(str).find();
    }

    /**
     * Parser en string til en integer sålænge query indeholder en integer
     * @param query søgestreng
     * @return integer ud fra søgestreng
     * @throws SmashException hvis der ikke er en integer i søgestrengen
     */
    public static int lossyConvertStringToInt(String query) throws SmashException {
        Matcher m = ContainsIntegerPattern.matcher(query);
        if (m.find()) {
            int number = Integer.parseInt(m.group());
            return number;
        } else {
            throw new SmashException("Fejl i konvertering fra String til Integer");
        }
    }

    public static int yearValidator(int year) {
        if (year > 1900) {
            return year;
        } else {
            throw new SmashException("Fejl i validering af år");
        }
    }

    /**
     *Sikrer at brugeren indtaster en gyldig dato
     * @param sh scanner som håndterer brugerens input
     * @param question spørgsmål som brugeren skal stilles
     * @return gyldig LocalDate dato
     */
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

    /**
     * Sikrer at brugeren har indtastet en gyldig fødselsdag. Brugeren skal være ældre end 3 år og yngre end 120 år. Det skal
     * være en dato i fortiden. Hvis brugerens indtastning ikke lever op til disse kriterier spørges der igen indtil inputtet
     * er gyldigt.
     * @param sh Scanner som håndterer input fra brugeren
     * @param question Spørgsmål som brugeren stilles
     * @return
     */
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
        }
        return date;
    }

    /**
     * Bruges af birthdayValidatorWithScanner til at validere at brugeren har indtastet en gyldig fødselsdag.
     * Brugeren skal være ældre end 3 år og yngre end 120 år. Det skal være en dato i fortiden.
     * @param birthday fødselsdagsdato som valideres ud fra disse kriterier
     * @return gyldig fødselsdagsdato
     * @throws SmashException hvis indtastningen ikke er gyldig.
     */
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

    /**
     * Opretter en string hvor år og kvartal er skrevet sammen. 1/1/25 bliver til 25Q1
     * @param date LocalDate som input
     * @return sammenskrivning af år og kvartal
     */
    public static String getSeasonQuarter(LocalDate date) {
        int monthValue = date.getMonthValue();
        String yearString = String.valueOf(date.getYear()).substring(2);
        if(monthValue >= 1 && monthValue <= 3){
            return yearString + "Q1";
        } else if (monthValue >= 4 && monthValue <= 6) {
            return yearString + "Q2";
        } else if (monthValue >= 7 && monthValue <= 9) {
            return yearString + "Q3";
        } else if (monthValue >= 10 && monthValue <= 12) {
            return yearString + "Q4";
        } else {
            throw new SmashException("ikke gyldig måned");
        }
    }

    /**
     * Bruges af birthdayValidatorWithScanner og dateValidatorWithScanner til at validere om brugeren har indtastet
     * en gyldig dato
     * @param userDateString dato indtastning som string
     * @return LocalDate såfremt datoen er indtastet korrekt
     * @throws SmashException hvis der er fejl i datoindtastningen
     */
    static LocalDate dateValidator(String userDateString) throws SmashException {

        int count;
        int year = 0;
        int month = 0;
        int day = 0;
        int yearMin = 1900;
        LocalDate userDate = null;


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
                        month = Integer.parseInt(values[1]);
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

