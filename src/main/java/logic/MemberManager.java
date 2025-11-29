package main.java.logic;

import main.java.membership.Member;
import main.java.util.Validator;

import java.util.ArrayList;
import java.util.Collection;
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
}


