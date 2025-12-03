package main.java.membership;

import main.java.util.Validator;

import java.time.LocalDate;

public class MembershipPayment {
    private static int nextID = 1;
    private int paymentID;
    private boolean isPaid;
    private LocalDate paidDate;
    private LocalDate dueDate;
    private String seasonQuarter;
    private Member member;
    private double amount;

    public MembershipPayment(Member member, LocalDate dueDate){
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

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }
/*
    private String getSeasonQuarter() {
        int monthValue = dueDate.getMonthValue();
        String yearString = String.valueOf(dueDate.getYear()).substring(2);
        if(monthValue >= 1 && monthValue <= 3){
            return yearString + "Q1";
        } else if (monthValue >= 4 && monthValue <= 6) {
            return yearString + "Q2";
        } else if (monthValue >= 7 && monthValue <= 9) {
            return yearString + "Q3";
        } else if (monthValue >= 10 && monthValue <= 12) {
            return yearString + "Q4";
        } else {
            return "DingDong your Calculation is Wrong";
        }
    }

 */
  
    public boolean getIsPaid(){
        return isPaid;
    }

    public double getAmount() {
        return amount;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = this.paymentID;
    }

    public void setSeasonQuarter(String seasonQuarter){
        this.seasonQuarter = this.seasonQuarter;
    }

    public void setAmount(double amount){
        this.amount = this.amount;
    }

    public String paymentString() {
        return "Kvartal: " + seasonQuarter + ", Beløb: " + amount + " kr." + ", BetalingsID: " + paymentID;
    }

    //Comparator<MembershipPayment> byDueDate = Comparator.comparing(MembershipPayment::getDueDate);
    //Comparator<MembershipPayment> byMemberID = Comparator.comparing(MembershipPayment::getMemberID);
    //Comparator<MembershipPayment> byDueDateThenMemberID = byDueDate.thenComparing(byMemberID);
    public String displayPaymentString(){
            return String.format("%-30s | %-10s | %-8s | %.0f kr.",
                    member.getName(),
                    member.getMemberID(),
                    seasonQuarter,
                    amount) +
                    "\n" + "----------------------------------------------------------------";

    }




    public static String futurePaymentHeader() {
        return String.format(
                "%-30s | %-10s | %-8s | %s",
                "NAVN",
                "MEDLEMS-ID",
                "KVARTAL",
                "BELØB"
        ) + "\n" +
                "----------------------------------------------------------------";
    }

    @Override
    public String toString(){
        return "Betalings - id: " + paymentID + "\n" +
                "Opkrævnings dato: " + dueDate + "\n" +
                "Betaling modtaget dato: " + paidDate + "\n" +
                "Kvartal: " + seasonQuarter + "\n" +
                "Medlem: " + member + "\n" +
                "Beløb: " + amount + " kr." + "\n";
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
