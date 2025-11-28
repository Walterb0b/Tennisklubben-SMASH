package main.java.membership;

import main.java.logic.MemberManager;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberManagerTest {
    MemberManager mm = new MemberManager();
    Member m1 = new Member("Brian Gren", "00000000", LocalDate.of(1989, 9, 18), LocalDate.of(2025,1,1), new ActiveMembership());
    Member m2 = new Member("Morten Brun", "12345678", LocalDate.of(1985, 9, 18), LocalDate.of(2025,1,1),new ActiveMembership());
    Member m3 = new Member("Marie Larsen", "87654321", LocalDate.of(1939, 2, 14), LocalDate.of(2025,1,1),new ActiveMembership());

    @Test
    void addMemberTest(){


        mm.addMember(m1);
        assertEquals(mm.getMember(m1.getMemberID()),m1 );
    }

    @Test
    void testSearchMemberID() {

        mm.addMember(m1);
        mm.addMember(m2);
        mm.addMember(m3);
        String query = "1";
        assertEquals(m1.getMemberID(), mm.searchForMemberIDs(query).getFirst());
    }

}