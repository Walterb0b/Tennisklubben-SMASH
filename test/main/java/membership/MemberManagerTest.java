package main.java.membership;

import main.java.logic.MemberManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberManagerTest {

    @Test
    void addMemberTest(){
        MemberManager mm = new MemberManager();
        Member m1 = new Member("Brian", "00000000", LocalDate.of(1989, 9, 18), new ActiveMembership());
        mm.addMember(m1);
        assertEquals(mm.getMember(m1.getMemberID()),m1 );
    }

}