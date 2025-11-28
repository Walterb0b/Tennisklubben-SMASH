package main.java.logic;

import main.java.tournaments.Tournament;

import java.util.HashMap;

public class TournamentManager {

    private HashMap<Integer, Tournament> tournaments;

    public TournamentManager() {
        tournaments = new HashMap<>();
    }

    public void addTournament(Tournament tournament) {
        tournaments.put(tournament.getTournamentId(), tournament);
    }

    public Tournament getTournament(int id) {
        return tournaments.get(id);
    }

    public void removeTournament(int id) {
        tournaments.remove(id);
    }

    public void printAllTournaments() {
        for (Tournament tournament : tournaments.values()) {
            System.out.println(tournament);
        }
    }
}
