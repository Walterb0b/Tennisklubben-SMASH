package main.java.membership;

import main.java.logic.MemberManager;

import java.time.LocalDate;

public class StamDataManager {

    private MemberManager memberManager;

    public StamDataManager(MemberManager memberManager){
        this.memberManager = memberManager;
    }

    public boolean updateName(int memberID, String newName){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setName(newName);
            return true;
        }
        return false;
    }

    public boolean updateBirthday(int memberID, LocalDate birthday){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setBirthday(birthday);
            return true;
        }
        return false;
    }

    public boolean updateMembershipStatus(int memberID, boolean active){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setMembership(new ActiveMembership());
            return true;
        }
        return false;
    }

    public boolean updatePlayPreference(int memberID, PlayPreference newType ){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setPlayPreference(newType);
            return true;
        }
        return false;
    }

    public boolean updatePhoneNumber(int memberID, String phoneNumber){
        Member member = memberManager.getMember(memberID);
        if(member != null){
            member.setPhoneNumber(phoneNumber);
            return true;
        }
        return false;
    }

//    public boolean updateEmail(int memberID, String email){
//        Member member = memberManager.getMember(memberID);
//        if(member != null){
//            member.setEmail(email);
//            return true;
//        }
//        return false;
//    }
//    public boolean changeAddress(int memberID, String address){
//        Member member = memberManager.getMember(memberID);
//        if(member != null){
//            member.setAddress(address);
//            return true;
//        }
//        return false;
//    }
//
//    public boolean deactivateMembership(int memberID) {
//        Member member = memberManager.getMember(memberID);
//
//        if (member != null) {
//            member.setDeactivate(false);
//            return true;
//        }
//        return false;
//    }
//    public boolean activateMembership(int memberID) {
//        Member member = memberManager.getMember(memberID);
//
//        if (member != null) {
//            member.setActivate(true);
//            return true;
//        }
//        return false;
//    }

}
