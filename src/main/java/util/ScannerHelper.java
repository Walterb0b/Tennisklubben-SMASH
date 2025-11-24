package main.java.util;

import java.util.Scanner;

public class ScannerHelper {

    private Scanner sc;

    //Constructor
    public ScannerHelper () {
        this.sc = new Scanner(System.in);
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

            if (sc.hasNextInt()) {
                selectInt = sc.nextInt();
                sc.nextLine();

                if (selectInt < 0) {
                    System.out.println("Du har indtastet et negativt tal. Prøv igen.");
                } else if (selectInt > intMax) {
                    System.out.println("Du kan ikke indtaste tal som er større end " + intMax + ". Prøv igen.");
                } else {
                    numCorrect = true;
                }
            } else if (sc.hasNextLine() && sc.nextLine().equals(abort)) {
                System.out.println("Du har afbrudt den aktuelle handling. Går tilbage til hovedmenuen");
                selectInt = -1;
                sc.nextLine();
            } else {
                System.out.println("Du har ikke indtastet et tal. Prøv igen.");
                sc.nextLine(); // rydder forkert input
            }
        }
        return selectInt;
    }

}
