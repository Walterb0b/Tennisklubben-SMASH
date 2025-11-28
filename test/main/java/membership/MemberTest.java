package main.java.membership;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    Member m1 = new Member("Brian", "83910281", LocalDate.of(1995, 9, 18), LocalDate.of(2025,1,1), new ActiveMembership());
    Member m2 = new Member("Børge", "12121212", LocalDate.of(1964, 9, 18), LocalDate.of(2025,1,1),new ActiveMembership());
    Member m3 = new Member("Morten", "12345678", LocalDate.of(1973, 9, 18), LocalDate.of(2025,1,1),new PassiveMembership());


    @Test
    void getAgeTestTrue() {
        //Svært at skrive en god UnitTest da udregning af age afhænger af LocalDate.now()


        assertEquals(30, m1.getAge());

    }

    @Test
    void getAgeTestFalse() {
        //Svært at skrive en god UnitTest da udregning af age afhænger af LocalDate.now()

        assertNotEquals(36, m1.getAge());

    }

    @Test
    void testMemberIDincrementation() {

        assertEquals(1, m1.getMemberID());
        assertEquals(2, m2.getMemberID());
        assertEquals(3, m3.getMemberID());

        assertTrue(m1.getMemberID() != m2.getMemberID());

        assertEquals( m3.getNextID(), (m3.getMemberID() + 1) );


    }

    @Test
    void testMemberIDReset() {

        assertEquals(1, m1.getMemberID());
        assertEquals(2, m2.getMemberID());


    }


}