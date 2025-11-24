package main.java.controller;

import main.java.membership.*;
import main.java.util.ScannerHelper;

import java.time.LocalDate;
import java.util.Scanner;

public class MemberController {
    private ScannerHelper sc = new ScannerHelper();
    private MemberManager memberManager;

    public MemberController(ScannerHelper sc, MemberManager memberManager){
        this.sc = sc;
        this.memberManager = memberManager;
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

}
