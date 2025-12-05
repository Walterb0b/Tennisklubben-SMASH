package main.java.logic;

import main.java.membership.Disciplines;
import main.java.membership.Member;
import main.java.membership.MembershipPayment;
import main.java.util.Formatter;
import main.java.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Håndterer listen over medlemmer
 */
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

    /**
     * Fritekst søgning på medlemsnavn eller medlemsID
     * @param query søgestrengen, som søger på medlemsnavn eller medlemsID
     * @return ArrayList over medlemsID, der lever op til søgestrengen
     */

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


    Comparator<Member> byMemberID = Comparator.comparing(Member::getMemberID);
    Comparator<Member> byMemberName = Comparator.comparing(Member::getName);
    Comparator<Member> byMemberIDMemberName = byMemberID.thenComparing(byMemberName);

    /**
     * En liste over medlemmer sorteret efter først medlemsID og derefter medlemsnavn
     * @return sorteret ArrayList over Members
     */
    public ArrayList<Member> getAllMembersSortedByMemberIDName() {
        ArrayList<Member> membersSorted = new ArrayList<Member>(members.values());
        membersSorted.sort(byMemberIDMemberName);
        return membersSorted;
    }

    public Member findMemberByName(String name) {
        for(Member m : getAllMembers().values()) {
            if(m.getName().equalsIgnoreCase(name)) return m;
        }
        return null;
    }

}


