package main.java.app;

import main.java.logic.MemberManager;
import main.java.membership.*;
import main.java.util.ScannerHelper;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        runProgram();

        }


    public static void runProgram() {
        //printMainMenu();

        MemberManager mm = new MemberManager();

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


    public static void printMainMenu() {
        System.out.println();
        System.out.println(
                """
                           TENNISKLUBBEN SMASH
                           Velkommen!
                           """
               




        );
    }
}