package main.java.util;

import main.java.logic.*;
import main.java.membership.*;
import main.java.tournaments.MatchType;
import main.java.tournaments.PlayerResult;
import main.java.tournaments.ResultOutcome;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static main.java.tournaments.MatchType.TRÆNING;
import static main.java.tournaments.MatchType.TURNERING;

public class FileHandler {
    private MemberManager memberManager;
    private PaymentManager paymentManager;
    private ResultManager resultManager;
    private TournamentManager tournamentManager;
    private PlayerStats playerStats;
    private final String delimiter = ";";
    private String memberDatabaseFilePath = "memberDatabase.csv";
    private String paymentDatabaseFilePath = "paymentDatabase.csv";
    private String playerStatsDatabaseFilePath = "paymentDatabase.csv";


    public FileHandler (MemberManager memberManager, PaymentManager paymentManager, ResultManager resultManager, TournamentManager tournamentManager, PlayerStats playerStats) {
        this.memberManager = memberManager;
        this.paymentManager = paymentManager;
        this.resultManager = resultManager;
        this.tournamentManager = tournamentManager;
        this.playerStats = playerStats;
    }

    public void writeFile(ArrayList<String> fileInput, String fileName){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){

            //String[] lines = fileInput.split("\n");
            //for(int i = 1; i < lines.length; i++){

            for (String line : fileInput) {

                bw.write(line);
                bw.newLine();

            }

        } catch (IOException e) {
            System.out.println("Error writing file.");
        }

    }

    public void saveMembersToCSV() {
        String name;
        String memberID;
        String phoneNumber;
        String birthday;
        String signUpDate;
        String cancellationDate;
        String activePassive;
        String competitiveCasual;
        String playsSingle;
        String playsDouble;
        String playsMixDouble;


        String singleLine;

        ArrayList<String> memberCSV = new ArrayList<>();

        name = "Medlemsnavn";
        memberID = "MedlemsID";
        phoneNumber = "Telefonnummer";
        birthday = "Fødselsdag";
        signUpDate = "Medlemsskab Startdato";
        cancellationDate = "Medlemsskab Slutdato";
        activePassive = "Aktivt/Passivt Medlemsskab";
        competitiveCasual = "Konkurrencespiller/Motionist";
        playsSingle = "Disciplin: Spiller Single";
        playsDouble = "Disciplin: Spiller Double";
        playsMixDouble = "Disciplin: Spiller MixDouble";

        singleLine = name + delimiter + memberID + delimiter + phoneNumber + delimiter + birthday + delimiter + signUpDate +
                delimiter + cancellationDate + delimiter + activePassive + delimiter + competitiveCasual + delimiter +
                playsSingle + delimiter + playsDouble + delimiter + playsMixDouble;

        memberCSV.add(singleLine);

        for (Member m : memberManager.getAllMembersSortedByMemberIDName()) {
            name = m.getName();
            memberID = String.valueOf(m.getMemberID());
            phoneNumber = m.getPhoneNumber();
            birthday = Formatter.localDateToString(m.getBirthday());
            signUpDate = Formatter.localDateToString(m.getSignUpDate());
            cancellationDate = Formatter.localDateToString(m.getCancellationDate());
            activePassive = Formatter.booleanToString(m.getMembership().isActive(), "Aktivt", "Passivt");
            if (m.getPlayPreference() == null) {
                competitiveCasual = "Motionist";
                playsSingle = "Nej";
                playsDouble = "Nej";
                playsMixDouble = "Nej";
            } else {
                competitiveCasual =  Formatter.booleanToString(m.getPlayPreference().isCompetetiveMember(), "Konkurrencespiller", "Motionist");
                playsSingle = Formatter.booleanToString(m.getPlayPreference().getGamePreference().contains(Disciplines.SINGLE), "Ja", "Nej");
                playsDouble = Formatter.booleanToString(m.getPlayPreference().getGamePreference().contains(Disciplines.DOUBLE), "Ja", "Nej");
                playsMixDouble = Formatter.booleanToString(m.getPlayPreference().getGamePreference().contains(Disciplines.MIXDOUBLE), "Ja", "Nej");
            }

            singleLine = name + delimiter + memberID + delimiter + phoneNumber + delimiter + birthday + delimiter + signUpDate +
                    delimiter + cancellationDate + delimiter + activePassive + delimiter + competitiveCasual + delimiter +
                    playsSingle + delimiter + playsDouble + delimiter + playsMixDouble;

            memberCSV.add(singleLine);

        }

        writeFile(memberCSV, memberDatabaseFilePath);

    }

    public void savePaymentsToCSV() {
        String name;
        String memberID;
        String paymentID;
        String dueDate;
        String seasonQuarter;
        String amount;
        String isPaid;


        String singleLine;

        ArrayList<String> paymentCSV = new ArrayList<>();


        name = "Medlemsnavn";
        memberID = "MedlemsID";
        paymentID = "BetalingsID";
        dueDate = "Betalingsdato";
        seasonQuarter = "Sæsonskvartal";
        amount = "Beløb";
        isPaid = "Betalt";

        singleLine = name + delimiter + memberID + delimiter + paymentID + delimiter + dueDate + delimiter + seasonQuarter +
                delimiter + amount + delimiter + isPaid;


        for (MembershipPayment p : paymentManager.getAllPaymentsSortedByDueDateMemberID()) {

            Member m = p.getMember();

            name = m.getName();
            memberID = String.valueOf(m.getMemberID());
            paymentID = String.valueOf(p.getPaymentID());
            dueDate = Formatter.localDateToString(p.getDueDate());
            seasonQuarter = p.getSeasonQuarter();
            amount = String.valueOf(p.getAmount());
            isPaid = String.valueOf(p.getIsPaid());

            singleLine = name + delimiter + memberID + delimiter + paymentID + delimiter + dueDate + delimiter + seasonQuarter +
                    delimiter + amount + delimiter + isPaid + delimiter ;

            paymentCSV.add(singleLine);
        }
        writeFile(paymentCSV, "paymentDatabase.csv");
    }

    public void savePlayerStatsToCSV() {

        String singleLine;

        ArrayList<String> playerStatsCSV = new ArrayList<>();

        String name = "Medlemsnavn";
        String memberID = "MedlemsID";
        String winsSingle = "Single vundet";
        String lossesSingle = "Single tabt";
        String winsDouble = "Double vundet";
        String lossesDouble = "Double tabt";
        String winsMix = "MixDouble vundet";
        String lossesMix = "MixDouble tabt";
        String eloRating = "eloRating";
        String smashPoints = "SmashPoints";


        singleLine = name + delimiter + memberID + delimiter + winsSingle + delimiter + lossesSingle + delimiter + winsDouble +
                delimiter + lossesDouble + delimiter + winsMix + delimiter + lossesMix + delimiter + eloRating + delimiter + smashPoints;
        playerStatsCSV.add(singleLine);

        for (Member m : memberManager.getAllMembersSortedByMemberIDName()) {

            name = m.getName();
            memberID = String.valueOf(m.getMemberID());
            winsSingle = String.valueOf(playerStats.getWins(m, Disciplines.SINGLE));
            lossesSingle = String.valueOf(playerStats.getLosses(m, Disciplines.SINGLE));
            winsDouble = String.valueOf(playerStats.getWins(m, Disciplines.DOUBLE));
            lossesDouble = String.valueOf(playerStats.getLosses(m, Disciplines.DOUBLE));
            winsMix = String.valueOf(playerStats.getWins(m, Disciplines.MIXDOUBLE));
            lossesMix = String.valueOf(playerStats.getLosses(m, Disciplines.MIXDOUBLE));
            eloRating = String.valueOf(m.getEloRating());
            smashPoints = String.valueOf(m.getSmashPoints());


            singleLine = name + delimiter + memberID + delimiter + winsSingle + delimiter + lossesSingle + delimiter + winsDouble +
                    delimiter + lossesDouble + delimiter + winsMix + delimiter + lossesMix + delimiter + eloRating + delimiter + smashPoints;

            playerStatsCSV.add(singleLine);
        }
        writeFile(playerStatsCSV, "playerStatsDatabase.csv");
    }

    public void saveResultsToCSV() {
        ArrayList<String> parts = new ArrayList<>();
        String singleLine;

        String matchID = "KampID";
        String memberID = "MedlemsID";
        String discipline = "Discipline";
        String type = "Type";
        String outcome = "Resultat";
        String opponents = "Modstander(ere)";
        String score = "Score";
        String date = "Dato";


        singleLine = matchID + delimiter + memberID + delimiter + discipline + delimiter + type + delimiter + outcome +
                delimiter + opponents + delimiter + score + delimiter + date;
        parts.add(singleLine);

        for (PlayerResult r : resultManager.getAllResults()) {
            parts.add(
                    r.getMatchID() + delimiter
                            + r.getPlayer().getMemberID() + delimiter
                            + r.getDiscipline() + delimiter
                            + r.getType() + delimiter
                            + r.getOutcome() + delimiter
                            + r.getOpponentInfo() + delimiter
                            + r.getScore() + delimiter
                            + Formatter.localDateToString(r.getDate()));

        }
        writeFile(parts, "resultDatabase.csv");
    }

    public ArrayList<String[]> readFromFile(String filename){
        ArrayList<String[]> fileContent = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                fileContent.add(parts);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;
    }

    public void createMembersFromCSV() {
        ArrayList<String[]> fileContent = readFromFile(memberDatabaseFilePath);
        for (String[] parts : fileContent) {

            String memberName = parts[0];
            int memberID = Integer.parseInt(parts[1]);
            String phoneNumber = parts[2];
            LocalDate birthday = Formatter.stringToLocalDate(parts[3]);
            LocalDate signUpDate = Formatter.stringToLocalDate(parts[4]);

            LocalDate cancellationDate = null;
            if (!parts[5].isBlank()) {
                cancellationDate = Formatter.stringToLocalDate(parts[5]);
            }

            boolean isActive = parts[6].equalsIgnoreCase("aktivt");
            boolean isCompetitive = parts[7].equalsIgnoreCase("konkurrencespiller");
            boolean playsSingle = parts[8].equalsIgnoreCase("ja");
            boolean playsDouble = parts[9].equalsIgnoreCase("ja");
            boolean playsMixDouble = parts[10].equalsIgnoreCase("ja");

            HashSet<Disciplines> disciplinesSet = new HashSet<>();
            if (playsSingle) disciplinesSet.add(Disciplines.SINGLE);
            if (playsDouble) disciplinesSet.add(Disciplines.DOUBLE);
            if (playsMixDouble) disciplinesSet.add(Disciplines.MIXDOUBLE);

            Member m = new Member(memberName, phoneNumber, birthday, signUpDate,
                    isActive ? new ActiveMembership() : new PassiveMembership(),
                    new PlayPreference(isCompetitive, disciplinesSet));

            memberManager.addMember(m);

            if (cancellationDate != null) {
                m.setCancellationDate(cancellationDate);
            }
        }
    }

    public void readResultsCSV() {
        ArrayList<String[]> fileContent = readFromFile("resultDatabase.csv");

        for (String[] parts : fileContent) {
            int matchID = Integer.parseInt(parts[0]);
            int memberID = Integer.parseInt(parts[1]);
            Disciplines discipline = Disciplines.valueOf(parts[2]);
            MatchType type = MatchType.valueOf(parts[3]);
            ResultOutcome outcome = ResultOutcome.valueOf(parts[4]);
            String opponents = parts[5];
            String score = parts[6];
            LocalDate date = Formatter.stringToLocalDate(parts[7]);

            Member m = memberManager.getMember(memberID);
            if (m == null) {
                System.out.println("Fejl ved indlæsning af resultater fra filen for medlem " + memberID);
                continue;
            }

            if (type == TURNERING) {
                resultManager.addExternalMatchResult(
                        List.of(m), discipline, type, opponents, score, date
                );
            } else if (type == TRÆNING) {
                // You will need a way to get both teams. If you saved opponent IDs somewhere, reconstruct the teams:
                List<Member> teamA = List.of(m); // placeholder
                List<Member> teamB = List.of(); // parse opponents into Members
                int winningTeam = outcome == ResultOutcome.VUNDET ? 1 : 2; // simple logic
                resultManager.addInternalMatchResult(teamA, teamB, discipline, type, winningTeam, score, date);
            }

            //nextMatchID = Math.max(nextMatchID, matchID + 1);
        }
    }


}