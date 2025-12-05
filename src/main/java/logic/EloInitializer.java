package main.java.logic;

import main.java.membership.Member;
import main.java.tournaments.MatchType;
import main.java.tournaments.ResultOutcome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//Denne klasse er lavet med AI da vi havde rigtig mange problemer med at få det til at virke
public class EloInitializer {
    private final MemberManager memberManager;
    private final RatingService ratingService;

    public EloInitializer(MemberManager memberManager, RatingService ratingService){
        this.memberManager = memberManager;
        this.ratingService = ratingService;
    }

    /**
     * Initialiserer Elo for spillere ved opstart
     * @param csvPath Filstien for CSV fil
     */
    public void initializeEloFromCSV(String csvPath){
        //Nulstil Elo på alle medlemmer
        resetEloForAllMembers();

        //Læs CSV og grupper linjer pr. MatchID
        Map<Integer, List<String[]>> matchesByID = loadCsvGroupedByMatchID(csvPath);

        //Gå alle kampe igennen i reækkefølge af MatchID
        List<Integer> matchIDs = new ArrayList<>(matchesByID.keySet());
        Collections.sort(matchIDs);

        for(int matchID : matchIDs){
            List<String[]> rows = matchesByID.get(matchID);
            if(rows == null || rows.isEmpty()) continue;

            String typeField = rows.get(0)[3].trim();
            String locationField = rows.get(0)[5].trim();

            if(!typeField.equalsIgnoreCase("TURNERING")){
                continue;
            }

            List<Member> winners = new ArrayList<>();
            List<Member> losers = new ArrayList<>();

            for(String[] row : rows){
                String playerField = row[1];
                String resultField = row[4].trim();

                int memberID = extractPlayerID(playerField);
                if(memberID == -1) continue;

                Member m = memberManager.getMember(memberID);

                if(m == null) continue;

                if(!m.isCompetitive()) continue;

                if(resultField.equalsIgnoreCase("VUNDET")){
                    winners.add(m);
                } else {
                    losers.add(m);
                }
            }

            if(winners.isEmpty() && losers.isEmpty()){
                continue;
            }

            MatchType type = MatchType.TURNERING;

            if(locationField.equalsIgnoreCase("INTERN")){
                ratingService.updateAfterInternalMatch(winners, losers, 1, type);
            } else {
                List<Member> clubPlayers = new ArrayList<>();
                clubPlayers.addAll(winners);
                clubPlayers.addAll(losers);

                ResultOutcome outcome = winners.isEmpty() ? ResultOutcome.TABT : ResultOutcome.VUNDET;

                ratingService.updateAfterExternalMatch(clubPlayers, outcome, type);
            }
        }

    }

    /**
     * Hjælper til at trække et spiller ID (MemberID) ud fra en string
     * @param playerField String felt med en spiller (Member)
     * @return ID
     */
    private int extractPlayerID(String playerField) {
        int start = playerField.indexOf("ID:");
        if(start == -1) return -1;
        start += 3;
        int end = playerField.indexOf(")", start);
        if(end == -1) end = playerField.length();
        String IDText = playerField.substring(start, end).trim();
        try {
            return Integer.parseInt(IDText);
        } catch (NumberFormatException nfe){
            return -1;
        }
    }

    /**
     * Læser csv og grupperer kampene på matchID
     * @param csvPath Filstien for csv fil
     * @return Map af MatchID og fælter for kampen
     */
    private Map<Integer, List<String[]>> loadCsvGroupedByMatchID(String csvPath) {
        Map<Integer, List<String[]>> matchesByID = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(csvPath))){
            String line = br.readLine();
            while((line = br.readLine()) != null){
                if(line.isBlank()) continue;
                String[] fields = line.split(";");
                if(fields.length < 9) continue;

                int matchID = Integer.parseInt(fields[0].trim());

                List<String[]> list = matchesByID.get(matchID);
                if(list == null){
                    list = new ArrayList<>();
                    matchesByID.put(matchID, list);
                }
                list.add(fields);
            }
        } catch (IOException e){
            System.out.println("Fejl ved læsning af Elo" + e.getMessage());
        }

        return matchesByID;
    }

    private void resetEloForAllMembers() {
        for(Member m : memberManager.getAllMembers().values()){
            if(m.isCompetitive()){
                m.setEloRating(1500);
            } else {
                m.setEloRating(null);
            }
        }
    }
}
