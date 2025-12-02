package main.java.tournaments;

import main.java.membership.Disciplines;

import java.time.LocalDate;

public class TournamentEntry {

    private int entryID;
    private int memberID;
    private int tournamentID;
    private Disciplines discipline;
    private String tournamentName;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    public TournamentEntry(int entryID, int memberID, Tournament tournament, Disciplines discipline) {
        this.entryID = entryID;
        this.memberID = memberID;
        this.tournamentID = tournament.getTournamentId();
        this.discipline = discipline;
        this.tournamentName = tournament.getName();
        this.location = tournament.getLocation();
        this.startDate = tournament.getStartDate();
        this.endDate = tournament.getEndDate();
    }

    public int getEntryID() {return entryID;}

    public int getMemberID() {return memberID;}

    public int getTournamentID() {return tournamentID;}

    public Disciplines getDiscipline() {return discipline;}

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
