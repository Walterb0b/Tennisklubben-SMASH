

package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.RatingService;
import main.java.logic.ResultManager;
import main.java.logic.TournamentManager;
import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.tournaments.*;
import main.java.util.FileHandler;
import main.java.util.ScannerHelper;
import main.java.util.Validator;


import java.time.LocalDate;
import java.util.*;

/**
 * Controller der håndterer turneringer
 * oprettelse, vise turneringer, tilmeld spiller
 */
public class TournamentController {

    private ScannerHelper sc;
    private FileHandler fh;
    private MemberManager memberManager;
    private TournamentManager tournamentManager;
    private CoachController coachController;
    private ResultManager resultManager;
    private RatingService ratingService;

    /**
     *
     * @param sc,                håndterer brugerinput
     * @param memberManager,     håndterer medlemmer
     * @param tournamentManager, håndterer turneringer
     */
    public TournamentController(ScannerHelper sc, FileHandler fh, MemberManager memberManager, TournamentManager tournamentManager, CoachController coachController, ResultManager resultManager, RatingService ratingService) {
        this.sc = sc;
        this.fh = fh;
        this.memberManager = memberManager;
        this.tournamentManager = tournamentManager;
        this.coachController = coachController;
        this.resultManager = resultManager;
        this.ratingService = ratingService;
    }

    /**
     * loop for turneringsmenu
     */
    public void run() {
        boolean back = false;

        while (!back) {
            sc.printTournamentMenu();
            int choice = sc.navigateMenu(4);   // 1–3 + 0

            switch (choice) {
                case 1:
                    createTournament();
                    fh.saveTournamentsToCSV();
                    break;
                case 2:
                    addMatchToTournament();
                    fh.saveTournamentsToCSV();
                    break;
                case 3:
                    showAllTournaments();
                    break;
                case 4:
                    showTournamentMatches();
                    break;
                case 0:
                    back = true;
                    System.out.println("Går tilbage til hovedmenu ");
                    break;
                default:
                    System.out.println("FEJL, prøv igen senere ");
            }
        }
    }

    private void addMatchToTournament() {
        Tournament t = selectTournament();

        if (t == null) return;

        sc.printLn("\nTilføjer kamp til turnering: " + t.getName());

        Disciplines discipline = coachController.askDiscipline();

        sc.printLn("Er det en intern eller ekstern turneringskamp?");
        sc.printLn("1. Intern");
        sc.printLn("2. Ekstern");
        int choice = sc.askNumber("Vælg: ");

        MatchLocation location = switch (choice) {
            case 1 -> MatchLocation.INTERN;
            case 2 -> MatchLocation.EKSTERN;
            default -> {
                sc.printLn("Ugyldigt valg, bruger ekstern som standard");
                yield MatchLocation.EKSTERN;
            }
        };

        int perTeam = (discipline == Disciplines.SINGLE) ? 1 : 2;

        Set<Integer> forbidden = new HashSet<>();
        List<Member> teamA = new ArrayList<>();
        List<Member> teamB = new ArrayList<>();
        List<Member> clubPlayers = new ArrayList<>();

        if (location == MatchLocation.INTERN) {
            sc.printLn("Vælg spillere til hold A:");
            coachController.pickPlayersForTeam(perTeam, MatchType.TURNERING, teamA, forbidden);

            sc.printLn("Vælg spillere til hold B:");
            coachController.pickPlayersForTeam(perTeam, MatchType.TURNERING, teamB, forbidden);

            String score = sc.askQuestion("Indtast score (f.eks. 6-4 5-7 6-4)");
            sc.printLn("Hvilket hold vandt?");
            sc.printLn("1. Team A");
            sc.printLn("2. Team B");

            int winnerTeam = sc.askNumber("Vælg: ");

            int matchID = resultManager.addInternalMatchResult(teamA, teamB, discipline, MatchType.TURNERING, winnerTeam, score, LocalDate.now());

            ratingService.updateAfterInternalMatch(teamA, teamB, winnerTeam, MatchType.TURNERING);

            tournamentManager.addMatchToTournament(t.getTournamentID(), matchID, resultManager);

            sc.printLn("Kamp: " + matchID + " blev tilføjet til " + t.getName());
        } else {
            sc.printLn("Vælg spillere fra klubben:");
            coachController.pickPlayersForTeam(perTeam, MatchType.TURNERING, clubPlayers, forbidden);

            String opponentInfo = sc.askQuestion("Beskrivelse af modstander(e)");
            String score = sc.askQuestion("Score (fx 6-4 3-6 7-5)");
            ResultOutcome outcome = resultManager.calculateOutcomeFromScore(score);
            boolean clubWon = (outcome == ResultOutcome.VUNDET);

            int matchId = resultManager.addExternalMatchResult(
                    clubPlayers, discipline, MatchType.TURNERING, opponentInfo, score, LocalDate.now()
            );

            ratingService.updateAfterExternalMatch(
                    clubPlayers, clubWon, MatchType.TURNERING
            );

            tournamentManager.addMatchToTournament(t.getTournamentID(), matchId, resultManager);
            sc.printLn("Kamp " + matchId + " tilføjet til " + t.getName());
        }
    }

