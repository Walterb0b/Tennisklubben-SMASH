package main.java.util;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ScannerHelper {

    private Scanner sc;

    //Constructor
    public ScannerHelper () {
        this.sc = new Scanner(System.in);
    }
    public void printMainMenu(){
        System.out.println();
        System.out.println("""
                           🎾🥎=== Hovedmenu ===🥎🎾
                ┌────────────────────────────────────────────┐
                │           1. Medlemskab                    │
                │           2. Økonomi                       │
                │           3. Spillerstatistik              │
                │           4. Trænings- og turneringsdata   │
                │           9. Luk programmet                │
                └────────────────────────────────────────────┘
                """);
        //System.out.println("1. Medlemskab");
        //System.out.println("2. Økonomi");
        //System.out.println("3. Spillerstatistik");
        //System.out.println("4. Træning -og turneringsdata");
        //System.out.println("9. Luk programmet");
    }

    public void printMemberMenu(){
        System.out.println();
        System.out.println("""
                           🧑‍🎾=== Medlemmer ===‍🎾👨
                ┌────────────────────────────────────────────┐
                │           1. Se medlemskab                 │
                │           2. Se medlemsoversigt            │
                │           3. Tilføj medlem                 │
                │           4. Rediger medlem                │
                │           5. Slet medlem                   │
                │           0. Gå tilbage                    │
                └────────────────────────────────────────────┘
                """);
        //System.out.println("=== Medlemmer ===");
        //System.out.println("1. Se medlemskab");
        //System.out.println("2. Se medlemsoversigt");
        //System.out.println("3. Tilføj medlem");
        //System.out.println("4. Rediger medlem");
        //System.out.println("5. Slet medlem");
        //System.out.println("0. Gå tilbage");
    }

    public void printFinanceMenu(){
        System.out.println("=== Økonomi ===");
        System.out.println("1. Tilføj indbetaling");
        System.out.println("2. Restance liste");
        System.out.println("3. Se forventede kontingent betalinger");
        System.out.println("0. Gå tilbage");
    }

    public void printStatsMenu(){
        System.out.println("=== Spillerstatistik ===");
        System.out.println("1. Se top 5 spillere");
        System.out.println("2. Se statistik for en spiller");
        System.out.println("3. Se turneringskampe for spiller");
        System.out.println("0. Gå tilbage");
    }

    public void printResultMenu(){
        System.out.println("=== Træning og turneringsdata ===");
        System.out.println("1. Tilføj turneringskamp");
        System.out.println("2. Rediger turneringskamp");
        System.out.println("3. Slet turneringskamp");
        System.out.println("4. Tilføj træningsresultat");
        System.out.println("5. Rediger træningsresultat");
        System.out.println("6. Slet træningsresultat");
        System.out.println("0. Gå tilbage");
    }

    public void printEditMemberMenu(){
        System.out.println();
        System.out.println("""
                 🧑‍📋=== Ændring af medlemsoplysninger ===‍📋👨
                ┌─────────────────────────────────────────────┐
                │            1. Ændre navn                    │
                │            2. Ændre fødselsdagsdato         │
                │            3. Ændre telefonnummer           │
                │            4. Ændre medlemskab              │
                │            0. Gå tilbage                    │
                └─────────────────────────────────────────────┘
                """);
        //System.out.println("=== Ændring af medlemsoplysninger ===");
        //System.out.println("1. Ændre navn");
        //System.out.println("2. Ændre fødselsdagsdato");
        //System.out.println("3. Ændre telefonnummer");
        //System.out.println("4. Ændre medlemskab (Passivt/Aktivt");
        //System.out.println("0. Gå tilbage");
    }

    //Metode til at få int fra scanner input
    public int askNumber(int intMax) {
        boolean numCorrect = false;
        int selectInt = 0;


        while (!numCorrect) {

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen.");
                } else if (selectInt == 0) {
                    System.out.println("Du kan ikke indtaste 0. Prøv igen.");
                } else if (selectInt > intMax) {
                    System.out.println("Du kan ikke indtaste tal som er større end " + intMax + ". Prøv igen.");
                } else {
                    numCorrect = true;
                }
            } else {
                System.out.println("Du har ikke indtastet et tal. Prøv igen.");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }
    //Metode til at få String fra scanner input
    public String askQuestion(String question) {
        System.out.print(question + ": ");
        String answer = sc.nextLine();
        if (answer.isEmpty()) {
            System.out.println("Ups - den fik jeg ikke. Prøv igen.");
            System.out.print(question + ": ");
            answer = sc.nextLine();
        }
        return answer;
    }

    //Metode til at navigere i menuen
    public int navigateMenu(int intMax) {
        String abort = "HMENU";
        boolean numCorrect = false;
        int selectInt = 0;


        while (!numCorrect) {
            System.out.print("Indtast tal (1-" + intMax + "): ");

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen.");
                } else if (selectInt > intMax) {
                    System.out.println("Du kan ikke indtaste tal som er større end " + intMax + ". Prøv igen.");
                } else if (selectInt == 0) {
                    System.out.println("Afbryder nuværende handling og går tilbage til tidligere menu.");
                    numCorrect = true;
                } else {
                    numCorrect = true;
                }
            } else if (sc.hasNextLine() && sc.nextLine().equals(abort)) {
                System.out.println("Afbryder nuværende handling og går tilbage til hovedmenuen");
                selectInt = -1;
                numCorrect = true;
            } else {
                System.out.println("Du har ikke indtastet et tal. Prøv igen.");
                //sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }

}
