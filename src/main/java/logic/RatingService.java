package main.java.logic;

import main.java.membership.Member;
import main.java.tournaments.MatchType;

import java.util.ArrayList;
import java.util.List;

public class RatingService {

    //Elo: Kun for turneringskampe
    private static final int K = 24;

    //SMASH-point: Kun for træningskampe
    private static final int trainingWinPoints = 3;
    private static final int trainingLossPoints = 1;

    //=========================
    //===   ELO -RATING     ===
    //=========================

    /**
     * Opdaterer Elo-rating eller Smashpoints efter intern kamp
     * @param teamA Liste over spillerne på det ene hold
     * @param teamB Liste over spillerne på det andet hold
     * @param winningTeam Bestemmer hvem der har vundet
     * @param type Er det en træningkamp eller turneringskamp
     */
    public void updateAfterInternalMatch(List<Member> teamA, List<Member> teamB, int winningTeam, MatchType type){
        if(type == MatchType.TURNERING){
            updateEloAfterInternalTournament(teamA, teamB, winningTeam);
        } else if (type == MatchType.TRÆNING){
            updateSmashPointsAfterInternalTraining(teamA, teamB, winningTeam);
        }
    }

    /**
     * Opdaterer Elo-rating eller Smashpoints efter intern kamp
     * @param clubPlayers Liste over spillerne fra klubben
     * @param clubWon True hvis holdet fra klubben har vundet
     * @param type Er det en træningkamp eller turneringskamp
     */
    public void updateAfterExternalMatch(List<Member> clubPlayers, boolean clubWon, MatchType type){
        if(type == MatchType.TURNERING){
            updateEloAfterExternalTournament(clubPlayers, clubWon);
        } else if (type == MatchType.TRÆNING) {
            updateSmashPointsAfterExternalTraining(clubPlayers, clubWon);
        }
    }

    private void updateEloAfterInternalTournament(List<Member> teamA, List<Member> teamB, int winningTeam) {

        List<Member> compA = filterCompetetive(teamA);
        List<Member> compB = filterCompetetive(teamB);

        if(compA.isEmpty() && compB.isEmpty()){
            return;
        }

        double ratingA = averageRating(compA);
        double ratingB = averageRating(compB);

        double expectedA = expectedScore(ratingA, ratingB);
        double expectedB = 1.0 - expectedA;

        double scoreA = (winningTeam == 1) ? 1.0 : 0.0;
        double scoreB = (winningTeam == 2) ? 1.0 : 0.0;

        double deltaA = K * (scoreA - expectedA);
        double deltaB = K * (scoreB - expectedB);

        for(Member m : compA){
            int oldRating = ensureEloInitialized(m);
            int newRating = (int) Math.round(oldRating + deltaA);
            m.setEloRating(newRating);
        }

        for(Member m : compB){
            int oldRating = ensureEloInitialized(m);
            int newRating = (int) Math.round(oldRating + deltaB);
            m.setEloRating(newRating);
        }
    }

    private void updateEloAfterExternalTournament(List<Member> clubPlayers, boolean clubWon) {

        List<Member> compPlayers = filterCompetetive(clubPlayers);
        if(compPlayers.isEmpty()) return;

        double teamRating = averageRating(compPlayers);
        double opponentRating = 1500;

        double expected = expectedScore(teamRating, opponentRating);
        double score = clubWon ? 1.0 : 0.0;
        double delta = K * (score - expected);

        for(Member m : compPlayers){
            int oldRating = ensureEloInitialized(m);
            int newRating = (int) Math.round(oldRating + delta);
            m.setEloRating(newRating);
        }
    }

    //Helpers til Elo udregning
    private int ensureEloInitialized(Member m) {
        if(m.getEloRating() == null){
            m.setEloRating(1500);
        }
        return m.getEloRating();
    }

    private double expectedScore(double ratingA, double ratingB) {
        double diff = (ratingB - ratingA) / 400.0;
        return 1.0 / (1.0 + Math.pow(10, diff));
    }

    private double averageRating(List<Member> players) {
        if(players.isEmpty()){
            return 1500;
        }
        int sum = 0;
        for(Member m : players){
            sum += ensureEloInitialized(m);
        }
        return sum / (double) players.size();
    }

    private List<Member> filterCompetetive(List<Member> players) {
        List<Member> result = new ArrayList<>();
        for(Member m : players){
            if(m.isCompetetive())
                result.add(m);
        }
        return result;
    }

    //=========================
    //===   SMASH POINTS    ===
    //=========================

    /**
     * Opdaterer smash point efter intern træning
     * @param teamA Medlemmer på det ene hold
     * @param teamB Medlemmer på det andet hold
     * @param winningTeam Bestemmer vinderholdet
     */
    private void updateSmashPointsAfterInternalTraining(List<Member> teamA, List<Member> teamB, int winningTeam) {
        List<Member> winners = (winningTeam == 1) ? teamA : teamB;
        List<Member> losers = (winningTeam == 1) ? teamB : teamA;

        for(Member m : winners){
            m.addSmashPoints(trainingWinPoints);
        }
        for(Member m : losers){
            m.addSmashPoints(trainingLossPoints);
        }
    }

    /**
     * Kan bruges til at opdatere smash point ved træninger mod eksterne spillere
     * @param clubPlayers Medlemmer fra klubben
     * @param clubWon True hvis holdet fra klubben vandt
     */
    private void updateSmashPointsAfterExternalTraining(List<Member> clubPlayers, boolean clubWon) {

        for(Member m : clubPlayers){
            if(clubWon){
                m.addSmashPoints(trainingWinPoints);
            } else {
                m.addSmashPoints(trainingLossPoints);
            }
        }
    }
}
