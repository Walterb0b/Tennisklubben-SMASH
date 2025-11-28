package main.java.membership;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActiveMembershipTest {

    Member m1 = new Member("John Doe", "12345678", LocalDate.of(2008, 9, 23), LocalDate.of(2025,1,1),new ActiveMembership());
    Member m2 = new Member("Martin Henriksen", "12345678", LocalDate.of(2000, 9, 23), LocalDate.of(2025,1,1),new ActiveMembership());
    Member m3 = new Member("Lisa Henriksen", "12345678", LocalDate.of(1964, 9, 23), LocalDate.of(2025,1,1),new ActiveMembership());

    @Test
    void calculateYearlyFeeUnder18True() {
        assertEquals(800, m1.getMembership().calculateYearlyFee(m1.getAge()));
    }

    @Test
    void calculateYearlyFeeUnder18False() {
        assertNotEquals(1500, m1.getMembership().calculateYearlyFee(m1.getAge()));
    }

    @Test
    void calculateYearlyFeeOver18True() {
        assertEquals(1500, m2.getMembership().calculateYearlyFee(m2.getAge()));
    }

    @Test
    void calculateYearlyFeeOver18False() {
        assertNotEquals(800, m2.getMembership().calculateYearlyFee(m2.getAge()));
    }

    @Test
    void calculateYearlyFeeOver60True() {
        assertEquals(1125, m3.getMembership().calculateYearlyFee(m3.getAge()));
    }

    @Test
    void calculateYearlyFeeOver60False() {
        assertNotEquals(1500, m3.getMembership().calculateYearlyFee(m3.getAge()));
    }



}