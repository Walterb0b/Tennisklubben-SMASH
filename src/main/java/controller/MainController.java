package main.java.controller;

import main.java.logic.*;
import main.java.membership.StamDataManager;
import main.java.util.ScannerHelper;
import main.java.logic.TournamentManager;
import main.java.util.FileHandler;


public class MainController {
    private ScannerHelper sc;
    private FileHandler fh;
    private MemberController memberController;
    private FinanceController financeController;
    private CoachController coachController;
    private TournamentController tournamentController;

    public MainController(ScannerHelper sc, FileHandler fh, MemberManager memberManager, StamDataManager stamDataManager, PaymentManager paymentManager, ResultManager resultManager, TournamentManager tournamentManager, PlayerStats playerStats, RatingService ratingService){
        this.sc = sc;
        this.fh = fh;
        this.memberController = new MemberController(sc, fh, memberManager, stamDataManager, paymentManager);
        this.financeController = new FinanceController(sc, fh, memberManager, paymentManager);
        this.coachController = new CoachController(sc, memberManager, resultManager, playerStats, ratingService, fh);
        this.tournamentController = new TournamentController(sc, fh, memberManager, tournamentManager, coachController, resultManager, ratingService);
    }

    public void run(){
        boolean running = true;
        while(running){
            sc.printMainMenu();
            int choice = sc.navigateMenu(9);

            switch (choice){
                case 1:
                    memberController.run();
                    break;
                case 2:
                    financeController.run();
                    break;
                case 3:
                    coachController.runStats();
                    break;
                case 4:
                    coachController.runResult();
                    break;
                case 5:
                    tournamentController.run();
                    break;
                case 9:
                    fh.saveMembersToCSV();
                    fh.savePaymentsToCSV();
                    fh.savePlayerStatsToCSV();
                    fh.saveResultsToCSV();
                    fh.saveTournamentsToCSV();
                    running = false;
                    System.out.println("Farvel!");
                    break;
                default:
                    System.out.println("Ugyldigt valg");
            }
        }
    }
}
