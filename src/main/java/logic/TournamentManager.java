package main.java.logic;

import main.java.tournaments.Tournament;

import java.util.HashMap;

/**
 * Bruges til at håndtere turneringer
 */
public class TournamentManager {

    public HashMap<Integer, Tournament> tournaments;

    /**
     * Opretter ny TourManager
     */
    public TournamentManager() {this.tournaments = new HashMap<Integer, Tournament>();}

    /**
     * @param tournament, tilføjer turneringer
     */
    public void addTournament(Tournament tournament) {tournaments.put(tournament.getTournamentId(), tournament);}

    /**
     * @param id, Henter turnering ID
     * @return, Tournament-Objekt
     */
    public Tournament getTournament(int id) {return tournaments.get(id);}

    /** Udskriver turneringer til konsol
     */
    public void printAllTournaments() {
        for (Tournament tournament : tournaments.values()) {System.out.println(tournament);}
    }


}
