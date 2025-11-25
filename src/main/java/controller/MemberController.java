package main.java.controller;

import main.java.logic.MemberManager;
import main.java.membership.*;
import main.java.util.ScannerHelper;
import main.java.util.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class MemberController {
    private ScannerHelper sc;
    private MemberManager memberManager;
    private StamDataManager stamDataManager;

    public MemberController(ScannerHelper sc, MemberManager memberManager, StamDataManager stamDataManager){
        this.sc = sc;
        this.memberManager = memberManager;
        this.stamDataManager = stamDataManager;
    }


    public void run() {
        boolean back = false;

        while (!back) {
            sc.printMemberMenu();
            int choice = sc.navigateMenu(6);


            switch (choice) {
                //case 1 : memberManager.getMember();
                        //break;
                //case 2 : printAllMembers();
                        //break;
                case 3 : createMember();
                        break;
                case 4 : editMember();
                        break;
                //case 5 : memberManager.removeMember();
                        //break;
                //case 6 : ();
                        //break;
                case 0 :
                    back = true;
                    break;

                    default : System.out.println("Der er sket noget helt uventet");

            }
        }

    }

    private void createMember(){
        String name = sc.askQuestion("Indtast navn: ");
        String phoneNumber = sc.askQuestion("Indtast telefonnummer: ");
        LocalDate birthday = Validator.dateValidator(sc, "fødselsdag", "");
        boolean active = sc.askQuestion("Aktivt medlemskab?(Ja/Nej): ").trim().equalsIgnoreCase("J");
        Membership membership = active ? new ActiveMembership() : new PassiveMembership();
        Member member = new Member(name, phoneNumber, birthday, membership);
        memberManager.addMember(member);

        //memberManager.printAllMembers();

        System.out.println("Oprettet medlem med ID: " + member.getMemberID());
    }
    private void editMember() {
        boolean running = true;

        while(running) {
            sc.printEditMemberMenu();
            int choice = sc.navigateMenu(4);
            switch (choice){
                case 1:
                    editName();
                    break;
                case 2:
                    editBirthday();
                    break;
                case 3:
                    editPhoneNumber();
                    break;
                case 4:
                    //editMembership();
                    break;
                case 0:
                    running = false;
                    System.out.println("Går tilbage til medlemsmenu");
                    break;
                default:
                    System.out.println("Der er sket noget uvist");

            }
        }

    }

    private void editName(){
        System.out.println("Indtast medlemsID: ");
        int memberID = sc.askNumber(9999999);
        String choice = sc.askQuestion("Vil du ændre navn for " + memberManager.getMember(memberID).getName() + "?");

        if(choice.equalsIgnoreCase("Ja")) {
            String newName = sc.askQuestion("Indtast det nye navn: ");
            stamDataManager.updateName(memberID, newName);
            System.out.println("Du ændrede navn for " + memberManager.getMember(memberID));
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }

    private void editBirthday(){
            System.out.println("Indtast medlemsID: ");
            int memberID = sc.askNumber(9999999);
            String choice = sc.askQuestion("Vil du ændre fødselsdagsdato for " + memberManager.getMember(memberID).getName() + "?");

            if(choice.equalsIgnoreCase("Ja")) {
                LocalDate newBirthday = Validator.birthdayValidatorWithScanner(sc,"Indtast fødselsdag i datoformatet (DD/MM/YYYY)");
                stamDataManager.updateBirthday(memberID, newBirthday);
                System.out.println("Du ændrede fødselsdagdato for " + memberManager.getMember(memberID));
            } else if (choice.equalsIgnoreCase("Nej")) {
                System.out.println("Går tilbage til menuen");
            } else {
                System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
            }
        }
    private void editPhoneNumber() {
        System.out.println("Indtast medlemsID: ");
        int memberID = sc.askNumber(9999999);
        String choice = sc.askQuestion("Vil du ændre telefonnummer for " + memberManager.getMember(memberID).getName() + "?");

        if(choice.equalsIgnoreCase("Ja")) {
            String newPhoneNumber = sc.askQuestion("Indtast det nye telefonnummer: ");
            stamDataManager.updatePhoneNumber(memberID, newPhoneNumber);
            System.out.println("Du ændrede telefonnummer for " + memberManager.getMember(memberID));
        } else if (choice.equalsIgnoreCase("Nej")) {
            System.out.println("Går tilbage til menuen");
        } else {
            System.out.println("Ikke et gyldigt svar, går tilbage til menuen");
        }
    }
    private void editMembership() {
        System.out.println("Indtast medlemsID: ");
        int memberID = sc.askNumber(9999999);
        String choice = sc.askQuestion("Vil du ændre medlemskab for " + memberManager.getMember(memberID).getName() + "?");

        if(choice.equalsIgnoreCase("Ja")) {
            System.out.println("Vælg nyt medlemskab:\n1. Aktivt\n2. Passivt");
            int membershipChoice = sc.askNumber(2);

            switch(membershipChoice) {
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
}
