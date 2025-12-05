package main.java.membership;

import main.java.logic.MemberManager;

import java.time.LocalDate;

public class StamDataManager {

    private MemberManager memberManager;

    public StamDataManager(MemberManager memberManager){
        this.memberManager = memberManager;
    }

    public void updateName(int memberID, String newName){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setName(newName);
        } else {
            System.out.println("Medlem findes ikke");
        }
    }

    public void updateBirthday(int memberID, LocalDate birthday){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setBirthday(birthday);
        } else {
            System.out.println("Medlem findes ikke");
        }
    }

    public void updateMembershipActive(int memberID) {
        Member member = memberManager.getMember(memberID);
        if (member != null) {
            member.setMembership(new ActiveMembership());
        } else {
            System.out.println("Medlem findes ikke");
        }
    }
    public void updateMembershipPassive(int memberID){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setMembership(new PassiveMembership());
        } else {
            System.out.println("Medlem findes ikke");
        }
    }

    public void updatePlayPreference(int memberID, PlayPreference newType ){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setPlayPreference(newType);
        } else {
            System.out.println("Medlem findes ikke");
        }
    }

    public void updatePhoneNumber(int memberID, String phoneNumber){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setPhoneNumber(phoneNumber);
        } else {
            System.out.println("Medlem findes ikke");
        }
    }


}
