package main.java.util;

import main.java.logic.MemberManager;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Håndterer input fra brugeren og printer menuer
 */
public class ScannerHelper {

    private Scanner sc;
    private MemberManager memberManager;

    //Constructor
    public ScannerHelper (MemberManager memberManager) {
        this.sc = new Scanner(System.in);
        this.memberManager = memberManager;
    }
    public void printMainMenu(){
        System.out.println();
        System.out.println("""
                      🎾🥎=== Hovedmenu ===🥎🎾
                ┌────────────────────────────────────┐
                │   1. Medlemskab                    │
                │   2. Økonomi                       │
                │   3. Spillerstatistik              │
                │   4. Kampdata                      │
                │   5. Turneringsadministration      │
                │   9. Luk programmet                │
                └────────────────────────────────────┘
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
                ┌─────────────────────────────────┐
                │     1. Se medlemskab            │
                │     2. Se medlemsoversigt       │
                │     3. Tilføj medlem            │
                │     4. Rediger medlem           │
                │     5. Slet medlem              │
                │     0. Gå tilbage               │
                └─────────────────────────────────┘
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
        System.out.println();
        System.out.println("""
                              💳=== Økonomi ===💳
                ┌─────────────────────────────────────────────┐
                │    1. Tilføj indbetaling                    │
                │    2. Restance liste                        │
                │    3. Se forventede kontingent betalinger   │
                │    0. Gå tilbage                            │
                └─────────────────────────────────────────────┘
                """);
        //System.out.println("=== Økonomi ===");
        //System.out.println("1. Tilføj indbetaling");
        //System.out.println("2. Restance liste");
        //System.out.println("3. Se forventede kontingent betalinger");
        //System.out.println("0. Gå tilbage");
    }

    public void printStatsMenu(){
        System.out.println();
        System.out.println("""
                       📊=== Spillerstatistik ===📊
                ┌────────────────────────────────────────┐
                │    1. Se top 5 spillere                │
                │    2. Se statistik for en spiller      │
                │    3. Se turneringskampe for spiller   │
                │    0. Gå tilbage                       │
                └────────────────────────────────────────┘
                """);
        //System.out.println("=== Spillerstatistik ===");
        //System.out.println("1. Se top 5 spillere");
        //System.out.println("2. Se statistik for en spiller");
        //System.out.println("3. Se turneringskampe for spiller");
        //System.out.println("0. Gå tilbage");
    }

    public void printResultMenu(){
        System.out.println();
        System.out.println("""
                        🎾=== Kampdata ===🎾
                ┌────────────────────────────────────┐
                │     1. Tilføj kamp                 │
                │     2. Rediger kamp                │
                │     3. Slet kamp                   │
                │     4. Se Kamp                     │
                │     0. Gå tilbage                  │
                └────────────────────────────────────┘
                """);
        //System.out.println("=== Træning og turneringsdata ===");
        //System.out.println("1. Tilføj turneringskamp");
        //System.out.println("2. Rediger turneringskamp");
        //System.out.println("3. Slet turneringskamp");
        //System.out.println("4. Tilføj træningsresultat");
        //System.out.println("5. Rediger træningsresultat");
        //System.out.println("6. Slet træningsresultat");
        //System.out.println("0. Gå tilbage");
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

    /**
     * printer turneringsmenu
     */
    public void printTournamentMenu() {
        System.out.println();
        System.out.println("""
                      🎾=== Turnerings-Menu ===🎾
                ┌────────────────────────────────────┐
                │     1. Opret turnering             │
                |     2. Tilføj kamp til turnering   |
                │     3. Vis alle turneringer        │
                │     4. Vis kampe i en turnering    │
                │     0. Gå tilbage                  │
                └────────────────────────────────────┘
                """);
    }

    public void printLn(String prompt){
        System.out.println(prompt);
    }

    public void print(String prompt){
        System.out.print(prompt);
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

    public int askNumber(String prompt) {
        System.out.print(prompt);
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

    /**
     * Bruges til at navigere i menupunkter
     * @param intMax det højest gyldige tal som kan indtastes
     * @return brugerens valg som integer
     */
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

    public int selectMemberFromList() {
        boolean inputCorrect = false;
        int viewCount = 1;
        int memberID = 0;
        while (!inputCorrect) {
            String query = askQuestion("Indtast MedlemsID eller søg på navn");
            if (query.isEmpty() || query.isBlank()) {
                System.out.println("Din søgestreng er tom. Prøv igen.");
            } else {
                ArrayList<Integer> memberList = memberManager.searchForMemberIDs(query);
                if (memberList.isEmpty()) {
                    System.out.println("Der findes ikke medlemmer, der opfylder dine søgekriterier. Prøv igen.");

                } else if (memberList.size()==1) {
                    System.out.println("Du har valgt " + memberManager.getMember(memberList.getFirst()).memberNameAndIDString());
                    memberID = memberList.getFirst();
                    inputCorrect = true;


                }else {
                    for (int m : memberList) {
                        System.out.println(viewCount + ". " + memberManager.getMember(m).memberNameAndIDString());
                        viewCount++;
                    }
                    System.out.println();
                    System.out.println("Vælg medlem fra listen");
                    int userSelect = navigateMenu(memberList.size());
                    userSelect = userSelect - 1;
                    memberID = memberList.get(userSelect);
                    memberManager.getMember(memberID);
                    inputCorrect = true;
                }
            }
        }
        return memberID;
    }

    public boolean askConfirmYesNo (String question) {
        boolean replyCorrect = false;
        boolean reply = false;

        while(!replyCorrect) {
            System.out.print(question + "? (j/n):");
            String answer = sc.nextLine();
            if (answer.equalsIgnoreCase("ja") ||
                    answer.equalsIgnoreCase("j")) {
                replyCorrect = true;
                reply = true;
            } else if (answer.equalsIgnoreCase("nej") ||
                    answer.equalsIgnoreCase("n")) {
                replyCorrect = true;
                reply = false;
            } else {
                System.out.println("Ups, den fik jeg ikke - prøv igen");
            }

        }
        return reply;
    }

}
