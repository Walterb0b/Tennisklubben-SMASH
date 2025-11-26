package main.java.logic;

import main.java.membership.Member;

import java.util.HashMap;

public class MemberManager {
    private HashMap<Integer, Member> members;

    public MemberManager() {
        members = new HashMap<>();
    }

    public void addMember(Member member) {
        members.put(member.getMemberID(), member);
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

    public int membersSize(){
        return  members.size();
    }
}


