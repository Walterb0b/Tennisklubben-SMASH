package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.PaymentManager;
import main.java.membership.StamDataManager;
import main.java.util.ScannerHelper;
import main.java.logic.TournamentManager;


public class MainController {
    private ScannerHelper sc;
    private MemberController memberController;
    private FinanceController financeController;
    private CoachController coachController;
    private TournamentController tournamentController;

    public MainController(ScannerHelper sc, MemberManager memberManager, StamDataManager stamDataManager, PaymentManager paymentManager, TournamentManager tournamentManager){
        this.sc = sc;
        this.memberController = new MemberController(sc, memberManager, stamDataManager, paymentManager);
        this.financeController = new FinanceController(sc, memberManager, paymentManager);
        this.coachController = new CoachController(sc, memberManager);
        this.tournamentController = new TournamentController(sc, memberManager, tournamentManager);
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
                case 9:
                    running = false;
                    System.out.println("Farvel!");
                    break;
                default:
                    System.out.println("Ugyldigt valg");
            }
        }
    }
}
