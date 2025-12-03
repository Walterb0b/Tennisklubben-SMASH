package main.java.logic;

import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.tournaments.MatchType;
import main.java.tournaments.PlayerResult;
import main.java.tournaments.ResultOutcome;
import main.java.util.FileHandler;
import main.java.util.Formatter;
import main.java.logic.MemberManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultManager {
    private final List<PlayerResult> results = new ArrayList<>();
    private int nextMatchID = 1;

    public List<PlayerResult> getAllResults() {
        return results;
    }

    public void addExternalMatchResult(List<Member> clubPlayers, Disciplines discipline, MatchType type, String opponentInfo, String score, LocalDate date) {
        int matchID = nextMatchID++;

        ResultOutcome outcome = calculateOutcomeFromScore(score);

        for (Member player : clubPlayers) {
            results.add(new PlayerResult(matchID, player, discipline, type, outcome, opponentInfo, score, date));
        }
    }

    public void addInternalMatchResult(List<Member> teamA, List<Member> teamB, Disciplines discipline, MatchType type, int winningTeam, String score, LocalDate date) {
        int matchID = nextMatchID++;

        List<Member> winners = (winningTeam == 1) ? teamA : teamB;
        List<Member> losers = (winningTeam == 1) ? teamB : teamA;

        for (Member w : winners) {
            results.add(new PlayerResult(matchID, w, discipline, type, ResultOutcome.VUNDET, buildOpponentNameString(losers), score, date));
        }
        for (Member l : losers) {
            results.add(new PlayerResult(matchID, l, discipline, type, ResultOutcome.TABT, buildOpponentNameString(winners), score, date));
        }
    }

    public List<PlayerResult> getResultsFromMatch(int matchID) {
        return results.stream().filter(r -> r.getMatchID() == matchID).toList();
    }

    public List<PlayerResult> getResultsForPlayer(Member member) {
        return results.stream().filter(r -> r.getPlayer() == member).toList();
    }

    public ResultOutcome calculateOutcomeFromScore(String score) {
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


    /*public void deleteResults(int matchID){
        if(results.contains() {
            results.removeAll(getResultsFromMatch(matchID));
        }

    }*/

    private String buildOpponentNameString(List<Member> opponents) {
        return opponents.stream()
                .map(Member::getName)
                .collect(Collectors.joining(", "));
    }

    public void saveResultsToCSV() {
        ArrayList<String> parts = new ArrayList<>();
        final String delimiter = ";";

        for (PlayerResult r : results) {
            parts.add(
                    r.getMatchID() + delimiter
                            + r.getPlayer().getMemberID() + delimiter
                            + r.getDiscipline() + delimiter
                            + r.getType() + delimiter
                            + r.getOutcome() + delimiter
                            + r.getOpponentInfo() + delimiter
                            + r.getScore() + delimiter
                            + Formatter.localDateToString(r.getDate()) + "\n");

        }
        FileHandler.writeFile(parts, "resultDatabase.csv");
    }

    public void readResultsCSV() {
        ArrayList<String[]> fileContent = FileHandler.readFromFile("resultDatabase.csv");

        for (String[] parts : fileContent) {
            int matchID = Integer.parseInt(parts[0]);
            int memberID = Integer.parseInt(parts[1]);
            Disciplines discipline = Disciplines.valueOf(parts[2]);
            MatchType type = MatchType.valueOf(parts[3]);
            ResultOutcome outcome = ResultOutcome.valueOf(parts[4]);
            String opponents = parts[5];
            String score = parts[6];
            LocalDate date = Formatter.stringToLocalDate(parts[7]);

            Member m = memberManager.getMember(memberID);
            if (m == null) {
                System.out.println("Fejl ved indlæsning af resultater fra filen for medlem " + memberID);
                continue;
            }

            results.add(new PlayerResult(matchID, m, discipline, type, outcome, opponents, score, date));

            //nextMatchID = Math.max(nextMatchID, matchID + 1);
        }

    }
}
