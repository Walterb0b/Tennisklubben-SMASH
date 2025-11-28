package main.java.logic;

import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.tournaments.PlayerResult;
import main.java.tournaments.ResultOutcome;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerStats {

    private ResultManager resultManager;

    public PlayerStats(ResultManager resultManager){
        this.resultManager = resultManager;
    }

    public int getWins(Member m, Disciplines d){
        return (int) resultManager.getResultsForPlayer(m).stream().
                filter(r -> r.getDiscipline() == d).
                filter(r -> r.getOutcome() == ResultOutcome.VUNDET).
                count();
    }

    public int getLosses(Member m, Disciplines d){
        return (int) resultManager.getResultsForPlayer(m).stream().
                filter(r -> r.getDiscipline() == d).
                filter(r -> r.getOutcome() == ResultOutcome.TABT).
                count();
    }

    public double getWinRate(Member m, Disciplines d){
        int wins = getWins(m, d);
        int losses = getLosses(m, d);
        int total = wins + losses;

        if(total == 0) return 0;
        return (wins * 100.0) / total;
    }

    public List<Member> getTop5(Disciplines d){
        return resultManager.getAllResults().stream().filter(r -> r.getDiscipline() == d && r.getOutcome() == ResultOutcome.VUNDET).
                collect(Collectors.groupingBy(PlayerResult::getPlayer, Collectors.counting())).
                entrySet().stream().sorted((a, b) -> Long.compare(b.getValue(), a.getValue())).
                limit(5).map(Map.Entry::getKey).toList();
    }
}
