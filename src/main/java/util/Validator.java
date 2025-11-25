package main.java.util;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class Validator {

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
                System.out.println("Dato-fejl: " + e);
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
                date = dateValidator(answer);
            } catch (SmashException e){
                System.out.println("Dato-fejl: " + e);
            } try {
                date = birthdayValidator(date);
                inputCorrect = true;
            } catch (SmashException e) {
                System.out.println("FødselsdagsDato-fejl: " + e);
            }
        }
        return date;
    }

    public static LocalDate birthdayValidator(LocalDate birthday) throws SmashException {
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

    public static LocalDate dateValidator(String userDateString) throws SmashException {

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

    public static LocalDate dateValidatorOLD(ScannerHelper sh, String dateType, String string) {

            boolean inputCorrect = false;
            boolean yearCorrect = false;
            boolean monthCorrect = false;
            boolean dayCorrect = false;
            //LocalDate today = LocalDate.now();
            int count;
            int year = 0;
            int month = 0;
            int day = 0;
            LocalDate userDate = null;

            String userStringDate = "";
            System.out.println();

            while (!inputCorrect) {
                dayCorrect = false;
                monthCorrect = false;
                yearCorrect = false;
                userStringDate = sh.askQuestion("Indtast " + dateType + " i formatet DD/MM/YYYY");

                //hvis første char i brugerindtastningen er et bogstav, så er dato ikke korrekt
                if (Character.isLetter(userStringDate.charAt(0))) {
                    System.out.println("En dato skal starte med et tal i formatet DD/MM/YYYY. Prøv igen.");
                    //der skal minimum være én / i brugerindtastningen
                } else {
                    //finder ud af hvor mange / der er i min string
                    count = userStringDate.length() - userStringDate.replace("/", "").length();
                    if (count == 0) { // ikke gyldig - gentag
                        System.out.println("Ikke en gyldig dato i formatet DD/MM/YYYY");

                    } else if (count == 1 || count == 2) {
                        String[] values = userStringDate.trim().split("/");

                        //Nedenstående gemmer dagen
                        try {
                            day = Integer.parseInt(values[0]);
                            if (day > 31) {
                                System.out.println("FEJL DAG - dag større end 31");
                            } else {
                                dayCorrect = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Dag forkert indtastet. Prøv igen");
                        }

                        try {
                            month = Integer.parseInt(values[1]);
                            if (month > 12 && dayCorrect) {
                                System.out.println("FEJL MÅNED - måned større end 12");
                            } else {
                                monthCorrect = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Måned forkert indtastet. Prøv igen");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            System.out.println("Fejl i måned indtastning. Prøv igen");

                        //Nedenstående gemmer året

                        /*
                        }
                        if (count == 1 && dayCorrect && monthCorrect) {
                            year = today.getYear();
                            yearCorrect = true;
                            //   inputCorrect = true;

                         */
                        } if (count == 2 && dayCorrect && monthCorrect) {
                            try {
                                year = Integer.parseInt(values[2]);
                                yearCorrect = true;
                                //       inputCorrect = true;
                            } catch (NumberFormatException e) {
                                System.out.println("År forkert indtastet. Prøv igen");
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Fejl i år indtastning. Prøv igen");
                            }
                        } else if (count > 2 && dayCorrect && monthCorrect) {
                            System.out.println("FOR STOR. Ikke en gyldig dato i formatet DD/MM/YYYY");
                        }
                    }
                }

                if (dayCorrect && monthCorrect && yearCorrect) {
                    try {
                        System.out.print(year + "/");
                        System.out.print(month + "/");
                        System.out.print(day + " ");
                        System.out.println();

                        userDate = LocalDate.of(year, month, day);
                        inputCorrect = true;
                        /*
                        // BRUGES TIL AT DEBUGGE: System.out.println("Du har valgt et korrekt dato-format");
                        if (userDate.isBefore(today)) {
                            System.out.println("Dato er før dags dato. Prøv igen.");
                        }
                        if (userDate.equals(today) || userDate.isAfter(today)) {
                            inputCorrect = true;
                        }

                         */

                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: " + e);
                    } catch (DateTimeException e) {
                        System.out.println("FEJL dato" + e);
                    }
                }
            }

            // BRUGES TIL AT DEBUGGE: System.out.println("du har indtastet noget korrekt");
            return userDate;
        }

    }

