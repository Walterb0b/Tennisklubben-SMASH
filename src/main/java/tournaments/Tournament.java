package main.java.tournaments;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Klassen bruges af TourManager og TourController
 *
 */
public class Tournament {

    private Integer tournamentID;
    private String name;
    private TournamentLevel level;
    private LocalDate startDate;
    private LocalDate endDate;

    private List<Integer> matchIDs = new ArrayList<>();

    /**
     *
     * @param tournamentID ID for turnering for at skelne
     * @param name, navn på turnering
     * @param level, Turneringens niveau
     * @param startDate, Startdato for turnering
     * @param endDate, Sidste dato for turnering
     */
    public Tournament(int tournamentID, String name, TournamentLevel level, LocalDate startDate, LocalDate endDate) {
        this.tournamentID = tournamentID;
        this.name = name;
        this.level = level;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**** @return Turnerings ID
     * Navn på turnering
     * Turneringsadresse
     * Start dato
     * Slut dato
     * Tilføjer entry ID for medlem/konkurrencespiller  */
    public Integer getTournamentID() {
        return tournamentID;
    }

    public String getName() {return name;}

    public TournamentLevel getLevel() {return level;}

    public LocalDate getStartDate() {return startDate;}

    public LocalDate getEndDate() {return endDate;}

    public List<Integer> getMatchIDs() {return matchIDs;}

    public void addMatchID(int matchID) {
        if(!matchIDs.contains(matchID)){
            matchIDs.add(matchID);
        }
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + tournamentID +
                ", name='" + name + '\'' +
                ", niveau='" + level + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}

