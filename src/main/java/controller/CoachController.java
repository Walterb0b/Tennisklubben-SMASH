package main.java.controller;

import main.java.logic.MemberManager;
import main.java.logic.PlayerStats;
import main.java.logic.RatingService;
import main.java.logic.ResultManager;
import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.tournaments.MatchType;
import main.java.tournaments.ResultOutcome;
import main.java.util.ScannerHelper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CoachController {

    private ScannerHelper sc;
    private MemberManager memberManager;
    private ResultManager resultManager;
    private PlayerStats playerStats;
    private RatingService ratingService;


    public CoachController(ScannerHelper sc, MemberManager memberManager, ResultManager resultManager, PlayerStats playerStats, RatingService ratingService){
        this.sc = sc;
        this.memberManager = memberManager;
        this.resultManager = resultManager;
        this.playerStats = playerStats;
        this.ratingService = ratingService;
    }

    public void runStats() {
        boolean running = true;

        while (running) {
            sc.printStatsMenu();
            int choice = sc.navigateMenu(3);
            switch (choice) {
                case 1 :
                    topFive();
                    break;
                case 2 :
                    playerStats();
                    break;
                case 3 :
                // CoachManager.playerMatches();
                    System.out.println("Turneringskampe for spiller");
                        break;

                case 0 :
                    running = false;
                    break;
                default:
                    sc.printLn("Ugyldigt valg");
            }
        }
    }


    public void runResult() {
        boolean running = true;

        while (running) {
            sc.printResultMenu();
            int choice = sc.navigateMenu(6);
            switch (choice) {
                case 1 : addMatch();
                    break;
                case 2 :
                // CoachManager.editMatch();
                    System.out.println("Rediger turneringskamp");
                break;
                case 3 :
                removeMatch();
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
                    sc.printLn("Ugyldigt valg");
            }
        }

    }

    private void removeMatch() {
        sc.printLn("\n=== Fjern en kamp ===");
        int matchID = sc.askNumber("Hvilken kamp vil du gerne slette? (Indtast ID): ");

        try{
            //resultManager.deleteResults(matchID);
        } catch (IndexOutOfBoundsException IOE){
            sc.printLn("Der findes ingen kamp med dette ID");
        }

        sc.printLn("Kamp " + matchID + " er blevet slettet");
    }

    private void addMatch() {
        sc.printLn("\n=== Registrer kamp===");
        sc.printLn("1. Kamp mod en anden klub");
        sc.printLn("2. Intern kamp (Vores egne spillere mod hinanden)");

        int typeChoice = sc.askNumber(2);

        Disciplines discipline = askDiscipline();
        int perTeam = playersPerTeam(discipline);

        if(typeChoice == 1) {
            addExternalMatchFlow(discipline, perTeam);
        } else if (typeChoice == 2) {
            addInternalMatchFlow(discipline, perTeam);
        }
    }



    private void addInternalMatchFlow(Disciplines discipline, int perTeam) {
        sc.printLn("=== Intern kamp ===");

        List<Member> teamA = new ArrayList<>();
        sc.printLn("Vælg spillere til hold A");
        for(int i = 1; i <= perTeam; i++){
            Member m = memberManager.getMember(sc.selectMemberFromList());
            if(m == null) return;
            teamA.add(m);
        }

        List<Member> teamB = new ArrayList<>();
        sc.printLn("Vælg spillere til hold B");
        for(int i = 1; i <= perTeam; i++){
            Member m = memberManager.getMember(sc.selectMemberFromList());
            if(m == null) return;
            teamB.add(m);
        }

        MatchType type = MatchType.valueOf(sc.askQuestion("Er det en træning eller intern turnering").equalsIgnoreCase("Træning") ? "Træning".toUpperCase() : "Turnering".toUpperCase());

        String score = sc.askQuestion("Indtast score (fx. 6-4 7-5 6-2): ");

        sc.printLn("Hvilket hold vandt?");
        sc.printLn("1. Team A");
        sc.printLn("2. Team B");
        int winnerTeam = sc.askNumber(2);

        if(winnerTeam != 1 && winnerTeam != 2){
            sc.printLn("Ugyldigt valg - kamp ikke gemt");
        }

        resultManager.addInternalMatchResult(teamA, teamB, discipline, type, winnerTeam, score, LocalDate.now());

        ratingService.updateAfterInternalMatch(teamA, teamB, winnerTeam, type);

        sc.printLn("Intern kamp registreret og Elo eller Smash-points opdateret");
    }

    private void addExternalMatchFlow(Disciplines discipline, int perTeam) {
        sc.printLn("=== Kamp mod anden klub ===");

        List<Member> clubPlayers = new ArrayList<>();
        for(int i = 1; i <= perTeam; i++){
            Member m = memberManager.getMember(sc.selectMemberFromList());
            if(m == null)
                return;
            clubPlayers.add(m);
        }
        String opponentInfo = sc.askQuestion("Indtast info om modstander(e) fx. (Spiller X fra klub Y): ");
        String score = sc.askQuestion("Indtast score (fx. 6-4 7-5 6-2): ");

        resultManager.addExternalMatchResult(clubPlayers, discipline, MatchType.TURNERING, opponentInfo, score, LocalDate.now());

        boolean clubWon = (calculateOutComeFromScore(score) == ResultOutcome.VUNDET);

        ratingService.updateAfterExternalMatch(clubPlayers, clubWon, MatchType.TURNERING);
    }

    private ResultOutcome calculateOutComeFromScore(String score) {
        return resultManager.calculateOutcomeFromScore(score);
    }

    private int playersPerTeam(Disciplines discipline) {
        return (discipline == Disciplines.SINGLE) ? 1 : 2;
    }

    private Disciplines askDiscipline() {
        sc.printLn("Vælg disciplin:");
        sc.printLn("1. SINGLE");
        sc.printLn("2. DOUBLE");
        sc.printLn("3. MIX DOUBLE");

        int choice = sc.askNumber(3);

        return switch (choice) {
            case 1 -> Disciplines.SINGLE;
            case 2 -> Disciplines.DOUBLE;
            case 3 -> Disciplines.MIXDOUBLE;
            default -> {
                sc.printLn("Ugyldigt valg, bruger single som standard");
                yield Disciplines.SINGLE;
            }
        };
    }


    private void playerStats() {
        Member m = memberManager.getMember(sc.selectMemberFromList());

        resultManager.getResultsForPlayer(m);
    }

    private void topFive() {
        sc.printLn("\n=== Top 5 ===");
        sc.printLn("Hvad vil du gerne se top 5 over?");
        sc.printLn("1. Elo - ranking");
        sc.printLn("2. Smash point - ranking");
        sc.printLn("3. En specifik disciplin (flest sejre)");
        int choice = sc.askNumber("Vælg: ");

        switch (choice){
            case 1 : top5ByElo();
            break;
            case 2 : top5BySmashPoints();
            break;
            case 3 : top5ByDiscipline();
            break;
            default: sc.printLn("Ugyldigt valg");
            break;
        }

    }

    private void top5BySmashPoints() {
        List<Member> top5 = playerStats.getTop5BySmashPoints(memberManager.getAllMembersCollection());

        if (top5.isEmpty()) {
            sc.printLn("Ingen spillere har SMASH-point endnu.");
            return;
        }

        sc.printLn("\n=== TOP 5 SMASH-point ===");
        int rank = 1;
        for (Member m : top5) {
            sc.printLn(rank + ". " + m.getName() + " (SMASH: " + m.getSmashPoints() + ")");
            rank++;
        }
    }

    private void top5ByDiscipline(){
        Disciplines d = askDiscipline();

        List<Member> top5 = playerStats.getTop5ByDiscipline(d);

        sc.printLn("\n TOP 5 i " + d + ": ");

        int rank = 1;
        for(Member m : top5){
            sc.printLn(rank + ". " + m.getName() + " (ID: " + m.getMemberID() + ")");
            rank++;
        }
    }

    private void top5ByElo(){
        List<Member> top5 = playerStats.getTop5ByElo(memberManager.getAllMembersCollection());

        if (top5.isEmpty()) {
            sc.printLn("Ingen spillere med Elo-rating endnu.");
            return;
        }

        sc.printLn("\n=== TOP 5 ELO (kun competitive) ===");
        int rank = 1;
        for (Member m : top5) {
            sc.printLn(rank + ". " + m.getName() + " (Elo: " + m.getEloRating() + ")");
            rank++;
        }
    }
}
