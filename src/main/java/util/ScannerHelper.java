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
        System.out.println("=== Hovedmenu ===");
        System.out.println("1. Medlemskab");
        System.out.println("2. Økonomi");
        System.out.println("3. Spillerstatistik");
        System.out.println("4. Træning -og turneringsdata");
        System.out.println("0. Luk programmet");
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
