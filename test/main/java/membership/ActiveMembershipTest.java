package main.java.membership;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActiveMembershipTest {

    Member m1 = new Member("John Doe", "12345678", LocalDate.of(2008, 9, 23), new ActiveMembership());

    @Test
    void calculateYearlyFeeSucces() {
        assertEquals(800, m1.getMembership().calculateYearlyFee());
    }

    @Test
    void calculateYearlyFeeFail() {
        assertEquals(1500, m1.getMembership().calculateYearlyFee());
    }
}