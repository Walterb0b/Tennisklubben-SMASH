package main.java.util;

import main.java.logic.MemberManager;
import main.java.membership.Disciplines;
import main.java.membership.Member;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private MemberManager memberManager;
    private static final String delimiter = ";";

    public FileHandler (MemberManager memberManager) {
        this.memberManager = memberManager;
    }

    public void writeFile(ArrayList<String> fileInput, String fileName){

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){

            //String[] lines = fileInput.split("\n");
            //for(int i = 1; i < lines.length; i++){

            for (String line : fileInput) {

                bw.write(line);
                bw.newLine();

            }

            bw.close();

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
