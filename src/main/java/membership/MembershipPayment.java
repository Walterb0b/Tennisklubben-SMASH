package main.java.membership;

import main.java.util.Validator;

import java.time.LocalDate;

public class MembershipPayment {
    private static int nextID = 1;
    private int paymentID;
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
    }

    public int getPaymentID() {
        return paymentID;
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

    @Override
    public String toString(){
        return "Betalings - id: " + paymentID + "\n" +
                "Betalings dato: " + paidDate + "\n" +
                "Kvartal: " + seasonQuarter + "\n" +
                "Medlem: " + member + "\n" +
                "Beløb: " + amount + " kr." + "\n";
    }
}
