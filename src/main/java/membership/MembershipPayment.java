package main.java.membership;

import main.java.util.Validator;

import java.time.LocalDate;

public class MembershipPayment {
    private static int nextID = 1;
    private int paymentID;
    private boolean isPaid;
    private LocalDate dueDate;
    private LocalDate paidDate;
    private String seasonQuarter;
    private Member member;
    private double amount;

    public MembershipPayment(Member member, LocalDate dueDate) {
        this.paymentID = nextID++;
        this.paidDate = null;
        this.dueDate = dueDate;
        this.seasonQuarter = Validator.getSeasonQuarter(dueDate);
        this.member = member;
        this.amount = member.getMembership().calculateYearlyFee(member.getAge()) / 4;
        this.isPaid = false;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getMemberID() {
        return member.getMemberID();
    }

    public String getSeasonQuarter() {
        return seasonQuarter;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getIsPaidDate() {
        return dueDate;
    }

    public void setIsPaidDate(LocalDate paidDate) {
        this.paidDate = paidDate;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public double getAmount() {
        return amount;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = this.paymentID;
    }

    public void setSeasonQuarter(String seasonQuarter) {
        this.seasonQuarter = this.seasonQuarter;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


    public String paymentString() {
        return "Kvartal: " + seasonQuarter + ", Beløb: " + amount + " kr." + ", BetalingsID: " + paymentID;
    }

    public String displayPaymentString() {
        return String.format("%-30s | %-10s | %-8s | %.0f kr.",
                member.getName(),
                member.getMemberID(),
                seasonQuarter,
                amount) +
                "\n" + "----------------------------------------------------------------";

    }

    public static String futurePaymentHeader() {
        return String.format(
                "%-10s | %-30s | %-8s | %s",
                "MEDLEMS-ID",
                "NAVN",
                "BELØB",
                "KVARTAL"
        ) + "\n" +
                "----------------------------------------------------------------------------------";
    }

    @Override
    public String toString() {
        return "Betalings - id: " + paymentID + "\n" +
                "Opkrævnings dato: " + dueDate + "\n" +
                "Betaling modtaget dato: " + paidDate + "\n" +
                "Kvartal: " + seasonQuarter + "\n" +
                "Medlem: " + member + "\n" +
                "Beløb: " + amount + " kr." + "\n";
    }
}