package main.java.tournaments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Klassen bruges af TourManager og TourController
 *
 */
public class Tournament {

    private int tournamentID;
    private String name;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<TournamentEntry> entries;

    /**
     *
     * @param tournamentID ID for turnering for at skelne
     * @param name, navn på turnering
     * @param location, lokation for turnering
     * @param startDate, Startdato for turnering
     * @param endDate, Sidste dato for turnering
     */
    public Tournament(int tournamentID, String name, String location,
                      LocalDate startDate, LocalDate endDate) {
        this.tournamentID = tournamentID;
        this.name = name;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.entries = new ArrayList<>();
    }

    /**** @return Turnerings ID
     * Navn på turnering
     * Turneringsadresse
     * Start dato
     * Slut dato
     * Tilføjer entry ID for medlem/konkurrencespiller*/
    public int getTournamentId() {return tournamentID;}

    public String getName() {return name;}

    public String getLocation() {return location;}

    public LocalDate getStartDate() {return startDate;}

    public LocalDate getEndDate() {return endDate;}

    public List<TournamentEntry> getEntries() {return entries;}

    public void addEntry(TournamentEntry entry) {entries.add(entry);}

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + tournamentID +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", entries=" + entries.size() +
                '}';
    }
}

