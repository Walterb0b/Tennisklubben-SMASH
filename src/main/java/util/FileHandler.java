package main.java.util;

import main.java.logic.*;
import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.membership.MembershipPayment;
import main.java.membership.PlayPreference;
import main.java.tournaments.Tournament;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private MemberManager memberManager;
    private PaymentManager paymentManager;
    private ResultManager resultManager;
    private TournamentManager tournamentManager;
    private PlayerStats playerStats;
    private final String delimiter = ";";

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
        String smashPoint;


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
        smashPoint = "Antal SmashPoint";

        singleLine = name + delimiter + memberID + delimiter + phoneNumber + delimiter + birthday + delimiter + signUpDate +
                delimiter + cancellationDate + delimiter + activePassive + delimiter + competitiveCasual + delimiter +
                playsSingle + delimiter + playsDouble + delimiter + playsMixDouble + delimiter + smashPoint;

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
                competitiveCasual = "";
                playsSingle = "";
                playsDouble = "";
                playsMixDouble = "";
            } else {
                competitiveCasual =  Formatter.booleanToString(m.getPlayPreference().isCompetetiveMember(), "Konkurrencespiller", "Motionist");
                playsSingle = Formatter.booleanToString(m.getPlayPreference().getGamePreference().contains(Disciplines.SINGLE), "Ja", "");
                playsDouble = Formatter.booleanToString(m.getPlayPreference().getGamePreference().contains(Disciplines.DOUBLE), "Ja", "");
                playsMixDouble = Formatter.booleanToString(m.getPlayPreference().getGamePreference().contains(Disciplines.MIXDOUBLE), "Ja", "");
            }

            smashPoint = String.valueOf(m.getSmashPoints());

            singleLine = name + delimiter + memberID + delimiter + phoneNumber + delimiter + birthday + delimiter + signUpDate +
                    delimiter + cancellationDate + delimiter + activePassive + delimiter + competitiveCasual + delimiter +
                    playsSingle + delimiter + playsDouble + delimiter + playsMixDouble + delimiter + smashPoint;

            memberCSV.add(singleLine);

        }

        writeFile(memberCSV, "memberDatabase.csv");

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

        final String delimiter = ";";
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

/*
    public static ArrayList<String> readFromFile(String filename){
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

 */
}
