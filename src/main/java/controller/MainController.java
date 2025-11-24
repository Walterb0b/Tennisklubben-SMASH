package main.java.controller;

import main.java.membership.MemberManager;
import main.java.util.ScannerHelper;

public class MainController {
    private ScannerHelper sc;
    private MemberController memberController;
    private FinanceController financeController;
    private CoachController coachController;

    public MainController(ScannerHelper sc, MemberManager memberManager){
        this.sc = sc;
        this.memberController = new MemberController(sc, memberManager);
        this.financeController = new FinanceController(sc, memberManager, paymentManager);
        this.coachController = new CoachController(sc, coachService, memberManager);
    }

    public void run(){
        boolean running = true;
        while(running){
            sc.printMainMenu();
            int choice = sc.navigateMenu(4);

            switch (choice){
                case 1: memberController.run();
                case 2: financeController.run();
                case 3: coachController.run();
                case 4: coachController.run();
                case 0:
                    System.out.println("Farvel!");
                    running = false;
                default:
                    System.out.println("Ugyldigt valg");
            }
        }
    }
}
