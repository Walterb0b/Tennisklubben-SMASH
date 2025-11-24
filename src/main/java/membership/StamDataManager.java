package main.java.membership;

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
}
