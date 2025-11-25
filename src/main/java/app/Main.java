package main.java.app;

import main.java.controller.MainController;
import main.java.logic.MemberManager;
import main.java.membership.*;
import main.java.util.ScannerHelper;
import main.java.membership.StamDataManager;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        runProgram();

        }


    public static void runProgram() {
        //printMainMenu();
        ScannerHelper sh = new ScannerHelper();
        MemberManager mm = new MemberManager();
        StamDataManager sdm = new StamDataManager(mm);
        MainController mainController = new MainController(sh, mm, sdm);
        Member m1 = new Member("John Nielsen", "12345678",LocalDate.of(1968,10,22), new ActiveMembership());
        mm.addMember(m1);
        mainController.run();






        /*MemberManager mm = new MemberManager();

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

         */
    }
}