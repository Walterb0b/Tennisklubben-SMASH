package main.java.tournaments;

import main.java.membership.Disciplines;

import java.time.LocalDate;

public class TournamentEntry {

    private int entryID;
    private int memberID;
    private int tournamentID;
    private Disciplines discipline;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nextTournamentID;

    public TournamentEntry(int entryID, int memberID, int tournamentID, Disciplines discipline) {
        this.entryID = entryID;
        this.memberID = memberID;
        this.tournamentID = tournamentID;
        this.discipline = discipline;
    }

    public int getEntryID() {
        return entryID;
    }

    public int getMemberID() {
        return memberID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public Disciplines getDiscipline() {
        return discipline;
    }

    public void setEntryID(int entryID) {
        if (entryID <= 0) {
            throw new IllegalArgumentException("ID skal være større end 0");
        }
        this.entryID = entryID;
    }

    public void setDiscipline(Disciplines discipline) {
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "TournamentEntry{" +
                "entryID=" + entryID +
                ", memberID=" + memberID +
                ", tournamentID=" + tournamentID +
                ", discipline=" + discipline +
                '}';
    }
}

