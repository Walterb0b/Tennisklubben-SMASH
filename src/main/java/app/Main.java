package main.java.app;

import main.java.controller.CoachController;
import main.java.controller.FinanceController;
import main.java.controller.MainController;
import main.java.logic.*;
import main.java.membership.*;
import main.java.util.ScannerHelper;
import main.java.membership.StamDataManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        runProgram();
        //runTest();

        }


    public static void runProgram() {
        //printMainMenu();

        MemberManager mm = new MemberManager();
        ScannerHelper sh = new ScannerHelper(mm);
        PaymentManager pm = new PaymentManager(mm);
        StamDataManager sdm = new StamDataManager(mm);
        ResultManager rm = new ResultManager();
        PlayerStats ps = new PlayerStats(rm, mm);
        RatingService rs = new RatingService();

        MainController mainController = new MainController(sh, mm, sdm, pm, rm, ps, rs);

        Member m1 = new Member("John Nielsen", "12345678",LocalDate.of(1968,10,22), new ActiveMembership(), new PlayPreference(true, new HashSet<>(List.of(Disciplines.SINGLE))));
        Member m2 = new Member("Rasmus Johnsen", "87126732",LocalDate.of(1954,11,21), new ActiveMembership(), new PlayPreference(false, new HashSet<>(List.of(Disciplines.SINGLE, Disciplines.DOUBLE))));
        Member m3 = new Member("Lizzie Okdal", "78249712",LocalDate.of(2005,3,20), new ActiveMembership (), new PlayPreference(true, new HashSet<>(List.of(Disciplines.SINGLE, Disciplines.DOUBLE, Disciplines.MIXDOUBLE))));
        mm.addMember(m1);
        mm.addMember(m2);
        mm.addMember(m3);
        pm.createSeasonQuarterPayment(2025);
        pm.createSeasonQuarterPayment(2026);




        mainController.run();







    }

    public static void runTest() {



    }
}