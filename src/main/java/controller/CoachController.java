package main.java.controller;

import main.java.logic.MemberManager;
import main.java.util.ScannerHelper;

public class CoachController {

    private ScannerHelper sc;
    private MemberManager memberManager;


    public CoachController(ScannerHelper sc, MemberManager memberManager){
        this.sc = sc;
        this.memberManager = memberManager;

    }

    public void runStats() {
        boolean running = true;

        while (running) {
            sc.printStatsMenu();
            int choice = sc.navigateMenu(3);


            switch (choice) {
                case 1 :
                //CoachManager.topFive();
                    System.out.println("Top 5");
                        break;
                case 2 :
                    //CoachManager.playerStats();
                    System.out.println("Statistik for spiller");
                        break;

                case 3 :
                // CoachManager.playerMatches();
                    System.out.println("Turneringskampe for spiller");
                        break;

                case 0 :
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg");
            }
        }

    }


    public void runResult() {
        boolean running = true;

        while (running) {
            sc.printResultMenu();
            int choice = sc.navigateMenu(6);


            switch (choice) {
                case 1 :
                // CoachManager.addMatch();
                    System.out.println("Du har valgt tilføj turneringskamp");
                    break;
                case 2 :
                // CoachManager.editMatch();
                    System.out.println("Rediger turneringskamp");
                break;
                case 3 :
                // CoachManager.removeMatch();
                    System.out.println("Slet turneringskamp");
                break;
                case 4 :
                // CoachManager.addTrainingResult();
                    System.out.println("Tilføj træningsresultat");
                break;
                case 5 :
                // CoachManager.editTrainingResult();
                    System.out.println("Rediger træningsresultat");
                break;
                case 6 :
                // CoachManager.removeTrainingResult();
                    System.out.println("Slet træningsresultat");
                break;
                case 0 :
                    running = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg");
            }
        }

    }

}
