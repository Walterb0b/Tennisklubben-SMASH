package main.java.controller;

import main.java.logic.MemberManager;
import main.java.membership.*;
import main.java.util.ScannerHelper;

import java.time.LocalDate;
import java.util.Scanner;

public class MemberController {
    private ScannerHelper sc = new ScannerHelper();
    private MemberManager memberManager;
    private StamDataManager stamDataManager;

    public MemberController(ScannerHelper sc, MemberManager memberManager){
        this.sc = sc;
        this.memberManager = memberManager;
        this.stamDataManager = stamDataManager;
    }


    public void run() {
        boolean running = true;

        while (running) {
            sc.printMemberMenu();
            int choice = sc.navigateMenu();


            switch (choice) {
                case 1 -> memberManager.getMember();
                case 2 -> memberManager.printAllMembers();
                case 3 -> createMember();
                //case 4 -> memberManager.editMember();
                case 5 -> memberManager.removeMember();
                //case 6 -> ();

            }
        }

    }

    private void createMember(){
        String name = sc.askQuestion("Indtast navn: ");
        String phoneNumber = sc.askQuestion("Indtast telefonnummer: ");
        LocalDate birthday = sc.;
        boolean active = sc.askQuestion("Aktivt medlemskab?(Ja/Nej): ").trim().equalsIgnoreCase("J");
        Membership membership = active ? new ActiveMembership() : new PassiveMembership();
        Member member = new Member(name, phoneNumber, birthday, membership);
        memberManager.addMember(member);

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
