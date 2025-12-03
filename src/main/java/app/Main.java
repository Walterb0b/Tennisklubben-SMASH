package main.java.app;

import main.java.controller.CoachController;
import main.java.controller.FinanceController;
import main.java.controller.MainController;
import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.logic.TournamentManager;
import main.java.logic.*;
import main.java.membership.*;
import main.java.tournaments.Tournament;
import main.java.util.FileHandler;
import main.java.util.Formatter;
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
        FileHandler fh = new FileHandler(mm);
        PaymentManager pm = new PaymentManager(mm);
        StamDataManager sdm = new StamDataManager(mm);
        ResultManager rm = new ResultManager();
        TournamentManager tm = new TournamentManager();
        PlayerStats ps = new PlayerStats(rm, mm);
        RatingService rs = new RatingService();

        MainController mainController = new MainController(sh, mm, sdm, pm, rm, tm,ps, rs);

        Member m1 = new Member("John Nielsen", "12345678",LocalDate.of(1968,10,22), LocalDate.of(2025,1,1), new ActiveMembership());
        Member m2 = new Member("Rasmus Johnsen", "87126732",LocalDate.of(1954,11,21), LocalDate.of(2025,1,1), new ActiveMembership());
        Member m3 = new Member("Lizzie Okdal", "78249712",LocalDate.of(2005,3,20), LocalDate.of(2025,1,1), new ActiveMembership());
        Member m4 = new Member("Preben Kaas", "14085287",LocalDate.of(1983,3,20), LocalDate.of(2025,1,1), new PassiveMembership());
        Member m5 = new Member("Olga Findsen", "13082587",LocalDate.of(2000,4,1), LocalDate.of(2025,11,1), new ActiveMembership());
        mm.addMember(m1);
        mm.addMember(m2);
        mm.addMember(m3);
        mm.addMember(m4);
        mm.addMember(m5);
        pm.createSeasonQuarterPayment(2025);
        pm.createSeasonQuarterPayment(2026);

        fh.saveMembersToCSV();




        mainController.run();







    }

    public static void runTest() {



    }
}