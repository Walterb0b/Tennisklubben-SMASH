package main.java.util;

import main.java.logic.*;
import main.java.membership.*;
import main.java.tournaments.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static main.java.tournaments.MatchType.TRÆNING;
import static main.java.tournaments.MatchType.TURNERING;

public class FileHandler {
    private MemberManager memberManager;
    private PaymentManager paymentManager;
    private ResultManager resultManager;
    private TournamentManager tournamentManager;
    private RatingService ratingService;
    private PlayerStats playerStats;
    private final String delimiter = ";";
    private String memberDatabaseFilePath = "memberDatabase.csv";
    private String paymentDatabaseFilePath = "paymentDatabase.csv";
    private String playerStatDatabaseFilePath = "playerStatsDatabase.csv";
    private String resultDatabaseFilePath = "resultDatabase.csv";


    public FileHandler (MemberManager memberManager, PaymentManager paymentManager, ResultManager resultManager, TournamentManager tournamentManager, RatingService ratingService, PlayerStats playerStats) {
        this.memberManager = memberManager;
        this.paymentManager = paymentManager;
        this.resultManager = resultManager;
        this.tournamentManager = tournamentManager;
        this.ratingService = ratingService;
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
        String paidDate;
        String seasonQuarter;
        String amount;
        String isPaid;


        String singleLine;

        ArrayList<String> paymentCSV = new ArrayList<>();


        name = "Medlemsnavn";
        memberID = "MedlemsID";
        paymentID = "BetalingsID";
        dueDate = "Opkrævnings dato";
        paidDate = "Betaling modtaget dato";
        seasonQuarter = "Sæsonskvartal";
        amount = "Beløb";
        isPaid = "Betalt";

        singleLine = name + delimiter + memberID + delimiter + paymentID + delimiter + dueDate + delimiter +
                paidDate + delimiter + seasonQuarter + delimiter + amount + delimiter + isPaid;

        paymentCSV.add(singleLine);


        for (MembershipPayment p : paymentManager.getAllPaymentsSortedByMemberIDDueDate()) {

            Member m = p.getMember();

            name = m.getName();
            memberID = String.valueOf(m.getMemberID());
            paymentID = String.valueOf(p.getPaymentID());
            dueDate = Formatter.localDateToString(p.getDueDate());
            paidDate = Formatter.localDateToString(p.getIsPaidDate());
            seasonQuarter = p.getSeasonQuarter();
            amount = String.valueOf(p.getAmount());
            isPaid = String.valueOf(p.getIsPaid());

            singleLine = name + delimiter + memberID + delimiter + paymentID + delimiter + dueDate + delimiter +
                    paidDate + delimiter + seasonQuarter + delimiter + amount + delimiter + isPaid;

            paymentCSV.add(singleLine);
        }
        writeFile(paymentCSV, paymentDatabaseFilePath);
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
        writeFile(playerStatsCSV, playerStatDatabaseFilePath);
    }

    public void saveResultsToCSV() {
        ArrayList<String> parts = new ArrayList<>();
        String singleLine;

        String matchID = "KampID";
        String player = "Spiller";
        String discipline = "Discipline";
        String type = "Type";
        String outcome = "Resultat";
        String opponents = "Modstander(e)";
        String score = "Score";
        String date = "Dato";


        singleLine = matchID + delimiter + player + delimiter + discipline + delimiter + type + delimiter + outcome +
                delimiter + opponents + delimiter + score + delimiter + date;
        parts.add(singleLine);

        for (PlayerResult r : resultManager.getAllResults()) {

            String playerLabel = r.getPlayer().getName() + " (ID: " + r.getPlayer().getMemberID() + ")";

            parts.add(
                    r.getMatchID() + delimiter +
                            playerLabel + delimiter +
                            r.getDiscipline() + delimiter +
                            r.getType() + delimiter +
                            r.getOutcome() + delimiter +
                            r.getOpponentInfo() + delimiter +
                            r.getScore() + delimiter +
                            Formatter.localDateToString(r.getDate())
            );
        }
        writeFile(parts, resultDatabaseFilePath);
    }

