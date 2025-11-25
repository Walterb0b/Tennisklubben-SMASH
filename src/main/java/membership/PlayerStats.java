package main.java.membership;

public class PlayerStats {

    private int totalMatches;
    private int wins;
    private int losses;
    private int tournamentsPlayed;
    private int bestRanking;
    private int worstRanking;

    public PlayerStats(int totalMatches, int wins, int losses, int tournamentsPlayed, int bestRanking, int worstRanking){
        this.totalMatches = totalMatches;
        this.wins = wins;
        this.losses = losses;
        this.tournamentsPlayed = tournamentsPlayed;
        this.bestRanking = bestRanking;
        this.worstRanking = worstRanking;

    }

    public int getTotalMatches(){
        return totalMatches;
    }

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public int getTournamentsPlayed(){
        return tournamentsPlayed;
    }

    public int getBestRanking(){
        return bestRanking;
    }

    public int getWorstRanking(){
        return worstRanking;
    }

    @Override
    public String toString(){
        return "Kampe: " + totalMatches +
                "Sejre: " + wins +
                "Nederlag: " + losses +
                "Antal turneringer: " + tournamentsPlayed +
                "Bedste placering: " + bestRanking +
                "Dårligste placering: " + worstRanking;
    }
}
