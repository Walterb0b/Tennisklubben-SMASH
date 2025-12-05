package main.java.logic;

import main.java.membership.Member;
import main.java.tournaments.MatchType;
import main.java.tournaments.ResultOutcome;

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
     *
     * @param teamA
     * @param teamB
     * @param winningTeam
     * @param type
     */
    // Opdaterer Elo eller SmashPoints efter intern kamp
    public void updateAfterInternalMatch(List<Member> teamA, List<Member> teamB, int winningTeam, MatchType type) {
        if (type == MatchType.TURNERING) {
            updateEloAfterInternalTournament(teamA, teamB, winningTeam);
        } else if (type == MatchType.TRÆNING) {
            updateSmashPointsAfterInternalTraining(teamA, teamB, winningTeam);
        }
    }

    // Opdaterer Elo eller SmashPoints efter ekstern kamp
    public void updateAfterExternalMatch(List<Member> clubPlayers, ResultOutcome outcome, MatchType type) {
        if (type == MatchType.TURNERING) {
            updateEloAfterExternalTournament(clubPlayers, outcome);
        } else if (type == MatchType.TRÆNING) {
            updateSmashPointsAfterExternalTraining(clubPlayers, outcome);
        }
    }

    // =====================
    // INTERNAL TOURNAMENT
    // =====================
    private void updateEloAfterInternalTournament(List<Member> teamA, List<Member> teamB, int winningTeam) {
        List<Member> compA = filterCompetitive(teamA);
        List<Member> compB = filterCompetitive(teamB);

        if (compA.isEmpty() && compB.isEmpty()) return;

        double ratingA = averageRating(compA);
        double ratingB = averageRating(compB);

        double expectedA = expectedScore(ratingA, ratingB);
        double expectedB = 1.0 - expectedA;

        double scoreA = (winningTeam == 1) ? 1.0 : 0.0;
        double scoreB = (winningTeam == 2) ? 1.0 : 0.0;

        // Fordel delta på hver spiller
        double deltaA = K * (scoreA - expectedA) / Math.max(1, compA.size());
        double deltaB = K * (scoreB - expectedB) / Math.max(1, compB.size());

        for (Member m : compA) {
            int oldRating = ensureEloInitialized(m);
            m.setEloRating((int) Math.round(oldRating + deltaA));
        }

        for (Member m : compB) {
            int oldRating = ensureEloInitialized(m);
            m.setEloRating((int) Math.round(oldRating + deltaB));
        }
    }

    // =====================
    // EXTERNAL TOURNAMENT
    // =====================
    private void updateEloAfterExternalTournament(List<Member> clubPlayers, ResultOutcome outcome) {
        List<Member> compPlayers = filterCompetitive(clubPlayers);
        if (compPlayers.isEmpty()) return;

        double teamRating = averageRating(compPlayers);
        double opponentRating = 1500; // standard modstander Elo

        double expected = expectedScore(teamRating, opponentRating);
        double score = outcome == ResultOutcome.VUNDET ? 1.0 : 0.0;

        // Del delta mellem alle spillere
        double delta = K * (score - expected) / compPlayers.size();

        for (Member m : compPlayers) {
            int oldRating = ensureEloInitialized(m);
            m.setEloRating((int) Math.round(oldRating + delta));
        }
    }



    //Helpers til Elo udregning

    /**
     * Tjekker om Elo er initialiseret for en spiller
     * @param m Et medlem som skal tjekkes
     * @return Elo ratingen for en spiller
     */
    private int ensureEloInitialized(Member m) {
        m.initializeEloIfNeeded();
        return m.getEloRating();
    }

    /**
     * Elo formlen. Bruges til at udregne hvor meget elo en spiller får eller mister
     * @param ratingA Rating for spiller A
     * @param ratingB Rating for spiller B
     * @return Den nye elo rating
     */
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

    /**
     * Hjælper metode til at filtrere konkurrence spillere
     * @param players Liste af de spillere som skal filtreres
     * @return Filtreret liste som kun indeholder konkurrencespillere
     */
    private List<Member> filterCompetitive(List<Member> players) {
        List<Member> result = new ArrayList<>();
        for(Member m : players){
            if(m.isCompetitive())
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
     * @param outcome True hvis holdet fra klubben vandt
     */
    private void updateSmashPointsAfterExternalTraining(List<Member> clubPlayers, ResultOutcome outcome) {

        for(Member m : clubPlayers){
            if(outcome == ResultOutcome.VUNDET){
                m.addSmashPoints(trainingWinPoints);
            } else {
                m.addSmashPoints(trainingLossPoints);
            }
        }
    }
}
