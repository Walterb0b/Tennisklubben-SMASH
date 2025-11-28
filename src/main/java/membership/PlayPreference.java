package main.java.membership;

import java.util.HashSet;

public class PlayPreference {
    private boolean isCompetetiveMember;
    private HashSet<Disciplines> disciplines;
    private int smashPoint;

    public PlayPreference(boolean isCompetetiveMember, HashSet<Disciplines> disciplines, int smashPoint) {
        this.isCompetetiveMember = isCompetetiveMember;
        this.disciplines = disciplines;
        this.smashPoint = smashPoint;
    }

    public boolean isCompetetiveMember() {
        return isCompetetiveMember;
    }

    public HashSet<Disciplines> getGamePreference() {
        return disciplines;
    }

    public int getSmashPoint() {
        return smashPoint;
    }

    public void setCompetetiveMember(boolean competetiveMember) {
        this.isCompetetiveMember = competetiveMember;
    }

    public void setGamePreference(HashSet<Disciplines> disciplines) {
        this.disciplines = disciplines;
    }

    public void setSmashPoint(int smashPoint) {
        this.smashPoint = smashPoint;
    }

    @Override
    public String toString(){
        return "Konkurrencespiller: " + isCompetetiveMember + "Disciplin(er): " + disciplines + "SMASH-Point: " + smashPoint;
    }
}
