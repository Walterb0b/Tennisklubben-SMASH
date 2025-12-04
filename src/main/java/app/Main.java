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
        }


    public static void runProgram() {
        MemberManager mm = new MemberManager();
        ScannerHelper sh = new ScannerHelper(mm);
        PaymentManager pm = new PaymentManager(mm);
        StamDataManager sdm = new StamDataManager(mm);
        ResultManager rm = new ResultManager();
        TournamentManager tm = new TournamentManager();
        PlayerStats ps = new PlayerStats(rm, mm);
        RatingService rs = new RatingService();

        FileHandler fh = new FileHandler(mm, pm, rm, tm, ps);

        MainController mainController = new MainController(sh, fh, mm, sdm, pm, rm, tm,ps, rs);



        fh.createMembersFromCSV();
        fh.readResultsCSV();
        fh.readPaymentsCSV();


        //fh.saveMembersToCSV();
        //fh.savePaymentsToCSV();
        fh.savePlayerStatsToCSV();









        mainController.run();







    }

}