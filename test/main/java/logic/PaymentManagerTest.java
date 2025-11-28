package main.java.logic;

import main.java.membership.ActiveMembership;
import main.java.membership.Member;
import main.java.membership.MembershipPayment;
import main.java.membership.PassiveMembership;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentManagerTest {
    Member m1 = new Member("Brian", "83910281", LocalDate.of(1995, 9, 18), new ActiveMembership());
    Member m2 = new Member("Børge", "12121212", LocalDate.of(1964, 9, 18), new ActiveMembership());
    Member m3 = new Member("Morten", "12345678", LocalDate.of(1973, 9, 18), new PassiveMembership());


    @Test
    void createSeasonQuarterListTrue() {

    }

}