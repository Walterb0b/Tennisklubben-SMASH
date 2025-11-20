package main.java.membership;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @Test
    void getAgeTestTrue() {
        Member m1 = new Member("Brian", 00000000, LocalDate.of(1989, 9, 18), new ActiveMembership());

        assertEquals(36, m1.getAge());

    }

    @Test
    void getAgeTestFalse() {
        Member m1 = new Member("Brian", 00000000, LocalDate.of(1990, 9, 18), new ActiveMembership());

        assertNotEquals(36, m1.getAge());

    }


}