    public void saveTournamentsToCSV(){
        ArrayList<String> parts = new ArrayList<>();
        String singleLine;

        String tournamentID = "TurneringsID";
        String name = "Navn";
        String level = "Niveau";
        String startDate = "Startdato";
        String endDate = "Slutdato";
        String matchIDs = "KampID'er";

        singleLine =  tournamentID + delimiter + name + delimiter + level + delimiter + startDate + delimiter + endDate
                + delimiter + matchIDs;
        parts.add(singleLine);

        for(Tournament t : tournamentManager.getAllTournaments()) {
            matchIDs = t.getMatchIDs().isEmpty()
                    ? ""
                    : String.join(",", t.getMatchIDs().stream()
                    .map(String::valueOf)
                    .toList());

            singleLine = t.getTournamentID() + delimiter +
                    t.getName() + delimiter +
                    t.getLevel() + delimiter +
                    Formatter.localDateToString(t.getStartDate()) + delimiter +
                    Formatter.localDateToString(t.getEndDate()) + delimiter +
                    matchIDs;

            parts.add(singleLine);
        }
        writeFile(parts, "tournamentDatabase.csv");
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

    public void createResultsFromCSV() {
        ArrayList<String[]> fileContent = readFromFile(resultDatabaseFilePath);

        for (String[] parts : fileContent) {

            // Spring header over
            if (parts[0].equalsIgnoreCase("KampID")) continue;

            int matchID = Integer.parseInt(parts[0]);
            int memberID = extractMemberID(parts[1]);
            Disciplines discipline = Disciplines.valueOf(parts[2]);
            MatchType type = MatchType.valueOf(parts[3]);
            ResultOutcome outcome = ResultOutcome.valueOf(parts[4]);
            String opponents = parts[5];
            String score = parts[6];
            LocalDate date = Formatter.stringToLocalDate(parts[7]);

            Member m = memberManager.getMember(memberID);
            if (m == null) {
                System.out.println("Fejl ved indlæsning af resultater for medlem " + memberID);
                continue;
            }

            // 1️⃣ Opret PlayerResult
            PlayerResult pr = new PlayerResult(
                    m,
                    discipline,
                    type,
                    outcome,
                    opponents,
                    score,
                    date
            );

            resultManager.getAllResults().add(pr);

            if (type == MatchType.TURNERING) {
                ratingService.updateAfterExternalMatch(List.of(m), outcome, type);
            }
            else if (type == MatchType.TRÆNING) {
                ratingService.updateAfterExternalMatch(List.of(m), outcome, type);
            }
        }
    }


    public void createPaymentsFromCSV() {
        ArrayList<String[]> fileContent = readFromFile(paymentDatabaseFilePath);

        for(String[] parts : fileContent){
            String name = parts[0];
            int memberID = Integer.parseInt(parts[1]);
            int paymentID = Integer.parseInt(parts[2]);
            LocalDate dueDate = Formatter.stringToLocalDate(parts[3]);
            LocalDate paidDate = Formatter.stringToLocalDate(parts[4]);
            String seasonQuarter = parts[5];
            double amount = Double.parseDouble(parts[6]);
            boolean isPaid = Boolean.parseBoolean(parts[7]);

            Member m = memberManager.getMember(memberID);
            if(m == null) {
                System.out.println("Fejl ved indlæsning af indbetalinger fra filen for medlem " + memberID);
                continue;
            }

            MembershipPayment payment = new MembershipPayment(m, dueDate);
            payment.setPaymentID(paymentID);
            payment.setAmount(amount);
            payment.setIsPaidDate(paidDate);
            payment.setSeasonQuarter(seasonQuarter);
            payment.setIsPaid(isPaid);

            paymentManager.getAllPayments().put(paymentID, payment);
        }
    }

    public void createTournamentsFromCSV() {
        ArrayList<String[]> fileContent = readFromFile("tournamentDatabase.csv");

        for(String[] parts : fileContent) {
            if(parts.length < 5) continue;

            int matchID = Integer.parseInt(parts[0]);
            String name = parts[1];
            TournamentLevel level = TournamentLevel.valueOf(parts[2]);
            LocalDate startDate = Formatter.stringToLocalDate(parts[3]);
            LocalDate endDate = Formatter.stringToLocalDate(parts[4]);

            Tournament t = new Tournament(matchID, name, level, startDate, endDate);

            if(parts.length > 5 && parts[5] != null && !parts[5].isBlank()){
                String[] matchIDs = parts[5].split(",");
                for(String m : matchIDs) {
                    t.addMatchID(Integer.parseInt(m));
                }
            }
            tournamentManager.getAllTournamentsForFileHandling().put(matchID, t);
        }
    }

    private int extractMemberID(String s) {
        try {
            String[] parts = s.split("ID: ");
            return Integer.parseInt(parts[1].replace(")", "").trim());
        } catch (Exception e) {
            return -1;
        }
    }

}