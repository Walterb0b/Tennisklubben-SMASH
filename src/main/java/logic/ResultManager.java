package main.java.logic;

import main.java.tournaments.PlayerResult;

import java.util.ArrayList;
import java.util.List;

public class ResultManager {
    private List<PlayerResult> results = new ArrayList<>();
    private int nextMatchId = 1;

    public List<PlayerResult> getAllResults(){
        return results;
    }


}
