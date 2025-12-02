

package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.TournamentManager;
import main.java.membership.Disciplines;
import main.java.tournaments.Tournament;
import main.java.tournaments.TournamentEntry;
import main.java.util.ScannerHelper;

import java.time.LocalDate;

public class TournamentController {

    private ScannerHelper sc;
    private MemberManager memberManager;
    private TournamentManager tournamentManager;

    public TournamentController(ScannerHelper sc, MemberManager memberManager, TournamentManager tournamentManager) {
        this.sc = sc;
        this.memberManager = memberManager;
        this.tournamentManager = tournamentManager;
    }

    public void run() {
        boolean back = false;

        while (!back) {
            printTournamentMenu();
            int choice = sc.navigateMenu(3);   // 1–3 + 0

            switch (choice) {case 1: createTournament();
                    break;
                case 2: showAllTournaments();
                    break;
                case 3: registerTournamentEntry();
                    break;
                case 0: back = true;
                    System.out.println("Går tilbage til hovedmenu ");
                    break;
                default:
                    System.out.println("FEJL, prøv igen senere ");
            }
        }
    }

    private void printTournamentMenu() {
        System.out.println("\n=== TURNERINGS-MENU ===");
        System.out.println("1. Opret turnering");
        System.out.println("2. Vis alle turneringer");
        System.out.println("3. Tilmeld spiller til turnering");
        System.out.println("0. Tilbage");
    }

    private void createTournament() {
        System.out.println("Du har valgt at oprette en turnering");

        System.out.println("Indtast turnerings-ID: ");
        int id = sc.askNumber(9999);

        String name = sc.askQuestion("Indtast turneringsnav: ");
        String location = sc.askQuestion("Indtasst lokation: ");

        String startInput = sc.askQuestion("Indtasst startdato i formatet YYYY-MM-DD: ");
        String endInput   = sc.askQuestion("Indtast slutdato i formatet YYYY-MM-DD: ");

        LocalDate startDate = LocalDate.parse(startInput);
        LocalDate endDate   = LocalDate.parse(endInput);

        Tournament t = new Tournament(id, name, location, startDate, endDate);
        tournamentManager.addTournament(t);

        System.out.println("Turnering oprettet: " + t);}

    private void showAllTournaments() {
        System.out.println("\nDu har valgt at se alle turneringer");
        tournamentManager.printAllTournaments();}

    private void registerTournamentEntry() {
        System.out.println("Du har valgt at tilmelde en spiller til en turnering");

        System.out.println("Indtast turnerings ID: ");
        int tournamentId = sc.askNumber(9999);

        Tournament t = tournamentManager.getTournament(tournamentId);
        if (t == null) {
            System.out.println("Ingen turnering med ID: " + tournamentId);
            return;}

        int memberID = sc.selectMemberFromList();

        System.out.println("Indtast entry ID: ");
        int entryId = sc.askNumber(9999);

        String discInput = sc.askQuestion("Vælg disciplin (SINGLE / DOUBLE / MIXED):");

        Disciplines discipline;
        try {discipline = Disciplines.valueOf(discInput);
        } catch (IllegalArgumentException e) {System.out.println("Ukendt disciplin. Går tilbage til turneringsmenuen.");
            return;}

        TournamentEntry entry = new TournamentEntry(entryId, memberID, t, discipline);
        t.addEntry(entry);

        System.out.println("Spiller tilmeldt turnering:");
        System.out.println(entry);}}
