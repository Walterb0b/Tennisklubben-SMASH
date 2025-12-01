package main.java.tournaments;

import main.java.membership.Disciplines;
import main.java.membership.Member;

import java.time.LocalDate;

public class PlayerResult {
    private int matchID;
    private Member player;
    private Disciplines discipline;
    private MatchType type;
    private ResultOutcome outcome;
    private String opponentInfo;
    private String score;
    private LocalDate date;

    public PlayerResult(int matchID,
                        Member player,
                        Disciplines discipline,
                        MatchType type,
                        ResultOutcome outcome,
                        String opponentInfo,
                        String score,
                        LocalDate date){
        this.matchID = matchID;
        this.player = player;
        this.discipline = discipline;
        this.type = type;
        this.outcome = outcome;
        this.opponentInfo = opponentInfo;
        this.score = score;
        this.date = date;
    }

    public int getMatchID(){return matchID;}
    public Member getPlayer(){return player;}
    public Disciplines getDiscipline(){return discipline;}
    public MatchType getType(){return type;}
    public ResultOutcome getOutcome(){return outcome;}
    public String getOpponentInfo(){return opponentInfo;}
    public String getScore(){return score;}
    public LocalDate getDate(){return date;}

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
