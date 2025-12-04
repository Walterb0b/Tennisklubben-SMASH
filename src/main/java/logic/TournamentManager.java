package main.java.logic;

import main.java.tournaments.MatchType;
import main.java.tournaments.PlayerResult;
import main.java.tournaments.Tournament;
import main.java.tournaments.TournamentLevel;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Bruges til at håndtere turneringer
 */
public class TournamentManager {

    public HashMap<Integer, Tournament> tournaments = new HashMap<>();
    private int nextTournamentID = 1;

    /**
     * Opretter en turnering
     * @param name Turneringens navn
     * @param level Turneringens niveau
     * @param start Startdato for turneringen
     * @param end Slutdato for turneringen
     */
    public Tournament createTournament(String name, TournamentLevel level, LocalDate start, LocalDate end) {
        Tournament t = new Tournament(nextTournamentID++, name, level, start, end);
        tournaments.put(t.getTournamentID(), t);
        return t;
    }

    /**
     * @param id Henter turnering ID
     * @return Tournament-Objekt
     */
    public Tournament getTournament(int id) {
        return tournaments.get(id);
    }

    public Collection<Tournament> getAllTournaments(){
        return tournaments.values();
    }

    public boolean addMatchToTournament(int tournamentID, int matchID, ResultManager resultManager){
        Tournament t = tournaments.get(tournamentID);
        if(t == null) return false;

        List<PlayerResult> res = resultManager.getResultsFromMatch(matchID);
        if(res.isEmpty()) return false;
        if(res.get(0).getType() != MatchType.TURNERING) return false;

        t.addMatchID(matchID);
        return true;
    }

    /**
     * Udskriver turneringer til konsol
     */
    public void printAllTournaments() {
        for (Tournament tournament : tournaments.values()) {
            System.out.println(tournament);
        }
    }


}
