package main.java.logic;

import main.java.membership.CompetitivePlayer;

import java.util.HashMap;

public class CompetitivePlayerManager {

    private HashMap<Integer, CompetitivePlayer> players;

    public CompetitivePlayerManager() {
        players = new HashMap<>();
    }

    public void addPlayer(CompetitivePlayer player) {
        players.put(player.getPlayerId(), player);
    }

    public CompetitivePlayer getPlayer(int id) {
        return players.get(id);
    }

    public void removePlayer(int id) {
        players.remove(id);
    }

    public void printAllPlayers() {
        for (CompetitivePlayer player : players.values()) {
            System.out.println(player);
        }
    }
}
