package main.java.logic;

import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.tournaments.MatchLocation;
import main.java.tournaments.MatchType;
import main.java.tournaments.PlayerResult;
import main.java.tournaments.ResultOutcome;
import main.java.util.FileHandler;
import main.java.util.Formatter;
import main.java.logic.MemberManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ResultManager {
    private final List<PlayerResult> results = new ArrayList<>();
    private int nextMatchID = 1;

    public List<PlayerResult> getAllResults() {
        return results;
    }

    /**
     * Tilføjer en ekstern kamp
     * @param clubPlayers liste af spillere fra klubben
     * @param discipline hvilken disciplin der er blevet spillet
     * @param type hvilken slags kamptype (i ekstern kamp altid turnering lige nu)
     * @param opponentInfo String med info omkring modstander(ne)
     * @param score kampens score
     * @param date kampens dato
     */
    public int addExternalMatchResult(List<Member> clubPlayers, Disciplines discipline, MatchType type, MatchLocation location, String opponentInfo, String score, LocalDate date) {
        int matchID = nextMatchID++;

        ResultOutcome outcome = calculateOutcomeFromScore(score);

        for (Member player : clubPlayers) {
            results.add(new PlayerResult(player, discipline, type, outcome, location, opponentInfo, score, date));
        }

        return matchID;
    }

    /**
     * Tilføjer en intern kamp
     * @param teamA liste af spillere på team A
     * @param teamB liste af spillere på team B
     * @param discipline hvilken disciplin der er blevet spillet
     * @param type hvilken slags kamptype det er (træning eller turnering)
     * @param winningTeam hvem vandt
     * @param score scoren for kampen
     * @param date kampens dato
     */
    public int addInternalMatchResult(List<Member> teamA, List<Member> teamB, Disciplines discipline, MatchType type, int winningTeam, String score, LocalDate date){
        int matchID = nextMatchID++;

        List<Member> winners = (winningTeam == 1) ? teamA : teamB;
        List<Member> losers = (winningTeam == 1) ? teamB : teamA;

        for (Member w : winners) {
            results.add(new PlayerResult(w, discipline, type, ResultOutcome.VUNDET, MatchLocation.INTERN, buildOpponentNameString(losers), score, date));
        }
        for (Member l : losers) {
            results.add(new PlayerResult(l, discipline, type, ResultOutcome.TABT, MatchLocation.INTERN, buildOpponentNameString(winners), score, date));
        }

        return matchID;
    }

    /**
     * Finder resultater fra en kamp ud fra et matchID
     * @param matchID ID på den kamp man gerne vil finde
     * @return Returnerer en liste af alle resultater fra en kamp med matchID
     */
    public List<PlayerResult> getResultsFromMatch(int matchID){
        return results.stream().filter(r -> r.getMatchID() == matchID).toList();
    }

    /**
     * Finder kampresultater for et specifikt medlem
     * @param member Det medlem vi gerne vil se resultater for
     * @return Liste af resultater for medlemmet
     */
    public List<PlayerResult> getResultsForPlayer(Member member){
        return results.stream().filter(r -> r.getPlayer() == member).toList();
    }

    /**
     * Hjælper metode til at finde resultatet for kampen ud fra en score
     * @param score scoren for kampen
     * @return ResultOutcome.VUNDET eller ResultOutcome.TABT
     */
    public ResultOutcome calculateOutcomeFromScore(String score){
        int setsWon = 0;
        int setsLost = 0;

        String[] sets = score.trim().split("\\s+");

        for (String set : sets) {
            String[] parts = set.split("-");
            if (parts.length != 2) {
                continue;
            }

            try {
                int ours = Integer.parseInt(parts[0]);
                int theirs = Integer.parseInt(parts[1]);

                if (ours > theirs) {
                    setsWon++;
                } else {
                    setsLost++;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Fejl i scoreinput");
            }
        }
        return (setsWon > setsLost) ? ResultOutcome.VUNDET : ResultOutcome.TABT;
    }

    /**
     * Hjælper metode til at lave modstanderne for en intern kamp
     * @param opponents Listen af modstandere
     * @return String med modstanderne
     */
    private String buildOpponentNameString(List<Member> opponents) {
        return opponents.stream()
                .map(Member::getName)
                .collect(Collectors.joining(", "));
    }

    /**
     * Sletter en kamp
     * @param matchID ID for den kamp der skal slettes
     * @return True hvis kampen er slettet
     */
    public boolean deleteMatch(int matchID) {
        return results.removeIf(r -> r.getMatchID() == matchID);
    }

    public Set<Integer> getAllMatchIDs() {
        Set<Integer> ids = new HashSet<>();
        for (PlayerResult r : results) {
            ids.add(r.getMatchID());
        }
        return ids;
    }

    /**
     * Bruges til at opdatere modstander info og score for en kamp
     * @param matchID ID på den kamp der skal redigeres
     * @param newScore Den nye score
     * @param newOpponentInfo Ny info om modstander
     * @return True efter ændringer
     */
    public boolean updateMatchScoreAndOpponent(Integer matchID, String newScore, String newOpponentInfo) {
        List<PlayerResult> matchResults = getResultsFromMatch(matchID);
        if (matchResults.isEmpty()) return false;

        for (PlayerResult r : matchResults) {
            r.setScore(newScore);
            r.setOpponentInfo(newOpponentInfo);
            r.setOutcome(calculateOutcomeFromScore(newScore));
        }
        return true;
    }
}
