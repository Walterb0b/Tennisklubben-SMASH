package main.java.logic;

import main.java.membership.Member;
import main.java.membership.MembershipPayment;
import main.java.util.Formatter;
import main.java.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class MemberManager {
    private HashMap<Integer, Member> members;

    public MemberManager() {
        members = new HashMap<>();
    }

    public void addMember(Member member) {
        members.put(member.getMemberID(), member);

    }

    public HashMap<Integer, Member> getAllMembers() {
        return members;
    }

    public Member getMember(int ID) {
        return members.get(ID);
    }

    public void removeMember(int ID) {
        members.remove(ID);
    }

    public void printAllMembers() {
        for (Member i : members.values()) {
            System.out.println(i);
        }
    }

    public ArrayList<Integer> searchForMemberIDs(String query) {
        ArrayList<Integer> memberList = new ArrayList<>();


        if (Validator.containsInteger(query)) {
            for (Member m : members.values()) {
                if (m.getMemberID() == Validator.lossyConvertStringToInt(query)) {
                    memberList.add(m.getMemberID());
                }
            }
        } else {

            for (Member m : members.values()) {
                if (m.getName().toUpperCase().contains(query.toUpperCase())) {
                    memberList.add(m.getMemberID());
                }
            }
        }

        return memberList;
    }
    public int membersSize(){
        return  members.size();
    }

    Comparator<Member> byMemberID = Comparator.comparing(Member::getMemberID);
    Comparator<Member> byMemberName = Comparator.comparing(Member::getName);
    Comparator<Member> byMemberIDMemberName = byMemberID.thenComparing(byMemberName);

    public ArrayList<Member> getAllMembersSortedByMemberIDName() {
        ArrayList<Member> membersSorted = new ArrayList<Member>(members.values());
        membersSorted.sort(byMemberIDMemberName);
        return membersSorted;
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

        final String delimiter = ";";
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

        for (Member m : members.values()) {
            name = m.getName();
            memberID = String.valueOf(m.getMemberID());
            phoneNumber = m.getPhoneNumber();
            birthday = Formatter.localDateToString(m.getBirthday());

        }











    }
}


