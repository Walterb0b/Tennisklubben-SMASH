package main.java.membership;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getAgeTestTrue() {
        //Svært at skrive en god UnitTest da udregning af age afhænger af LocalDate.now()
        Member m1 = new Member("Brian", "00000000", LocalDate.of(1989, 9, 18), new ActiveMembership());

        assertEquals(36, m1.getAge());

    }

    @Test
    void getAgeTestFalse() {
        //Svært at skrive en god UnitTest da udregning af age afhænger af LocalDate.now()
        Member m1 = new Member("Brian", "00000000", LocalDate.of(1990, 9, 18), new ActiveMembership());

        assertNotEquals(36, m1.getAge());

    }

    @Test
    void testMemberIDincrementation() {
        Member m1 = new Member("Brian", "83910281", LocalDate.of(1995, 9, 18), new ActiveMembership());
        Member m2 = new Member("Børge", "12121212", LocalDate.of(1964, 9, 18), new ActiveMembership());
        Member m3 = new Member("Morten", "12345678", LocalDate.of(1973, 9, 18), new PassiveMembership());

        assertEquals(1, m1.getMemberID());
        assertEquals(2, m2.getMemberID());
        assertEquals(3, m3.getMemberID());

        assertTrue(m1.getMemberID() != m2.getMemberID());

        assertEquals( m3.getNextID(), (m3.getMemberID() + 1) );


    }

    @Test
    void testMemberIDReset() {
        Member m1 = new Member("Bjarne", "83910281", LocalDate.of(1995, 9, 18), new ActiveMembership());
        Member m2 = new Member("Lizzie", "12121212", LocalDate.of(1964, 9, 18), new ActiveMembership());


        assertEquals(1, m1.getMemberID());
        assertEquals(2, m2.getMemberID());


    }


}