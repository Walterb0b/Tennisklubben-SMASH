package main.java.logic;

import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.tournaments.PlayerResult;
import main.java.tournaments.ResultOutcome;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerStats {

    private ResultManager resultManager;
    private MemberManager memberManager;

    public PlayerStats(ResultManager resultManager, MemberManager memberManager){
        this.resultManager = resultManager;
        this.memberManager = memberManager;
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

    /**
     * Bruges til at lave en top 5 liste over en specifik disciplin
     * @return Liste over top 5 spillere med flest sejre i en specifik disciplin
     */
    public List<Member> getTop5ByDiscipline(Disciplines d){
        return resultManager.getAllResults().stream().filter(r -> r.getDiscipline() == d && r.getOutcome() == ResultOutcome.VUNDET).
                collect(Collectors.groupingBy(PlayerResult::getPlayer, Collectors.counting())).
                entrySet().stream().sorted((a, b) -> Long.compare(b.getValue(), a.getValue())).
                limit(5).map(Map.Entry::getKey).toList();
    }

    /**
     * Bruges til at lave en top 5 liste over Elo - rating
     * @return Liste over top 5 spillere med højeste Elo - rating
     */
    public List<Member> getTop5ByElo(){
        Collection<Member> allMembers = memberManager.getAllMembers().values();

        List<Member> list = new ArrayList<>(allMembers);

        list.removeIf(m -> !m.isCompetitive() || m.getEloRating() == null);

        Collections.sort(list, (m1, m2) -> Integer.compare(m2.getEloRating(), m1.getEloRating()));

        int limit = Math.min(5, list.size());

        return list.subList(0, limit);
    }

    /**
     * Bruges til at lave en top 5 liste over smash point
     * @return Liste over top 5 spillere med flest smash point
     */
    public List<Member> getTop5BySmashPoints(){
        Collection<Member> allMembers = memberManager.getAllMembers().values();

        List<Member> list = new ArrayList<>(allMembers);

        Collections.sort(list, (m1, m2) -> Integer.compare(m2.getSmashPoints(), m1.getSmashPoints()));

        int limit = Math.min(5, list.size());

        return list.subList(0, limit);
    }
}
