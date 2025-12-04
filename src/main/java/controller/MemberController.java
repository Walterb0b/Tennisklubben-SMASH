package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.membership.*;
import main.java.util.FileHandler;
import main.java.util.ScannerHelper;
import main.java.util.Validator;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberController {
    private ScannerHelper sc;
    private FileHandler fh;
    private MemberManager memberManager;
    private StamDataManager stamDataManager;
    private PaymentManager paymentManager;

    public MemberController(ScannerHelper sc, FileHandler fh, MemberManager memberManager, StamDataManager stamDataManager, PaymentManager paymentManager) {
        this.sc = sc;
        this.fh = fh;
        this.memberManager = memberManager;
        this.stamDataManager = stamDataManager;
        this.paymentManager = paymentManager;
    }


    public void run() {
        boolean back = false;

        while (!back) {
            sc.printMemberMenu();
            int choice = sc.navigateMenu(6);


            switch (choice) {
                case 1:
                    showMember();
                    break;
                case 2:
                    printAllMembers();
                    break;
                case 3:
                    createMember();
                    fh.saveMembersToCSV();
                    break;
                case 4:
                    editMember();
                    fh.saveMembersToCSV();
                    break;
                case 5:
                    removeMember();
                    break;
                    //case 6 : ();
                    //break;
                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("Der er sket noget helt uventet");
                    break;

            }
        }

    }

    private void showMember() {
        int memberID = selectMemberFromList();

        System.out.println(memberManager.getMember(memberID));

    }

    private void printAllMembers() {
        memberManager.printAllMembers();

    }

    private void createMember() {
        System.out.println("Du har valgt at oprette et medlem");
        System.out.println();
        String name = sc.askQuestion("Indtast navn");
        String phoneNumber = sc.askQuestion("Indtast telefonnummer");
        LocalDate birthday = Validator.birthdayValidatorWithScanner(sc, "Indtast fødselsdag i formatet DD/MM/YYYY");
        LocalDate signUpDate = LocalDate.now();
        boolean active = sc.askConfirmYesNo("Aktivt medlemsskab?");
        Membership membership = active ? new ActiveMembership() : new PassiveMembership();
        Member member = new Member(name, phoneNumber, birthday, signUpDate, membership);
        memberManager.addMember(member);
        paymentManager.createMembershipPaymentWithNewMember(member.getMemberID());

        //memberManager.printAllMembers();

        System.out.println("Oprettet medlem med ID: " + member.getMemberID());
    }

    private void editMember() {
        boolean running = true;

        while (running) {
            sc.printEditMemberMenu();
            int choice = sc.navigateMenu(4);
            switch (choice) {
                case 1:
                    editName();
                    fh.saveMembersToCSV();
                    break;
                case 2:
                    editBirthday();
                    fh.saveMembersToCSV();
                    break;
                case 3:
                    editPhoneNumber();
                    fh.saveMembersToCSV();
                    break;
                case 4:
                    editMembership();
                    fh.saveMembersToCSV();
                    break;
                case 0:
                    running = false;
                    System.out.println("Går tilbage til medlemsmenu");
                    break;
                default:
                    System.out.println("Der er sket noget uvist");
                    break;

            }
        }

    }

    private void editName() {
        int memberID = sc.selectMemberFromList();
        String choice = sc.askQuestion("Vil du ændre navn på " + memberManager.getMember(memberID).getName() + "? (Ja/Nej)");

        if (choice.equalsIgnoreCase("Ja")) {
            String newName = sc.askQuestion("Indtast det nye navn");
            stamDataManager.updateName(memberID, newName);
            System.out.println("Du ændrede navn for " + memberManager.getMember(memberID));
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }

    private void editBirthday() {

        int memberID = sc.selectMemberFromList();
        String choice = sc.askQuestion("Vil du ændre fødselsdagsdato på " + memberManager.getMember(memberID).getName() + "? (Ja/Nej)");

        if (choice.equalsIgnoreCase("Ja")) {
            LocalDate newBirthday = Validator.birthdayValidatorWithScanner(sc, "Indtast fødselsdag i datoformatet (DD/MM/YYYY)");
            stamDataManager.updateBirthday(memberID, newBirthday);
            System.out.println("Du ændrede fødselsdagdato for " + memberManager.getMember(memberID));
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }

    private void editPhoneNumber() {

        int memberID = sc.selectMemberFromList();
        String choice = sc.askQuestion("Vil du ændre telefonnummer på " + memberManager.getMember(memberID).getName() + "? (Ja/Nej)");

        if (choice.equalsIgnoreCase("Ja")) {
            String newPhoneNumber = sc.askQuestion("Indtast det nye telefonnummer");
            stamDataManager.updatePhoneNumber(memberID, newPhoneNumber);
            System.out.println("Du ændrede telefonnummer for " + memberManager.getMember(memberID));
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }

    private void editMembership() {

        int memberID = sc.selectMemberFromList();
        String choice = sc.askQuestion("Vil du ændre medlemskab for " + memberManager.getMember(memberID).getName() + "? (Ja/Nej)");

        if (choice.equalsIgnoreCase("Ja")) {
            System.out.println("Vælg nyt medlemskab:\n1. Aktivt\n2. Passivt");
            int membershipChoice = sc.askNumber(2);

            switch (membershipChoice) {
                case 1:
                    stamDataManager.updateMembershipActive(memberID);
                    System.out.println("Medlemskab ændret til aktivt for " + memberManager.getMember(memberID).getName());
                    break;
                case 2:
                    stamDataManager.updateMembershipPassive(memberID);
                    System.out.println("Medlemskab ændret til passivt for " + memberManager.getMember(memberID).getName());
                    break;
            }
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }

    private void removeMember() {

        int memberID = sc.selectMemberFromList();
        String choice = sc.askQuestion("Vil du slette " + memberManager.getMember(memberID).getName() + " fra systemet? (Ja/Nej)");
        if (choice.equalsIgnoreCase("Ja")) {
            System.out.println("Er du sikker? \n1. Ja\n2. Nej");
            int finalChoice = sc.askNumber(2);
            switch (finalChoice) {
                case 1:
                    memberManager.removeMember(memberID);
                    System.out.println("Du har slettet medlemmet med ID " + memberID);
                    break;
                case 2:
                    System.out.println("Går tilbage til menuen");
                    break;
            }
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }

    private int selectMemberFromList() {
        boolean inputCorrect = false;
        int viewCount = 1;
        int memberID = 0;
        while (!inputCorrect) {
            String query = sc.askQuestion("Indtast MedlemsID eller søg på navn");
            if (query.isEmpty() || query.isBlank()) {
                System.out.println("Din søgestreng er tom. Prøv igen.");
            } else {
                ArrayList<Integer> memberList = memberManager.searchForMemberIDs(query);
                if (memberList.isEmpty()) {
                    System.out.println("Der findes ikke medlemmer, der opfylder dine søgekriterier. Prøv igen.");

                } else {
                    for (int m : memberList) {
                        System.out.println(viewCount + ". " + memberManager.getMember(m));
                        viewCount++;
                    }
                    System.out.println();
                    System.out.println("Vælg medlem fra listen");
                    int userSelect = sc.navigateMenu(memberList.size());
                    userSelect = userSelect - 1;
                    memberID = memberList.get(userSelect);
                    memberManager.getMember(memberID);
                    inputCorrect = true;
                }

            }

        }
        return memberID;

    }

}
