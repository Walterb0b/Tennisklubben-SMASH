package main.java.membership;

import java.util.HashSet;

public class PlayPreference {
    private boolean isCompetetiveMember;
    private HashSet<Disciplines> disciplines;


    public PlayPreference(boolean isCompetetiveMember, HashSet<Disciplines> disciplines) {
        this.isCompetetiveMember = isCompetetiveMember;
        this.disciplines = disciplines;
    }

    public boolean isCompetetiveMember() {
        return isCompetetiveMember;
    }

    public HashSet<Disciplines> getGamePreference() {
        return disciplines;
    }

    public void setCompetetiveMember(boolean competetiveMember) {
        this.isCompetetiveMember = competetiveMember;
    }

    public void setGamePreference(HashSet<Disciplines> disciplines) {
        this.disciplines = disciplines;
    }

}
