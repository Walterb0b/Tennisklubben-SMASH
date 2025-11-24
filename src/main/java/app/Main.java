package main.java.app;

import main.java.membership.*;
import main.java.util.ScannerHelper;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        runProgram();

        }


    public static void runProgram() {
        //printMainMenu();

        MemberManager mm = new MemberManager();

        ScannerHelper sh = new ScannerHelper();

        System.out.println("REGISTRER MEDLEM");

        String name = sh.askQuestion("Indtast navn");
        System.out.print("Indtast telefonnummer: ");
        int phone = sh.askNumber(100000000);
        LocalDate birthday = inputUserDate();

        System.out.println("Vælg spillertype: \n1. Konkurrencespiller\n2. Motionist");
        int selType = sh.askNumber(2);


        System.out.println("Vælg medlemstype: \n1. Aktiv\n2. Passiv");
        int actPassive = sh.askNumber(2);

        if (actPassive == 1) {
            Member m1 = new Member(name, phone, birthday, new ActiveMembership());
        } else {
            Member m1 = new Member(name, phone, birthday, new PassiveMembership());
        }

        System.out.println("Medlem Registreret");









    }

    //Metoden her er input validering af datoer
    public static LocalDate inputUserDate() {
        //Problemer med at static metoder ikke kan tilgå de "generelle" variable fra toppen"
        ScannerHelper sh = new ScannerHelper();
        //LocalDate today = LocalDate.now(); //bruges i dato input validering
        LocalDate today = LocalDate.of(1900, 10, 7); //bruges i dato input validering - start booking date

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
        //userDate = LocalDate.of(2025, 10, 6);
        String userStringDate = "";
        System.out.println();

        while (!inputCorrect) {
            dayCorrect = false;
            monthCorrect = false;
            yearCorrect = false;
            userStringDate = sh.askQuestion("Venligst indtast datoen");

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

                    //Nedenstående gemmer måneden
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
                    }
                    //Nedenstående gemmer året

                    if (count == 1 && dayCorrect && monthCorrect) {
                        year = today.getYear();
                        yearCorrect = true;
                        //   inputCorrect = true;
                    } else if (count == 2 && dayCorrect && monthCorrect) {
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
                        System.out.println("FOR STOR. Ikke en gyldig dato i formatet DD/MM/YY");
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
                    // BRUGES TIL AT DEBUGGE: System.out.println("Du har valgt et korrekt dato-format");
                    if (userDate.isBefore(today)) {
                        System.out.println("Dato er før dags dato. Prøv igen.");
                    }
                    if (userDate.equals(today) || userDate.isAfter(today)) {
                        inputCorrect = true;
                    }

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

    public static void printMainMenu() {
        System.out.println();
        System.out.println(
                """
                           TENNISKLUBBEN SMASH
                           Velkommen!
                           """
               




        );
    }
}