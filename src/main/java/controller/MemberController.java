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

    public MemberController(ScannerHelper sc, MemberManager memberManager){
        this.sc = sc;
        this.memberManager = memberManager;
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
                //case 4 : memberManager.editMember();
                        //break;
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
        sc.printEditMemberMenu();
        int choice = sc.navigateMenu();

        while(running) {
            switch (choice){
                case 1:
                    editName();
                    break;
                case 2:
                    //editBirthday();
                    break;
                case 3:
                    //editPhoneNumber();
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
        memberManager.getMember(sc.askNumber(999999));
        System.out.println("Vil du ændre ");
        // stamDataManager.updateName()
    }
}

}
