package main.java.app;

import main.java.controller.MainController;
import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.logic.TournamentManager;
import main.java.logic.*;
import main.java.util.FileHandler;
import main.java.util.ScannerHelper;
import main.java.membership.StamDataManager;

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

        FileHandler fh = new FileHandler(mm, pm, rm, tm, rs, ps);

        MainController mainController = new MainController(sh, fh, mm, sdm, pm, rm, tm,ps, rs);



        fh.createMembersFromCSV();
        fh.createResultsFromCSV();
        fh.createPaymentsFromCSV();
        fh.createTournamentsFromCSV();


        //fh.saveMembersToCSV();
        //fh.savePaymentsToCSV();
        //fh.savePlayerStatsToCSV();









        mainController.run();







    }

}