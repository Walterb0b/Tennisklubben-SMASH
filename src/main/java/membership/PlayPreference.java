package main.java.membership;

import java.util.HashSet;

public class PlayPreference {
    private boolean isCompetetiveMember;
    private HashSet<GamePreference> gamePreference;
    private int smashPoint;

    public PlayPreference(boolean isCompetetiveMember, HashSet<GamePreference> gamePreference,int smashPoint) {
        this.isCompetetiveMember = isCompetetiveMember;
        this.gamePreference = gamePreference;
        this.smashPoint = smashPoint;
    }

    public boolean isCompetetiveMember() {
        return isCompetetiveMember;
    }

    public HashSet<GamePreference> getGamePreference() {
        return gamePreference;
    }

    public int getSmashPoint() {
        return smashPoint;
    }

    public void setCompetetiveMember(boolean competetiveMember) {
        this.isCompetetiveMember = competetiveMember;
    }

    public void setGamePreference(HashSet<GamePreference> gamePreference) {
        this.gamePreference = gamePreference;
    }

    public void setSmashPoint(int smashPoint) {
        this.smashPoint = smashPoint;
    }

    @Override
    public String toString(){
        return "Konkurrencespiller: " + isCompetetiveMember + "Disciplin(er): " + gamePreference + "SMASH-Point: " + smashPoint;
    }
}
