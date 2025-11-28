package main.java.tournaments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private int tournamentID;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<TournamentEntry> entries;


    public Tournament(int tournamentID, int id, String name, String location, LocalDate startDate, LocalDate endDate, int nextTournamentID) {
        this.tournamentID = tournamentID;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;

        this.entries = new ArrayList<>();
    }

    public int getTournamentID(){return tournamentID;}

    public String getName(){return name;}

    public String getLocation(){return location;}

    public LocalDate getStartDate(){return startDate;}

    public LocalDate getEndDate(){return endDate;}

    public List <TournamentEntry> getEntries(){return entries;}

    public void addEntry(TournamentEntry entry){
        entries.add(entry);
    }

    public int getTournamentId() {
        return tournamentID;
    }
}
