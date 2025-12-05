package main.java.tournaments;

import main.java.membership.Disciplines;
import main.java.membership.Member;

import java.time.LocalDate;

public class PlayerResult {
    private static int nextID = 1;
    private final int matchID;
    private Member player;
    private Disciplines discipline;
    private MatchType type;
    private ResultOutcome outcome;
    private MatchLocation location;
    private String opponentInfo;
    private String score;
    private LocalDate date;

    public PlayerResult(
                        Member player,
                        Disciplines discipline,
                        MatchType type,
                        ResultOutcome outcome,
                        MatchLocation location,
                        String opponentInfo,
                        String score,
                        LocalDate date){
        this.matchID = nextID++;
        this.player = player;
        this.discipline = discipline;
        this.type = type;
        this.outcome = outcome;
        this.location = location;
        this.opponentInfo = opponentInfo;
        this.score = score;
        this.date = date;
    }
    //Getters
    public int getMatchID(){return matchID;}
    public Member getPlayer(){return player;}
    public Disciplines getDiscipline(){return discipline;}
    public MatchType getType(){return type;}
    public ResultOutcome getOutcome(){return outcome;}
    public MatchLocation getLocation(){return location;}
    public String getOpponentInfo(){return opponentInfo;}
    public String getScore(){return score;}
    public LocalDate getDate(){return date;}

    //Setters
    public void setScore(String score) {
        this.score = score;
    }

    public void setOpponentInfo(String newOpponentInfo) {
        this.opponentInfo = newOpponentInfo;
    }

    public void setOutcome(ResultOutcome outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString(){
        return "Kamp ID: " + matchID + "\n" +
                "Kamp type: " + type + "\n" +
                "Spiller: " + player.getName() + " (ID: " + player.getMemberID() + ")" + "\n" +
                "Disciplin: " + discipline + "\n" +
                "Modstander: " + opponentInfo + "\n" +
                "Score: " + score + "\n" +
                "Resultat: " + outcome + "\n";
    }
}
