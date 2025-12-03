

package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.TournamentManager;
import main.java.membership.Disciplines;
import main.java.tournaments.Tournament;
import main.java.tournaments.TournamentEntry;
import main.java.util.ScannerHelper;
import main.java.util.Validator;

import java.time.LocalDate;

/**
 * Controller der håndterer turneringer
 * oprettelse, vise turneringer, tilmeld spiller
 */
public class TournamentController {

    private ScannerHelper sc;
    private MemberManager memberManager;
    private TournamentManager tournamentManager;

    /**
     *
     * @param sc, håndterer brugerinput
     * @param memberManager, håndterer medlemmer
     * @param tournamentManager, håndterer turneringer
     */
    public TournamentController(ScannerHelper sc, MemberManager memberManager, TournamentManager tournamentManager) {
        this.sc = sc;
        this.memberManager = memberManager;
        this.tournamentManager = tournamentManager;
    }

    /**
     * loop for turneringsmenu
     */
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

    /**
     * printer turneringsmenu
     */
    private void printTournamentMenu() {
        System.out.println("\n=== TURNERINGS-MENU ===");
        System.out.println("\n 1. Opret turnering");
        System.out.println("\n 2. Vis alle turneringer");
        System.out.println("\n 3. Tilmeld spiller til turnering");
        System.out.println("\n 0. Tilbage");}

    /**
     * opretter turnering udfra brugerinput
     */
    private void createTournament() {
        System.out.println("Du har valgt at oprette en turnering");

        System.out.println("Indtast turnerings-ID: ");
        int id = sc.askNumber(9999);

        String name = sc.askQuestion("Indtast turneringsnavn: ");
        String location = sc.askQuestion("Indtast lokation: ");

        LocalDate startDate = Validator.dateValidatorWithScanner(sc, "Indtast startdato i formatet DD/MM/YYYY: ");

        LocalDate endDate = Validator.dateValidatorWithScanner(sc, "Indtast slutdato i formatet DD/MM/YYYY: ");

        Tournament t = new Tournament(id, name, location, startDate, endDate);
        tournamentManager.addTournament(t);

        System.out.println("Turnering oprettet: " + t);
    }

    /**
     * printer turneringer skabt fra brugerinput
     * gemmer data om turneringer
     */
    private void showAllTournaments() {
        System.out.println("\nDu har valgt at se alle turneringer");
        tournamentManager.printAllTournaments();}

    /**
     * tilmelder spiller til turnering
     * laver entry id for medlem
     */
    private void registerTournamentEntry() {
        System.out.println("Du har valgt at tilmelde en spiller til en turnering ");

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