    private Tournament selectTournament() {
        Collection<Tournament> all = tournamentManager.getAllTournaments();
        if (all.isEmpty()) {
            sc.printLn("Ingen turneringer oprettet.");
            return null;
        }

        for (Tournament t : all) {
            sc.printLn(t.getTournamentID() + " - " + t.getName());
        }
        int id = sc.askNumber("Vælg turnering ID: ");
        Tournament t = tournamentManager.getTournament(id);
        if (t == null) {
            sc.printLn("Ukendt turnering.");
        }
        return t;
    }

    /**
     * opretter turnering udfra brugerinput
     */
    private void createTournament() {
        System.out.println("Du har valgt at oprette en turnering");


        String name = sc.askQuestion("Indtast turneringsnavn");
        sc.printLn("Hvilket niveau er turneringen?");
        sc.printLn("1. Lokal");
        sc.printLn("2. Regional");
        sc.printLn("3. National");

        int choice = sc.askNumber("Vælg");

        TournamentLevel level = switch (choice) {
            case 1 -> TournamentLevel.LOCAL;
            case 2 -> TournamentLevel.REGIONAL;
            case 3 -> TournamentLevel.NATIONAL;
            default -> {
                sc.printLn("Ugyldigt valg, bruger lokal som default");
                yield TournamentLevel.LOCAL;
            }
        };


        LocalDate startDate = Validator.dateValidatorWithScanner(sc, "Indtast startdato i formatet DD/MM/YYYY: ");

        LocalDate endDate = Validator.dateValidatorWithScanner(sc, "Indtast slutdato i formatet DD/MM/YYYY: ");

        Tournament t = tournamentManager.createTournament(name, level, startDate, endDate);

        System.out.println("Turnering oprettet: " + t);
    }

    /**
     * printer turneringer skabt fra brugerinput
     */
    private void showAllTournaments() {
        System.out.println("\nDu har valgt at se alle turneringer");
        if(tournamentManager.tournaments.isEmpty()){
            sc.printLn("Der er ikke oprettet nogen turneringer");
            return;
        }
        tournamentManager.printAllTournaments();
    }

    private void showTournamentMatches() {
        Tournament t = selectTournament();  // metode til at vælge turnering
        if (t == null) return;

        List<Integer> matchIds = t.getMatchIDs();
        if (matchIds.isEmpty()) {
            sc.printLn("\nDer er ingen registrerede kampe i turneringen '" + t.getName() + "'.");
            return;
        }

        sc.printLn("\n=== Kampe i turneringen: " + t.getName() + " ===");

        for (int matchId : matchIds) {
            List<PlayerResult> results = resultManager.getResultsFromMatch(matchId);
            if (results.isEmpty()) continue;

            PlayerResult first = results.get(0); // én kamp = flere PlayerResults

            sc.printLn("\nKamp-ID: " + matchId);
            sc.printLn("Dato: " + first.getDate());
            sc.printLn("Disciplin: " + first.getDiscipline());
            sc.printLn("Type: " + first.getType());
            sc.printLn("Modstander(e): " + first.getOpponentInfo());
            sc.printLn("Score: " + first.getScore());

            // vis alle spillere i kampen
            sc.printLn("Spillere:");
            for (PlayerResult r : results) {
                sc.printLn(" - " + r.getPlayer().getName() + " (" + r.getOutcome() + ")");
            }
        }
    }

}