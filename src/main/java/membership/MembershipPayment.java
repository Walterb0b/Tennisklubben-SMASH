package main.java.membership;

import java.time.LocalDate;

public class MembershipPayment {
    private static int nextID = 1;
    private int paymentID;
    private LocalDate paidDate;
    private String seasonQuarter;
    private Member member;
    private double amount;

    public MembershipPayment(Member member){
        this.paymentID = nextID++;
        this.paidDate = LocalDate.now();
        this.seasonQuarter = getSeasonQuarter(this.paidDate);
        this.member = member;
        this.amount = member.getMembership().calculateYearlyFee() / 4;
    }


    private String getSeasonQuarter(LocalDate date){
        int monthValue = (date.getMonthValue() - 1) / 3 + 1;
        if(monthValue == 1){
            return "Q1";
        } else if (monthValue == 2) {
            return "Q2";
        } else if (monthValue == 3) {
            return "Q3";
        } else if (monthValue == 4) {
            return "Q4";
        } else {
            return "DingDong your Calculation is Wrong";
        }
    }

    @Override
    public String toString(){
        return "Betalings - id: " + paymentID + "\n" +
                "Betalings dato: " + paidDate + "\n" +
                "Kvartal: " + seasonQuarter + "\n" +
                "Medlem: " + member + "\n" +
                "Beløb: " + amount + " kr." + "\n";
    }
}
